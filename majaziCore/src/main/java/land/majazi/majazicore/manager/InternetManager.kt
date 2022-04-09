package land.majazi.majazicore.manager

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class InternetManager {

    //______________________________________________________________________________________________ connection
    fun connection(context: Context): EnumInternetConnection {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities = cm.getNetworkCapabilities(cm.activeNetwork)
        return if (capabilities == null)
            EnumInternetConnection.NONE
        else {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> EnumInternetConnection.CELLULAR
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> EnumInternetConnection.WIFI
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI_AWARE) -> EnumInternetConnection.WIFI
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> EnumInternetConnection.WIFI
                else -> EnumInternetConnection.NONE
            }
        }
    }
    //______________________________________________________________________________________________ connection


    //______________________________________________________________________________________________ EnumInternetConnection
    enum class EnumInternetConnection {
        WIFI,
        CELLULAR,
        NONE
    }
    //______________________________________________________________________________________________ EnumInternetConnection
}