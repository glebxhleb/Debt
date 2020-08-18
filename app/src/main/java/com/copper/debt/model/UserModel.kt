package com.copper.debt.model

data class UserResponse(
    val username: String = "",
    val email: String = "",
    val contactsIds: List<String> = listOf()
)

fun UserResponse.isValid() =
    username.isNotBlank()
            && email.isNotBlank()

fun UserResponse.mapToUser(userId: String) = User(userId, username, email, contactsIds)

data class User(
    val id: String,
    val username: String,
    val email: String,
    val contactsIds: List<String>
) {
    override fun toString(): String {
        return "User(username='$username')"
    }
}

fun User.mapToRequest(): HashMap<String, Any> {
    val request = HashMap<String, Any>()
    request["username"] = username
    request["email"] = email
    request["contactsIds"] = contactsIds
    return request
}