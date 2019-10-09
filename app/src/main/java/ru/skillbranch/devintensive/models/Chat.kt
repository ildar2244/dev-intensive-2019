package ru.skillbranch.devintensive.models

/** Created by qq_3000 on 09.10.2019. */
class Chat(
    val id: String,
    val members: MutableList<User> = mutableListOf(),
    val messages: MutableList<BaseMessage> = mutableListOf()
) {
}