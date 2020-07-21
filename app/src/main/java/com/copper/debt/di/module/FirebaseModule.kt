package com.copper.debt.di.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
@Singleton
class FirebaseModule {

    @Provides
    fun firebaseAuth() : FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun firebaseDatabase() : FirebaseDatabase = FirebaseDatabase.getInstance()

    @Provides
    fun firebaseFirestore() : FirebaseFirestore = Firebase.firestore
}