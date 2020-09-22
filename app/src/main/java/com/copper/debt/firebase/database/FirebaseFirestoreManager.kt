package com.copper.debt.firebase.database

import android.util.Log
import com.copper.debt.model.*
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.firestore.ktx.toObjects
import kotlinx.coroutines.tasks.await
import java.lang.Exception
import java.util.*
import javax.inject.Inject

private const val KEY_USER = "user"
private const val KEY_DEBT = "debt"
private const val KEY_GROUP = "group"

class FirebaseFirestoreManager @Inject constructor(
    private val database: FirebaseFirestore
) : FirebaseDatabaseInterface {

    override fun listenToDebts(onResult: (Debt, Status) -> Unit) {
        database.collection(KEY_DEBT)
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w(KEY_DEBT, "Listen failed.", e)
                    return@addSnapshotListener
                }

                for (doc in value!!.documentChanges) {

                    val status = when (doc.type) {
                        DocumentChange.Type.ADDED -> Status.ADDED
                        DocumentChange.Type.MODIFIED -> Status.CHANGED
                        DocumentChange.Type.REMOVED -> Status.REMOVED
                    }

                    doc.document.toObject<DebtResponse>().run {
                        if (isValid()) {
                            onResult(mapToDebt(), status)
                        }
                    }
                }
            }
    }

    override fun addNewDebt(debt: Debt, onResult: (Boolean) -> Unit) {
        database.collection(KEY_DEBT)
            .document(debt.id)
            .set(debt.mapToRequest())
            .addOnCompleteListener {
                onResult(it.isComplete && it.isSuccessful)
            }
    }

    override fun getMyDebts(userId: String, onResult: (List<Debt>) -> Unit) {

    }

    override fun createUser(id: String, name: String, email: String, onResult: () -> Unit) {
        val user = User(name, email, listOf(), id)

        database.collection(KEY_USER)
            .document(id)
            .set(user.mapToRequest())
            .addOnSuccessListener {
                onResult()
            }
            .addOnFailureListener { e ->
                Log.w(KEY_DEBT, "Error adding document", e)
            }
    }

    override fun getProfile(id: String, onResult: (User) -> Unit) {

        database.collection(KEY_USER)
            .document(id)
            .get()
            .addOnSuccessListener { querySnapshot ->

                val user = querySnapshot.toObject<UserResponse>()
                user?.run {
                    if (isValid())
                        onResult(mapToUser())
                }
            }
            .addOnFailureListener {
            }
    }

    override suspend fun getProfile(id: String): User? {
        return try {
            val data = database.collection(KEY_USER)
                .document(id)
                .get()
                .await()

            data.toObject<UserResponse>()?.run {
                if (isValid())
                    mapToUser()
                else
                    null
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getProfiles(ids: List<String>): List<User> {
        return try {
            val data = database.collection(KEY_USER)
                .whereIn("id", ids)
                .get()
                .await()

            data.toObjects<UserResponse>()
                .filter(UserResponse::isValid)
                .map(UserResponse::mapToUser)
        } catch (e: Exception) {
            listOf()
        }
    }

    override suspend fun getUserGroups(userId: String): List<Group> {
        return try {
            val data = database.collection(KEY_GROUP)
                .whereArrayContains("usersIds", userId)
                .get()
                .await()

            data.toObjects<GroupResponse>()
                .filter(GroupResponse::isValid)
                .map(GroupResponse::mapToGroup)
        } catch (e: Exception) {
            listOf()
        }
    }

    override fun getGroup(groupId: String, onResult: (Group) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun addNewGroup(group: Group, onResult: (Boolean) -> Unit) {
        TODO("Not yet implemented")
    }
}

