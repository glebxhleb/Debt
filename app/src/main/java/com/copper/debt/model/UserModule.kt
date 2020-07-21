package com.copper.debt.model

import android.provider.ContactsContract

data class UserResponse(
    val id: String = "",
    val username: String = "",
    val email: String = ""
)
fun UserResponse.isValid() =
        username.isNotBlank()
        && email.isNotBlank()

fun UserResponse.mapToUser(userId: String) = User(userId, username, email)

data class User(
    val id: String,
    val username: String,
    val email: String)

fun User.mapToRequest() :  HashMap<String, Any>{
    val request = HashMap<String, Any>()
    request[username] = username
    request[email] = email
    return request
}