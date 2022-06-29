package land.majazi.majazicore.manager

import android.content.Context
import android.os.Build
import java.util.*

class DeviceManager {

    //__________________________________________________________________________________________ appVersionCode
    fun appVersionCode(context: Context): Long {
        return try {
            context.packageManager.getPackageInfo(context.packageName, 0).longVersionCode
        } catch (E: Exception) {
            0
        }
    }
    //__________________________________________________________________________________________ appVersionCode


    //__________________________________________________________________________________________ androidVersion
    fun androidVersion(): String {
        return try {
            val release = Build.VERSION.RELEASE
            val sdkVersion = Build.VERSION.SDK_INT
            "Android Version is $release & SDK Version is $sdkVersion"
        } catch (e: Exception) {
            "Android Version : ${e.message}"
        }
    }
    //__________________________________________________________________________________________ androidVersion


    //__________________________________________________________________________________________ deviceBrand
    fun deviceBrand(): String {
        return try {
            val manufacturer = Build.MANUFACTURER
            val model = Build.MODEL
            model.replace("-", "_")
            if (model.startsWith(manufacturer))
                model.replaceFirstChar { it.uppercase() }
            else
                "Device Brand : ${manufacturer.replaceFirstChar { it.uppercase() }} ${model.replaceFirstChar { it.uppercase() }}"
        } catch (e: java.lang.Exception) {
            "Device Brand : ${e.message}"
        }
    }
    //__________________________________________________________________________________________ deviceBrand


    //__________________________________________________________________________________________ deviceLanguage
    fun deviceLanguage(): String {
        return try {
            val language = Locale.getDefault().displayLanguage
            "Device Language : $language"
        } catch (e: Exception) {
            return "Device Language : ${e.message}"
        }
    }
    //__________________________________________________________________________________________ deviceLanguage

}