package land.majazi.majazicore.views.fragments

import android.Manifest
import android.app.Dialog
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.android.synthetic.main.dialog_pick_image.*
import land.majazi.majazicore.R
import land.majazi.majazicore.manager.DialogManager
import land.majazi.majazicore.manager.FileManager
import java.io.File

/**
 * need permissions
 * android.permission.WRITE_EXTERNAL_STORAGE
 * android.permission.CAMERA
 * in AndroidManifest
 * */

open class SelectPhotoFragment : Fragment() {

    private var crop = false
    private val liveData = MutableLiveData<File>()
    private var fileUri: Uri? = null

    private lateinit var dialog: Dialog


    //______________________________________________________________________________________________ selectPhotoDialog
    fun selectPhotoDialog(crop : Boolean, observer: Observer<File>) {
        context?.let { it ->
            this.crop = crop
            liveData.observe(viewLifecycleOwner, observer)
            Dexter.withContext(it)
                .withPermissions(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA
                )
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                        dialog = DialogManager(
                            it,
                            R.layout.dialog_pick_image
                        ).createDialog()
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
    }
    //______________________________________________________________________________________________ selectPhotoDialog


    //______________________________________________________________________________________________ cancelClick
    private fun cancelClick() {
        dialog.dismiss()
    }
    //______________________________________________________________________________________________ cancelClick


    //______________________________________________________________________________________________ galleryClick
    private fun galleryClick() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        registerPhoto.launch(gallery)
        dialog.dismiss()
    }
    //______________________________________________________________________________________________ galleryClick


    //______________________________________________________________________________________________ cameraClick
    private fun cameraClick() {
        val values = ContentValues(1)
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        fileUri = requireContext().contentResolver
            .insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                values
            )
        fileUri?.let { it ->
            val camera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            camera.putExtra(MediaStore.EXTRA_OUTPUT, it)
            camera.addFlags(
                Intent.FLAG_GRANT_READ_URI_PERMISSION
                        or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
            )
            registerCamera.launch(camera)
        }

        dialog.dismiss()
    }
    //______________________________________________________________________________________________ cameraClick


    //______________________________________________________________________________________________ registerPhoto
    private var registerPhoto = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            result.data?.let { it ->
                selectPhoto(it.data!!)
            }
        }
    }
    //______________________________________________________________________________________________ registerPhoto


    //______________________________________________________________________________________________ registerPhoto
    private var registerCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            fileUri?.let { it ->
                selectPhoto(it)
            }

        }
    }
    //______________________________________________________________________________________________ registerPhoto



    //______________________________________________________________________________________________ selectPhoto
    private fun selectPhoto(uri: Uri) {
        context?.let { it ->
            if (crop)
                crop
            else {
                val file = FileManager().getFileFromUri(it, uri)
                liveData.postValue(file)
            }
        }
    }
    //______________________________________________________________________________________________ selectPhoto


    //______________________________________________________________________________________________ crop
    private fun crop() {

    }
    //______________________________________________________________________________________________ crop


    //______________________________________________________________________________________________ onDestroyView
    override fun onDestroyView() {
        super.onDestroyView()
        liveData.removeObservers(viewLifecycleOwner)
    }
    //______________________________________________________________________________________________ onDestroyView


}