package land.majazi.majazicore.manager

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(private val context: Context) {

    //______________________________________________________________________________________________ get
    fun get(): SharedPreferences{
        return context.getSharedPreferences("secret_shared_prefs", Context.MODE_PRIVATE)
    }
    //______________________________________________________________________________________________ get

}