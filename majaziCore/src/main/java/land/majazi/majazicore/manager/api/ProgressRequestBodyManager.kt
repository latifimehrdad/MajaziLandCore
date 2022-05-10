package land.majazi.majazicore.manager.api

import android.os.Looper
import android.util.Log
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okio.BufferedSink
import java.io.File
import java.io.FileInputStream

class ProgressRequestBodyManager(
    private val file: File,
    private val contentType: String,
    private val uploadCallBack: UploadCallBack
) : RequestBody() {


    //______________________________________________________________________________________________ contentType
    override fun contentType(): MediaType? {
        return "$contentType/*".toMediaTypeOrNull()
    }
    //______________________________________________________________________________________________ contentType


    //______________________________________________________________________________________________ contentLength
    override fun contentLength(): Long {
        return file.length()
    }
    //______________________________________________________________________________________________ contentLength


    //______________________________________________________________________________________________ writeTo
    override fun writeTo(sink: BufferedSink) {
        val buffer = ByteArray(DEFAULT_BUFFER_SIZE)
        val fin = FileInputStream(file)
        var upload: Long = 0
        var read: Int
        val handle = android.os.Handler(Looper.getMainLooper())
        read = fin.read(buffer)
        while (read != -1) {
                handle.post {
                    uploadCallBack.onUploadPercent((100 * upload / file.length()).toInt())
                }
            upload += read
            sink.write(buffer, 0, read)
            read = fin.read(buffer)
        }
    }
    //______________________________________________________________________________________________ writeTo


    //______________________________________________________________________________________________ UploadCallBack
    interface UploadCallBack {
        fun onUploadPercent(percent: Int)
    }
    //______________________________________________________________________________________________ UploadCallBack
}