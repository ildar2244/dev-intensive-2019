package ru.skillbranch.devintensive.utils

/** Created by qq_3000 on 08.10.2019. */
object Utils {

    //Разбиваем строку[имя] на 2 строки[имя фамилия]
    fun parseFullName(fullName: String?): Pair<String?, String?>{
        val parts: List<String>? = fullName?.split(" ")
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)
        return firstName to lastName
    }

    //Заменяем кириллицу на латиницу
    fun transliteration(payload: String, divider: String = " "): String {
        val chars = mapOf(
            'а' to "a",
            'б' to "b",
            'в' to "v",
            'г' to "g",
            'д' to "d",
            'е' to "e",
            'ё' to "e",
            'ж' to "zh",
            'з' to "z",
            'и' to "i",
            'й' to "i",
            'к' to "k",
            'л' to "l",
            'м' to "m",
            'н' to "n",
            'о' to "o",
            'п' to "p",
            'р' to "r",
            'с' to "s",
            'т' to "t",
            'у' to "u",
            'ф' to "f",
            'х' to "h",
            'ц' to "c",
            'ч' to "ch",
            'ш' to "sh",
            'щ' to "sh'",
            'ъ' to "",
            'ы' to "i",
            'ь' to "",
            'э' to "e",
            'ю' to "yu",
            'я' to "ya"
        )
        val pToChar = payload.toCharArray()
        var result = ""
        for (i in pToChar.iterator()) {
            when {
                i.toString() == divider -> result += divider
                i.isUpperCase() -> result += chars.getValue(i.toLowerCase()).capitalize()
                !chars.containsKey(i) -> result += i.toString()
                else -> result += chars.getValue(i)
            }
        }
        return result
    }

    //Создаем инициалы для имени и фамилии
    fun toInitials(firstName: String?, lastName: String?): String? {
        val letter1 = firstName?.trim()?.firstOrNull()?.toUpperCase()
        val letter2 = lastName?.trim()?.firstOrNull()?.toUpperCase()
        return when {
            letter1 != null && letter2 != null -> letter1.toString() + letter2.toString()
            letter1 != null && letter2 == null  -> letter1.toString()
            letter1 == null && letter2 != null  -> letter2.toString()
            else -> null
        }
    }
}