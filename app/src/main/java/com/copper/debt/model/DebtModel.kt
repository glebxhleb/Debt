package com.copper.debt.model

import java.util.*
import kotlin.collections.HashMap

data class DebtResponse(
    val groupId: String = "",
    val creditorId: String = "",
    val creditorName: String = "",
    val text: String = "",
    val debtorsIds: Map<String, Double> = mapOf(),
    val debtDate: String = "",
    val lastChangerId: String = "",
    val lastChangeDate: String = "",
    val status: String = "",
    val currency: String = ""
)

fun DebtResponse.isValid() = groupId.isNotBlank()
        && creditorId.isNotBlank()
        && creditorName.isNotBlank()
        && text.isNotBlank()
        && debtorsIds.isNotEmpty()
        && debtDate.isNotBlank()
        && lastChangerId.isNotBlank()
        && lastChangeDate.isNotBlank()
        && status.isNotBlank()
        && currency.isNotBlank()

fun DebtResponse.mapToDebt(firebaseId: String) = Debt(
    firebaseId,
    groupId,
    creditorId,
    creditorName,
    text,
    debtorsIds,
    debtDate,
    lastChangerId,
    lastChangeDate,
    Status.valueOf(status),
    Currency.getInstance(currency)
)

data class Debt(
    val id: String,
    val groupId: String,
    val creditorId: String,
    val creditorName: String,
    val text: String,
    val debtorsIds: Map<String, Double>,
    val debtDate: String,
    val lastChangerId: String,
    val lastChangeDate: String,
    val status: Status,
    val currency: Currency
)

fun Debt.mapToRequest(): HashMap<String, Any> {
    val request = HashMap<String, Any>()
    request["groupId"] = groupId
    request["creditorId"] = creditorId
    request["creditorName"] = creditorName
    request["text"] = text
    request["debtorsIds"] = debtorsIds
    request["debtDate"] = debtDate
    request["lastChangerId"] = lastChangerId
    request["lastChangeDate"] = lastChangeDate
    request["status"] = status.toString()
    request["currency"] = currency.currencyCode
    return request
}

enum class Status {
    REMOVED,
    ADDED,
    CHANGED,
    NEW
}