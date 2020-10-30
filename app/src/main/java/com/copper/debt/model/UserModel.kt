package com.copper.debt.model

import android.net.Uri
import kotlin.collections.HashMap

const val NO_USER_ID = "no user"

data class UserResponse(
    val username: String = "",
    val email: String = "",
    val contactsIds: List<String> = listOf(),
    val id: String = "",
    val photoUrl: String = ""
)

fun UserResponse.isValid() =
    username.isNotBlank()
            && email.isNotBlank()

fun UserResponse.mapToUser() = User(username, email, contactsIds, id, photoUrl)

data class User(
    val username: String,
    val email: String,
    val contactsIds: List<String>,
    val id: String,
    val photoUrl: String
) {
    override fun toString(): String {
        return username
    }
}

fun User.mapToRequest(): HashMap<String, Any> {
    val request = HashMap<String, Any>()
    request["username"] = username
    request["email"] = email
    request["contactsIds"] = contactsIds
    request["id"] = id
    request["photoUrl"] = photoUrl
    return request
}