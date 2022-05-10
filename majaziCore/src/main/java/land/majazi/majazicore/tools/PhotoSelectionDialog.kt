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
import kotlinx.android.synthetic.main.dialog_pick_image.*
import land.majazi.majazicore.R
import land.majazi.majazicore.manager.DialogManager

/**
* need permission android.permission.WRITE_EXTERNAL_STORAGE in AndroidManifest
* */

class PhotoSelectionDialog(private val activity: AppCompatActivity) : MutableLiveData<Uri>() {

    private lateinit var fileUri: Uri
    private lateinit var dialog: Dialog

    //______________________________________________________________________________________________ init
    init {
        Dexter.withContext(activity)
            .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                    dialog = DialogManager(activity, R.layout.dialog_pick_image).createDialog()
                    dialog.materialButtonCancel.setOnClickListener { cancelClick() }
                    dialog.materialButtonGallery.setOnClickListener { galleryClick() }
                    dialog.materialButtonCamera.setOnClickListener { cameraClick() }
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
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        registerPhoto.launch(gallery)
    }
    //______________________________________________________________________________________________ galleryClick


    //______________________________________________________________________________________________ cameraClick
    private fun cameraClick() {

        val values = ContentValues(1)
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpg")
        fileUri = activity.contentResolver
            .insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                values
            )!!
        val camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        camera.putExtra(MediaStore.EXTRA_OUTPUT, fileUri)
        camera.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION
                or Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        registerCamera.launch(camera)
    }
    //______________________________________________________________________________________________ cameraClick


    //______________________________________________________________________________________________ registerPhoto
    private val registerPhoto = activity.registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            if (result.data != null) {
                dialog.dismiss()
                postValue(result.data?.data)
            }
        }
    }
    //______________________________________________________________________________________________ registerPhoto


    //______________________________________________________________________________________________ registerPhoto
    private val registerCamera = activity.registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            dialog.dismiss()
            postValue(fileUri)
        }
    }
    //______________________________________________________________________________________________ registerPhoto


}