package land.majazi.majazicore.tools

import androidx.core.text.isDigitsOnly
import java.text.DecimalFormat

class Splitter {

    //______________________________________________________________________________________________ split
    fun split(value: String?): String {
        if (value.isNullOrEmpty())
            return ""
        val s = value.replace(",", "", false).replace("٬", "", false)
        return if (s.isDigitsOnly())
            split(s.toLong())
        else
            ""
    }
    //______________________________________________________________________________________________ split


    //______________________________________________________________________________________________ split
    fun split(value: Long): String {
        val dec = DecimalFormat("#,###")
        return dec.format(value)
    }
    //______________________________________________________________________________________________ split


    //______________________________________________________________________________________________ split
    fun split(value: Double): String {
        val dec = DecimalFormat("#,###.########")
        return dec.format(value)
    }
    //______________________________________________________________________________________________ split

}