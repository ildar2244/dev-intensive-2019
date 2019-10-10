package ru.skillbranch.devintensive

import org.junit.Test

import org.junit.Assert.*
import ru.skillbranch.devintensive.exstensions.*
import ru.skillbranch.devintensive.models.*
import ru.skillbranch.devintensive.utils.Utils
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_instance() {
        val now = Date()
        val dateY = Date().add(-2, TimeUnits.YEAR)
        val dateM = Date().add(-3, TimeUnits.MONTH)
        val dateD = Date().add(-6, TimeUnits.DAY)
        val dateH = Date().add(-3, TimeUnits.HOUR)
        val dateMin = Date().add(-6, TimeUnits.MINUTE)
        val dateS = Date().add(-17, TimeUnits.SECOND)

        print(Date().humanizeDiff(dateY))
    }

    @Test
    fun test_copy() {
        val user = User.makeUser("John Wick")
        val user2 = user.copy(lastVisit = Date())
        val user3 = user.copy(lastVisit = Date().add(-2, TimeUnits.SECOND))
        val user4 = user.copy(lastName = "Cena", lastVisit = Date().add(2, TimeUnits.HOUR))

        println(
            """
            ${user.lastVisit?.format()}
            ${user2.lastVisit?.format()}
            ${user3.lastVisit?.format()}
            ${user4.lastVisit?.format()}
        """.trimIndent()
        )
    }

    @Test
    fun test_data_mapping() {
        val user = User.makeUser("Анатолий Борщов")
        println(user)

        val userView = user.toUserView()
        userView.printMe()
    }

    @Test
    fun test_abstract_factory() {
        val user = User.makeUser("Анатолий Борщов")
        val txtMessage =
            BaseMessage.makeMessage(user, Chat("0"), payload = "any text message", type = "text")
        val imgMessage =
            BaseMessage.makeMessage(user, Chat("0"), payload = "any image message", type = "image")

        //step1
//        when(txtMessage) {
//            is TextMessage -> println("this is text message")
//            is ImageMessage -> println("this is image message")
//        }

        //step2
        println(txtMessage.formatMessage())
        println(imgMessage.formatMessage())
    }
}
