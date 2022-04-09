package land.majazi.majazicore.manager.hash

import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import kotlin.experimental.and

class AESUtils {

    companion object {
        var keyValue = byteArrayOf(
            'c'.code.toByte(),
            'o'.code.toByte(),
            'd'.code.toByte(),
            'i'.code.toByte(),
            'n'.code.toByte(),
            'g'.code.toByte(),
            'a'.code.toByte(),
            'f'.code.toByte(),
            'f'.code.toByte(),
            'a'.code.toByte(),
            'i'.code.toByte(),
            'r'.code.toByte(),
            's'.code.toByte(),
            'c'.code.toByte(),
            'o'.code.toByte(),
            'm'.code.toByte()
        )
    }

    //______________________________________________________________________________________________ private fun
    @Throws(java.lang.Exception::class)
    private fun getRawKey(): ByteArray {
        val key: SecretKey = SecretKeySpec(keyValue, "AES")
        return key.encoded
    }

    @Throws(java.lang.Exception::class)
    private fun encrypt(raw: ByteArray, clear: ByteArray): ByteArray {
        val skeySpec: SecretKey = SecretKeySpec(raw, "AES")
        val cipher: Cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec)
        return cipher.doFinal(clear)
    }


    @Throws(java.lang.Exception::class)
    private fun decrypt(encrypted: ByteArray): ByteArray {
        val skeySpec: SecretKey = SecretKeySpec(keyValue, "AES")
        val cipher = Cipher.getInstance("AES")
        cipher.init(Cipher.DECRYPT_MODE, skeySpec)
        return cipher.doFinal(encrypted)
    }


    private fun toByte(hexString: String): ByteArray {
        val len = hexString.length / 2
        val result = ByteArray(len)
        for (i in 0 until len) result[i] = Integer.valueOf(
            hexString.substring(2 * i, 2 * i + 2),
            16
        ).toByte()
        return result
    }


    private fun toHex(buf: ByteArray?): String {
        if (buf == null) return ""
        val result = StringBuffer(2 * buf.size)
        for (i in buf.indices) {
            appendHex(result, buf[i])
        }
        return result.toString()
    }


    private val hex = "0123456789ABCDEF"

    private fun appendHex(sb: StringBuffer, b: Byte) {
        sb.append(hex[(b.toInt() shr 4) and 0x0f]).append(hex[(b and 0x0f).toInt()])
    }
    //______________________________________________________________________________________________ private fun



    //______________________________________________________________________________________________ public fun
    @Throws(Exception::class)
    fun encrypt(cleartext: String): String {
        val rawKey: ByteArray = getRawKey()
        val result: ByteArray = encrypt(rawKey, cleartext.toByteArray())
        return toHex(result)
    }


    @Throws(java.lang.Exception::class)
    fun decrypt(encrypted: String): String {
        val enc: ByteArray = toByte(encrypted)
        val result: ByteArray = decrypt(enc)
        return String(result)
    }
    //______________________________________________________________________________________________ public fun

}