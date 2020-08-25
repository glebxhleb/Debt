package com.copper.debt.model

import com.copper.debt.R
import java.util.*

const val NO_GROUP = R.string.without_group
const val NO_GROUP_ID = "no group"

data class GroupResponse(
    val name: String = "",
    val usersIds: List<String> = listOf(),
    val groupId: String = ""
)

fun GroupResponse.isValid() =
    name.isNotBlank()
            && groupId.isNotBlank()


fun GroupResponse.mapToGroup(): Group = Group(name, usersIds, groupId)

data class Group(
    val name: String,
    val users: List<String>,
    val id: String = UUID.randomUUID().toString()
) {
    override fun toString(): String {
        return name
    }
}