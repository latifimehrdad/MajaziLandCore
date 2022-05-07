package land.majazi.majazicore.manager

import android.content.Context
import kotlinx.android.synthetic.main.dialog_pick_image.*
import land.majazi.majazicore.R

class ImageManager(private val context: Context) {

    var crop: Boolean = false

   fun pickImageFromGallery(dialogTitle: String, dialogContent: String,  crop: Boolean) {
       this.crop = crop
       val pickDialog = DialogManager(context, R.layout.dialog_pick_image).createDialog()
       pickDialog.textViewTitle.text = dialogTitle
       pickDialog.textViewContent.text = dialogContent
       pickDialog.show()
   }
}