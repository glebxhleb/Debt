package com.copper.debt.firebase.database

import android.util.Log
import com.copper.debt.model.*
import com.google.firebase.database.*
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.toObject
import javax.inject.Inject

private const val KEY_USER = "user"
private const val KEY_DEBT = "debt"
private const val KEY_GROUP = "group"

class FirebaseFirestoreManager @Inject constructor(
    private val database: FirebaseFirestore
) : FirebaseDatabaseInterface {
    override fun listenToDebts(onResult: (Debt) -> Unit) {
        database.collection(KEY_DEBT)
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w(KEY_DEBT, "Listen failed.", e)
                    return@addSnapshotListener
                }

                for (doc in value!!.documentChanges) {

                    when (doc.type) {
                        DocumentChange.Type.ADDED -> {
                            doc.document.toObject<DebtResponse>().run {
                                if (isValid()) {
                                    onResult(mapToDebt(doc.document.id))
                                }
                            }
                        }
                        DocumentChange.Type.MODIFIED -> {

                        }
                        DocumentChange.Type.REMOVED -> {

                        }
                    }
                }
            }
    }

    override fun addNewDebt(debt: Debt, onResult: (Boolean) -> Unit) {

        database.collection(KEY_DEBT)
            .add(debt.mapToRequest())
            .addOnCompleteListener {
                onResult(it.isComplete && it.isSuccessful)
            }
    }

    override fun getMyDebts(userId: String, onResult: (List<Debt>) -> Unit) {

    }

    override fun createUser(id: String, name: String, email: String) {
        val user = User(id, name, email)

        database.collection(KEY_USER)
            .document(id)
            .set(user.mapToRequest())
            .addOnSuccessListener { documentReference ->

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
                // Успешно получили данные. Список в querySnapshot.documents
                val user = querySnapshot.toObject<UserResponse>()
                user?.run {
                    if (isValid())
                    onResult(mapToUser(id)) }
            }
            .addOnFailureListener { exception ->
            }
    }

    override fun getGroup(groupId: String, onResult: (Group) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun addNewGroup(group: Group, onResult: (Boolean) -> Unit) {
        TODO("Not yet implemented")
    }
}

