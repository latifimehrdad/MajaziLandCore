package land.majazi.majazicore.manager.hash

import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class ShaUtils {

    //______________________________________________________________________________________________ digest
    private fun digest(input: ByteArray, algorithm: String): ByteArray? {
        val md: MessageDigest = try {
            MessageDigest.getInstance(algorithm)
        } catch (e: NoSuchAlgorithmException) {
            throw IllegalArgumentException(e)
        }
        return md.digest(input)
    }
    //______________________________________________________________________________________________ digest


    //______________________________________________________________________________________________ bytesToHex
    private fun bytesToHex(bytes: ByteArray): String {
        val sb = StringBuilder()
        for (b in bytes) {
            sb.append(String.format("%02x", b))
        }
        return sb.toString()
    }
    //______________________________________________________________________________________________ bytesToHex


    //______________________________________________________________________________________________ hashString
    fun hashString(s: String, algorithm: String?): String? {
        val shaInBytes: ByteArray? = algorithm?.let { ShaUtils().digest(s.toByteArray(StandardCharsets.UTF_8), it) }
        return shaInBytes?.let { bytesToHex(it) }
    }
    //______________________________________________________________________________________________ hashString

}