package land.majazi.majazicore.tools.converter

class PersianNumberToEnglishNumber(private val number: String) {

    //______________________________________________________________________________________________ convert
    fun convert() : String {
        var stringNumber = ""
        if (number.isEmpty())
            return ""
        for (c in number)
            when (c) {
                '۰' -> stringNumber+= "0"
                '۱' -> stringNumber+= "1"
                '۲' -> stringNumber+= "2"
                '۳' -> stringNumber+= "3"
                '۴' -> stringNumber+= "4"
                '۵' -> stringNumber+= "5"
                '۶' -> stringNumber+= "6"
                '۷' -> stringNumber+= "7"
                '۸' -> stringNumber+= "8"
                '۹' -> stringNumber+= "9"
                '٫' -> stringNumber+= "."
                else -> stringNumber+= c
            }
        return stringNumber
    }
    //______________________________________________________________________________________________ convert

}