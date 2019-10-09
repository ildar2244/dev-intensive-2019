package ru.skillbranch.devintensive.utils

/** Created by qq_3000 on 08.10.2019. */
object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?>{
        val parts: List<String>? = fullName?.split(" ")
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)

        //example 1
//        return Pair(firstName, lastName)

        //example 2
        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}