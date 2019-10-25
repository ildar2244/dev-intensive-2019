package ru.skillbranch.devintensive.models

import java.util.*

/** Created by qq_3000 on 09.10.2019. */
abstract class BaseMessage(
    val id: String,
    val from: User?,
    val chat: Chat,
    val isIncoming: Boolean = false, //false=исходящее, true=входящее
    val date: Date = Date()
) {
    abstract fun formatMessage(): String

    companion object AbstractFactory {
        /* мой вариант. тест проходит BaseMessageTest.makeMessageTest()
        var lastId = -1

        fun makeMessage(from: User?, chat: Chat, date: Date = Date(), type: String = "text",
                        payload: Any?, isIncoming: Boolean = false): BaseMessage {
            lastId++

            return when(type) {
                "image" -> ImageMessage("$lastId", from, chat, date = date, image = payload as String, isIncoming = isIncoming)
                else -> TextMessage("$lastId", from, chat, date = date, text = payload as String, isIncoming = isIncoming)
            }
        } */

        /* Другой вариант решения от лектора */
        var lastId = 0
        fun makeMessage(from: User?, chat: Chat, date: Date = Date(), type:String = "text",
                        payload: Any, isIncoming: Boolean = false):BaseMessage{
            return when(type){
                "image"-> ImageMessage("${lastId++}", from, chat, date = date,
                    image = payload as String, isIncoming = isIncoming)
                "text" -> TextMessage("${lastId++}", from, chat, date = date,
                    text = payload as String, isIncoming = isIncoming)

                else-> if ("image" == payload || "text" == payload) makeMessage(from, chat, date,
                    payload, type, isIncoming) else throw IllegalArgumentException()
            }
        }
    }
}