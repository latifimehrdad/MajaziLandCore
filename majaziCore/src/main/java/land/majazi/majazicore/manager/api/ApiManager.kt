package land.majazi.majazicore.manager.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.google.gson.Gson
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.lang.Exception
import java.lang.StringBuilder


//__________________________________________________________________________________________________ apiCall
fun <T> apiCall(emitter: RemoteErrorEmitter, responseFunction: suspend () -> T): LiveData<T?> {
    return liveData {
        val response = privateApiCall(emitter) { responseFunction() }
        emit(response)
    }
}
//__________________________________________________________________________________________________ apiCall


//__________________________________________________________________________________________________ privateApiCall
private suspend fun <T> privateApiCall(
    emitter: RemoteErrorEmitter,
    responseFunction: suspend () -> T
): T? {
    return try {
        withTimeout(60000) {
            responseFunction()
        }
    } catch (e: Exception) {
        exceptionHandle(e, emitter)
        null
    }
}
//__________________________________________________________________________________________________ privateApiCall


//__________________________________________________________________________________________________ exceptionHandle
private fun exceptionHandle(e: Exception, emitter: RemoteErrorEmitter) {
    when (e) {
        is HttpException -> httpException(e, emitter)
        is TimeoutCancellationException -> emitter.onError(ErrorType.TimeOut, e.message)
        is IOException -> emitter.onError(ErrorType.Network, e.message)
        else -> emitter.onError(ErrorType.UNKNOWN, e.message)
    }
}
//__________________________________________________________________________________________________ exceptionHandle


//__________________________________________________________________________________________________ httpException
private fun httpException(e: HttpException, emitter: RemoteErrorEmitter) {
    when (e.code()) {
        401 -> emitter.unAuthorization(
            AuthorizationType.UnAuthorization,
            responseMessage(e.response())
        )
        403 -> emitter.unAuthorization(AuthorizationType.UnAccess, responseMessage(e.response()))
        400 -> emitter.onError(ErrorType.UNKNOWN, responseMessage(e.response()))
        else -> emitter.onError(ErrorType.UNKNOWN, responseMessage(e.response()))
    }
}
//__________________________________________________________________________________________________ httpException


//__________________________________________________________________________________________________ responseMessage
private fun responseMessage(response: Response<*>?): String? {
    val error = response?.errorBody()?.string()?.let {
        if (it.isEmpty())
            null
        else
            JSONObject(it)
    } ?: return response?.raw()?.toString()

    return if (!error.has("errors")) {
        val message = JSONObject(error.getString("message"))
        message.getString("Exception")
    } else {
        val message = error.getString("message")
        val errors = error.getString("errors")
            .let { Gson().fromJson(it, mutableListOf<String>().javaClass) }
        val sb = StringBuilder()
        sb.append(message)
        errors?.forEach { it ->
            run {
                sb.append("\n")
                sb.append(it)
            }
        }
        sb.toString()
    }
}
//__________________________________________________________________________________________________ responseMessage


interface RemoteErrorEmitter {
    fun unAuthorization(type: AuthorizationType, message: String?)
    fun onError(errorType: ErrorType, message: String?)
}


enum class ErrorType {
    Network,
    TimeOut,
    UNKNOWN
}


enum class AuthorizationType {
    UnAuthorization,
    UnAccess
}