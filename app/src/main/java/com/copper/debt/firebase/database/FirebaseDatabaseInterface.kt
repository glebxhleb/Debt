package com.copper.debt.firebase.database

import com.copper.debt.model.Debt
import com.copper.debt.model.Group
import com.copper.debt.model.User


interface FirebaseDatabaseInterface  {

    fun listenToDebts(onResult: (Debt) -> Unit)

    fun addNewDebt(debt: Debt, onResult: (Boolean) -> Unit)

    fun getMyDebts(userId: String, onResult: (List<Debt>) -> Unit)

    fun createUser(id: String, name: String, email: String)

    fun getProfile(id: String, onResult: (User) -> Unit)

    fun getGroup(groupId: String, onResult: (Group) -> Unit)

    fun addNewGroup(group: Group, onResult: (Boolean) -> Unit)

}