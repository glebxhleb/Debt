package com.copper.debt.di.module

import com.copper.debt.firebase.authentication.FirebaseAuthenticationInterface
import com.copper.debt.firebase.authentication.FirebaseAuthenticationManager
import com.copper.debt.firebase.database.FirebaseDatabaseInterface
import com.copper.debt.firebase.database.FirebaseFirestoreManager
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [FirebaseModule::class])
@Singleton
abstract class InteractionModule {

    @Binds
    abstract fun authentication(authentication: FirebaseAuthenticationManager): FirebaseAuthenticationInterface

    @Binds
    abstract fun database(database: FirebaseFirestoreManager): FirebaseDatabaseInterface
}