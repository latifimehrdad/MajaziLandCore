package land.majazi.majazilandcore.repo

import land.majazi.majazicore.manager.RemoteErrorEmitter
import land.majazi.majazicore.manager.apiCall
import land.majazi.majazilandcore.ApiInterface
import javax.inject.Inject

class PasswordRepo @Inject constructor(private val apiInterface: ApiInterface) {
    @Inject lateinit var emitter: RemoteErrorEmitter
    fun register() = apiCall(emitter){ apiInterface.register("","","")}
}