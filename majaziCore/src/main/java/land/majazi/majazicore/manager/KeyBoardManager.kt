package land.majazi.majazicore.manager

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

class KeyBoardManager {

    //______________________________________________________________________________________________ hide
    fun hide(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val view = activity.currentFocus
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
    //______________________________________________________________________________________________ hide

}