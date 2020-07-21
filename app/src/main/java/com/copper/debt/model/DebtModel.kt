package com.copper.debt.model

data class DebtResponse(
    val id: String = "",
    val groupId: String = "",
    val creditorId: String = "",
    val text: String = "",
    val debtorsIds: Map<String, Double> = mapOf(),
    val debtDate: String = "",
    val lastChangerId: String = "",
    val lastChangeDate: String = "",
    val status: String = ""
)

fun DebtResponse.isValid() = groupId.isNotBlank()
            && creditorId.isNotBlank()
            && text.isNotBlank()
            && debtorsIds.isNotEmpty()
            && debtDate.isNotBlank()
            && lastChangerId.isNotBlank()
            && lastChangeDate.isNotBlank()
            && status.isNotBlank()

fun DebtResponse.mapToDebt(firebaseId: String) = Debt(
    firebaseId,
    groupId,
    creditorId,
    text,
    debtorsIds,
    debtDate,
    lastChangerId,
    lastChangeDate,
    Status.valueOf(status)
)

data class Debt(
    val id: String,
    val groupId: String,
    val creditorId: String,
    val text: String,
    val debtorsIds: Map<String, Double>,
    val debtDate: String,
    val lastChangerId: String,
    val lastChangeDate: String,
    val status: Status
)

fun Debt.mapToRequest(): HashMap<String, Any> {
    val request = HashMap<String, Any>()
    request["groupId"] = groupId
    request["creditorId"] = creditorId
    request["text"] = text
    request["debtorsIds"] = debtorsIds
    request["debtDate"] = debtDate
    request["lastChangerId"] = lastChangerId
    request["lastChangeDate"] = lastChangeDate
    request["status"] = status.toString()
    return request
}

enum class Status {
    REMOVED,
    ADDED,
    CHANGED,
    NEW
}