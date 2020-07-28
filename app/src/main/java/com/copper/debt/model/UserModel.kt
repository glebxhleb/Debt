package com.copper.debt.model

data class UserResponse(
    val username: String = "",
    val email: String = "",
    val contactsIds: Map<String, String> = mapOf()
)
fun UserResponse.isValid() =
        username.isNotBlank()
        && email.isNotBlank()

fun UserResponse.mapToUser(userId: String) = User(userId, username, email, contactsIds)

data class User(
    val id: String,
    val username: String,
    val email: String,
    val contactsIds: Map<String, String>)

fun User.mapToRequest() :  HashMap<String, Any>{
    val request = HashMap<String, Any>()
    request["username"] = username
    request["email"] = email
    request["contactsIds"] = contactsIds
    return request
}