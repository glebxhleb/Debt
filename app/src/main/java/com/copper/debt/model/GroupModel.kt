package com.copper.debt.model

data class GroupResponse(
    val name: String = "",
    val usersIds: List<String> = listOf()
)

fun GroupResponse.isValid() =
    name.isNotBlank()


fun GroupResponse.mapToGroup(groupId: String) = Group(groupId, name, usersIds)

data class Group(
    val id: String,
    val name: String,
    val users: List<String>
)