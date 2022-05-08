package land.majazi.majazicore.tools

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData

class PhotoCropper(activity: AppCompatActivity, uri: Uri) {

    private val cropIntent = Intent("com.android.camera.action.CROP")
    private var liveDate: MutableLiveData<Bitmap>

    init {
        cropIntent.setDataAndType(uri, "image/*")
        cropIntent.putExtra("crop", true)
        cropIntent.putExtra("outputX", 180)
        cropIntent.putExtra("outputY", 180)
        cropIntent.putExtra("aspectX", 3)
        cropIntent.putExtra("aspectY", 4)
        cropIntent.putExtra("scaleUpIfNeeded", true)
        cropIntent.putExtra("return-data", true)
        liveDate = MutableLiveData()
    }


    fun crop(): MutableLiveData<Bitmap> {
        registerPhoto.launch(cropIntent)
        return liveDate
    }


    //______________________________________________________________________________________________ registerPhoto
    private val registerPhoto = activity.registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            if (result.data != null) {
                val bundle = result.data!!.extras
                val bitmap = bundle!!.getParcelable<Bitmap>("data")
                liveDate.postValue(bitmap!!)
            }
        }
    }
    //______________________________________________________________________________________________ registerPhoto

}