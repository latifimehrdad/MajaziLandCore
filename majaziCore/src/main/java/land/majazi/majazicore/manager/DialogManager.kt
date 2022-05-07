package land.majazi.majazicore.manager

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.view.WindowManager

class DialogManager(private val context: Context, private val layout: Int) {

    //______________________________________________________________________________________________ createDialog
    fun createDialog(): Dialog {
        val dialog = Dialog(context)
        dialog.setCancelable(false)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(layout)
        val lp = WindowManager.LayoutParams()
        val window = dialog.window
        lp.copyFrom(window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.attributes = lp
        return dialog
    }
    //______________________________________________________________________________________________ createDialog

}