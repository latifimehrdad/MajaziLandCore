package land.majazi.majazicore.tools.converter

import land.majazi.majazicore.models.SolarDateModel
import java.time.LocalDateTime

class Converter {

    //______________________________________________________________________________________________ toEnglishNumber
    fun toEnglishNumber(persianNumber: String): String {
        return PersianNumberToEnglishNumber(persianNumber).convert()
    }
    //______________________________________________________________________________________________ toEnglishNumber


    //______________________________________________________________________________________________ toSolarDate
    fun toSolarDate(gregorianDate: LocalDateTime): SolarDateModel {
        return GregorianDateToSolarDate(gregorianDate).convert()
    }
    //______________________________________________________________________________________________ toSolarDate


    //______________________________________________________________________________________________ toGregorianDate
    fun toGregorianDate(solarDate: String): LocalDateTime? {
        return SolarDateToGregorianDate(solarDate).convert()
    }
    //______________________________________________________________________________________________ toGregorianDate


}