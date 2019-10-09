package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.utils.Utils
import java.util.*

/** Created by qq_3000 on 08.10.2019. */
data class User(
    val id: String,
    var firstName: String?,
    var lastName: String?,
    var avatar: String?,
    var rating: Int = 0,
    var respect: Int = 0,
    val lastVisit: Date? = null,
    val isOnline: Boolean = false
) {
    constructor(id: String, firstName: String?, lastName: String?) : this(
        id = id,
        firstName = firstName,
        lastName = lastName,
        avatar = null
    )

    constructor(id: String) : this(id, "John", "Doe $id")

    init {
        println("It's Alive!!!\n" +
                "${if (lastName==="Doe") "His name is $firstName $lastName" else "And his name is $firstName $lastName!!!\n"}")
    }

    companion object Factory {
        private var lastId: Int = -1
        fun makeUser(fullName: String?): User{
            lastId++

            //example 1
//            val parts: List<String>? = fullName?.split(" ")
//            val firstName = parts?.getOrNull(0)
//            val lastName = parts?.getOrNull(1)

            //example 2
            val (firstName, lastName) = Utils.parseFullName(fullName)

            return User(id = "$lastId", firstName = firstName, lastName = lastName)
        }
    }

    fun printMe() = println(
        """
            id: $id
            firstName: $firstName
            lastName: $lastName
            avatar: $avatar
            rating: $rating
            respect: $respect
            lastVisit: $lastVisit
            isOnline: $isOnline
        """.trimIndent()
    )
}