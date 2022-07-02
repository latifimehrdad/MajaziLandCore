package land.majazi.majazicore.manager

import android.view.Gravity
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class MessageManager @Inject constructor() {

    //---------------------------------------------------------------------------------------------- showSnackBar
    fun showSnackBar(view: CoordinatorLayout, message: String, actionTitle: String, action: Unit) {
        val mySnack = Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE)
        mySnack.setAction(actionTitle) {
            mySnack.dismiss()
            action
        }
        val view: View = mySnack.view
        val params: CoordinatorLayout.LayoutParams = view.layoutParams as CoordinatorLayout.LayoutParams
        params.gravity = Gravity.TOP
        view.layoutParams = params
        mySnack.show()
    }
    //---------------------------------------------------------------------------------------------- showSnackBar
}