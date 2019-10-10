package ru.skillbranch.devintensive.models

import ru.skillbranch.devintensive.exstensions.humanizeDiff
import java.util.*

/** Created by qq_3000 on 09.10.2019. */
class TextMessage(
    id: String,
    from: User?,
    chat: Chat,
    isIncoming: Boolean = false,
    date: Date = Date(),
    var text: String
) : BaseMessage(id, from, chat, isIncoming, date) {

    override fun formatMessage(): String = "${from?.firstName} " +
            "${if (isIncoming) "получил" else "отправил"} сообщение \"$text\" ${date.humanizeDiff()}"
}