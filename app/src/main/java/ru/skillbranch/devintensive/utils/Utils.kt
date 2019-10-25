package ru.skillbranch.devintensive.utils

import android.content.Context
import kotlin.math.roundToInt

/** Created by qq_3000 on 08.10.2019. */
object Utils {

    //Разбиваем строку[имя] на 2 строки[имя фамилия]
    fun parseFullName(fullName: String?): Pair<String?, String?>{

        /* Это мой вариант. Не проходит тест Utils.parseFullNameTest()
        val parts: List<String>? = fullName?.split(" ")
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        */

        // Этот вариант проходит тест UtilsTest.parseFullNameTest()
        val parts = fullName?.trim()?.split(" ")
        val firstName = parts?.getOrNull(0)?.ifEmpty { null }
        val lastName = parts?.getOrNull(1)?.ifEmpty { null }

        return firstName to lastName
    }

    //Создаем инициалы для имени и фамилии
    fun toInitials(firstName: String?, lastName: String?): String? {

        /* Другой вариант решения от лектора
        val name = firstName.orEmpty().trim().getOrNull(0)?.toUpperCase()
        val surname = lastName.orEmpty().trim().getOrNull(0)?.toUpperCase()
        val firstInit = name?.toString() ?: ""
        val secondInit = surname?.toString() ?: ""
        return "$firstInit$secondInit".ifEmpty { null }*/

        /* мой вариант. тест проходит UtilsTest.toInitialsTest() */
        val letter1 = firstName?.trim()?.firstOrNull()?.toUpperCase()
        val letter2 = lastName?.trim()?.firstOrNull()?.toUpperCase()
        return when {
            letter1 != null && letter2 != null -> letter1.toString() + letter2.toString()
            letter1 != null && letter2 == null  -> letter1.toString()
            letter1 == null && letter2 != null  -> letter2.toString()
            else -> null
        }
    }

    //Заменяем кириллицу на латиницу
    fun transliteration(payload: String, divider: String = " "): String {
        val map = fillTranslitMap()
        val builder = StringBuilder()

        for (char in payload.trim())
            builder.append(getTranslChar(char, map))

        return builder.toString().replace(" ", divider)
    }

    private fun getTranslChar(char: Char, map: HashMap<Char, String>): String {
        val transl  = map[char.toLowerCase()] ?: char.toString()

        return if (char.isUpperCase() && transl.isNotEmpty())
            transl.capitalize()
        else transl
    }

    private fun fillTranslitMap(): HashMap<Char, String> {
        val map = hashMapOf<Char, String>()
        map['а'] = "a"
        map['б'] = "b"
        map['в'] = "v"
        map['г'] = "g"
        map['д'] = "d"
        map['е'] = "e"
        map['ё'] = "e"
        map['ж'] = "zh"
        map['з'] = "z"
        map['и'] = "i"
        map['й'] = "i"
        map['к'] = "k"
        map['л'] = "l"
        map['м'] = "m"
        map['н'] = "n"
        map['о'] = "o"
        map['п'] = "p"
        map['р'] = "r"
        map['с'] = "s"
        map['т'] = "t"
        map['у'] = "u"
        map['ф'] = "f"
        map['х'] = "h"
        map['ц'] = "c"
        map['ч'] = "ch"
        map['ш'] = "sh"
        map['щ'] = "sh'"
        map['ъ'] = ""
        map['ы'] = "i"
        map['ь'] = ""
        map['э'] = "e"
        map['ю'] = "yu"
        map['я'] = "ya"

        return map
    }

    fun convertPxToDp(context: Context, px: Int): Int {
        return (px / context.resources.displayMetrics.density).roundToInt()
    }

    fun convertDpToPx(context: Context, dp: Float): Int {
        return (dp * context.resources.displayMetrics.density).roundToInt()
    }

    fun convertSpToPx(context: Context, sp: Int): Int {
        return sp * context.resources.displayMetrics.scaledDensity.roundToInt()
    }
}