package land.majazi.majazilandcore.repo

import land.majazi.majazicore.manager.api.RemoteErrorEmitter
import land.majazi.majazicore.manager.api.apiCall
import land.majazi.majazilandcore.ApiInterface
import javax.inject.Inject

class PasswordRepo @Inject constructor(private val apiInterface: ApiInterface) {
    @Inject lateinit var emitter: RemoteErrorEmitter
    fun register() = apiCall(emitter){ apiInterface.register("","","")}
}