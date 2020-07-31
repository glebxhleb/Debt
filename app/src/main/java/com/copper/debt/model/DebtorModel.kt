package com.copper.debt.model

data class Debtor(
    val name: String,
    val id: String,
    var sum: Double = 0.0
)