package com.copper.debt.model

import java.io.Serializable
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
    val currency: String = "",
    val debtId: String = ""
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
        && debtId.isNotBlank()

fun DebtResponse.mapToDebt() = Debt(
    groupId,
    creditorId,
    creditorName,
    text,
    debtorsIds,
    debtDate,
    lastChangerId,
    lastChangeDate,
    Status.valueOf(status),
    Currency.getInstance(currency),
    debtId
)

data class Debt(
    val groupId: String,
    val creditorId: String,
    val creditorName: String,
    val text: String,
    val debtorsIds: Map<String, Double>,
    val debtDate: String,
    val lastChangerId: String,
    val lastChangeDate: String,
    val status: Status,
    val currency: Currency,
    val id: String = UUID.randomUUID().toString()
) : Serializable

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
    request["debtId"] = id
    return request
}

enum class Status : Serializable {
    REMOVED,
    ADDED,
    CHANGED,
    NEW
}