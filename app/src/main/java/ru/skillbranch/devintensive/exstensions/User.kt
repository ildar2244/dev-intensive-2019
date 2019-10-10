package ru.skillbranch.devintensive.exstensions

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils

/** Created by qq_3000 on 09.10.2019. */

fun User.toUserView(): UserView {

    val nickName = Utils.transliteration("$firstName $lastName")
    val status = when {
        lastVisit == null -> "Ещё ни разу не был"
        isOnline -> "online"
        else -> "Последний раз был ${lastVisit.humanizeDiff()}"
    }
    val initials = Utils.toInitials(firstName, lastName)

    return UserView(
        id,
        fullName = "$firstName $lastName",
        avatar = avatar,
        nickName = nickName,
        status = status,
        initials = initials
    )
}
