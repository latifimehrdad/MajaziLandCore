package land.majazi.majazicore.manager

import android.app.Activity
import android.app.KeyguardManager
import android.content.Context
import androidx.core.app.ActivityCompat

class BiometricManager {


    //______________________________________________________________________________________________ checkBiometric
    fun checkBiometric(activity: Activity) {
        val keyguardManager: KeyguardManager = activity.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        if (keyguardManager.isDeviceSecure) {

            val intent = keyguardManager.createConfirmDeviceCredentialIntent("رمز ورود به گوشی را وارد نمایید", "از وارد کردن رمز مجازی لند خودداری نمایید")
            ActivityCompat.startActivityForResult(activity, intent, 11, null)
        }
    }
    //______________________________________________________________________________________________ checkBiometric


}