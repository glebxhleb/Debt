package com.copper.debt.model

data class GroupResponse(
    val id: String = "",
    val name: String = "",
    val users: List<User> = listOf()
)

data class Group(
    val id: String,
    val name: String,
    val users: List<User>
)