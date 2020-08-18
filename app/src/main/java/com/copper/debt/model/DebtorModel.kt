package com.copper.debt.model

data class Debtor(
    val user: User,
    var sum: Double = 0.0
)