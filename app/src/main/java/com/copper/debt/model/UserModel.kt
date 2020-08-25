package com.copper.debt.model

import kotlin.collections.HashMap

const val NO_USER_ID = "no user"

data class UserResponse(
    val username: String = "",
    val email: String = "",
    val contactsIds: List<String> = listOf(),
    val id: String = ""
)

fun UserResponse.isValid() =
    username.isNotBlank()
            && email.isNotBlank()

fun UserResponse.mapToUser() = User(username, email, contactsIds, id)

data class User(
    val username: String,
    val email: String,
    val contactsIds: List<String>,
    val id: String
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
    return request
}