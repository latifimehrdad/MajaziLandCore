package land.majazi.majazicore.tools

import android.Manifest
import android.app.Dialog
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import land.majazi.majazicore.R
import land.majazi.majazicore.manager.DialogManager

/**
 * need permissions
 *  - android.permission.WRITE_EXTERNAL_STORAGE in AndroidManifest
 *  - android.permission.CAMERA
 *  - android.permission.RECORD_AUDIO
 *  - android.permission.READ_EXTERNAL_STORAGE
 * */

class VideoRecorder(private val activity: AppCompatActivity) : MutableLiveData<Uri>() {

    private lateinit var dialog: Dialog

    //______________________________________________________________________________________________ init
    init {
        Dexter.withContext(activity)
            .withPermissions(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA
            )
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                    dialog = DialogManager(activity, R.layout.fragment_capture_video).createDialog()
                    dialog.show()
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    p1: PermissionToken?
                ) {

                }
            }).check()
    }
    //______________________________________________________________________________________________ init


    //______________________________________________________________________________________________ cancelClick
    private fun cancelClick() {
        dialog.dismiss()
    }
    //______________________________________________________________________________________________ cancelClick


    //______________________________________________________________________________________________ galleryClick
    private fun galleryClick() {

    }
    //______________________________________________________________________________________________ galleryClick


    //______________________________________________________________________________________________ cameraClick
    private fun cameraClick() {
        val values = ContentValues(1)
        values.put(MediaStore.Images.Media.MIME_TYPE, "video/mp4")
        val fileUri = activity.contentResolver
            .insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                values
            )!!
        val camera = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
        camera.putExtra(Intent.EXTRA_TITLE, "Choose an action");
        camera.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
        camera.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        registerCamera.launch(camera)
    }
    //______________________________________________________________________________________________ cameraClick



    //______________________________________________________________________________________________ registerPhoto
    private val registerCamera = activity.registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            postValue(result.data?.data)
        }
    }
    //______________________________________________________________________________________________ registerPhoto


}