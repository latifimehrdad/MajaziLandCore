package land.majazi.majazicore.tools

import androidx.core.text.isDigitsOnly
import java.math.BigInteger

class Validator {

    fun isText(text: String?): Boolean{
        return !text.isNullOrBlank()
    }

    fun isMobile(mobile: String?): Boolean {
        return if (isText(mobile)) {
            val regex = Regex("^(09)\\d{9}")
            mobile!!.matches(regex)
        } else
            false
    }

    fun isPhone(phone: String?): Boolean {
        return if (isText(phone)) {
            val regex = Regex("^(0)([1-8])\\d{9}")
            phone!!.matches(regex)
        } else
            return false
    }

    fun isEmail(email: String?): Boolean {
        return if (isText(email)) {
            val regex = Regex(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
            )
            email!!.matches(regex)
        } else
            return false
    }

    fun isNationalCode(nationalCode: String?): Boolean {
        return if (isText(nationalCode)) {

            if (!nationalCode!!.isDigitsOnly())
                return false

            if (nationalCode.length != 10)
                return false

            val revers = nationalCode.reversed()
            val arrayNationalCode: Array<Int> =
                revers.toCharArray().map { it.toString().toInt() }.toTypedArray()

            var sum = 0
            for (i in 9 downTo 1)
                sum += arrayNationalCode[i] * (i + 1)
            val temp = sum % 11
            if (temp < 2)
                arrayNationalCode[0] == temp
            else
                arrayNationalCode[0] == 11 - temp
        } else
            return false
    }

    fun isStrongPassword(password: String?): Boolean {
        return if (isText(password)) {
            val regex = Regex("^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#\$%^&+=!]).{6,}\$")
            password!!.matches(regex)
        } else
            return false
    }

    fun isSignsInPassword(password: String?): Boolean {
        return if (isText(password)) {
            val regex = Regex("^(?=.*[@#\$%^&+=!]).+\$")
            password!!.matches(regex)
        } else
            return false
    }

    fun isUppercaseInPassword(password: String?): Boolean {
        return if (isText(password)) {
            val regex = Regex("^(?=.*[A-Z]).+\$")
            password!!.matches(regex)
        } else
            return false
    }

    fun isNumberInPassword(password: String?): Boolean {
        return if (isText(password)) {
            val regex = Regex("^(?=.*[0-9]).+\$")
            password!!.matches(regex)
        } else
            return false
    }

    fun isLowerInPassword(password: String?): Boolean {
        return if (isText(password)) {
            val regex = Regex("^(?=.*[a-z]).+\$")
            password!!.matches(regex)
        } else
            return false
    }

    fun isShaba(shaba: String?): Boolean {
        return if (isText(shaba)) {

            if (!shaba!!.isDigitsOnly())
                return false

            if (shaba.length != 24)
                return false

            var place1 = "IR" + shaba.subSequence(0, 2).toString()
            var place2 = shaba.subSequence(2, 24).toString()
            place1 = place1.replace("IR", "1827", true)
            place2 += place1
            val big = place2.toBigInteger()
            big.mod(BigInteger.valueOf(97)).toInt() == 1
        } else
            return false
    }

    fun isPriceForGetWay(price: String?): Boolean {
        return if (isText(price)) {
            val p = price!!.replace(",", "", false).replace("٬", "", false)
            if (!p.isDigitsOnly())
                return false
            val priceLong = p.toLong()
            !(priceLong < 1000 || priceLong > 49900000)
        } else
            return false
    }

    fun isPostalCode(postalCode: String?): Boolean {
        return if (isText(postalCode)) {
            val regex = Regex("\\b(?!(\\d)\\1{3})[13-9]{4}[1346-9][013-9]{5}\\b")
            postalCode!!.matches(regex)
        } else
            return false
    }

    fun isPersianName(name: String?): Boolean {
        return if (isText(name)) {
            val regex = Regex("^(?=.*[آ-ی]).+\$")
            name!!.matches(regex)
        } else
            return false
    }

    fun isCardNumber(cardNumber: String?): Boolean {

        return if (isText(cardNumber)) {

            if (!cardNumber!!.isDigitsOnly())
                return false

            if (cardNumber.length != 16)
                return false

            val arrayCard: Array<Int> =
                cardNumber.toCharArray().map { it.toString().toInt() }.toTypedArray()

            var cardTotal = 0

            for (i in arrayCard.indices)
                cardTotal += if (i % 2 == 0)
                    if (arrayCard[i] * 2 > 9) arrayCard[i] * 2 - 9 else arrayCard[i] * 2
                else
                    arrayCard[i]
            cardTotal % 10 == 0
        } else
            return false
    }
}