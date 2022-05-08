package land.majazi.majazicore.manager

import android.content.Context
import android.net.Uri
import java.io.File

class FileManager {

    //______________________________________________________________________________________________ getFileFromUri
    fun getFileFromUri(context: Context, uri: Uri): File {
        val cursor = context.contentResolver.query(
            Uri.parse(uri.toString()),
            Array(1) {android.provider.MediaStore.Images.ImageColumns.DATA},
            null, null, null)
        cursor?.moveToFirst()
        val photoPath = cursor?.getString(0)
        cursor?.close()
        return File(photoPath!!)
    }
    //______________________________________________________________________________________________ getFileFromUri


}