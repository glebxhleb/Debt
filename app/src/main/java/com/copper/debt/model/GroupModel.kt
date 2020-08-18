package com.copper.debt.model

import com.copper.debt.R

const val NO_GROUP = R.string.without_group
const val NO_GROUP_ID = "no group"

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