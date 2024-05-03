package com.example.jetnews.di

import com.example.jetnews.auth.AuthRepository
import com.example.jetnews.auth.AuthRepositoryImpl
import com.example.jetnews.respository.video.VideoRepository
import com.example.jetnews.respository.video.VideoRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesRepositoryImpl(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun providesFirestore() = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun providesVideoRepositoryImpl(firebaseFirestore: FirebaseFirestore): VideoRepository {
        return VideoRepositoryImpl(firebaseFirestore)
    }
}