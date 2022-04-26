package land.majazi.majazilandcore.repo

import land.majazi.majazicore.manager.RemoteErrorEmitter
import land.majazi.majazicore.manager.apiCall
import land.majazi.majazilandcore.ApiInterface
import javax.inject.Inject

class DashboardRepo @Inject constructor(private val apiInterface: ApiInterface) {
    @Inject lateinit var emitter: RemoteErrorEmitter
    fun getDashboard() = apiCall(emitter){apiInterface.getDashboard("sadhgdasgdhjgjasdg")}
}