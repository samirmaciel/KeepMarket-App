package com.sm.keepmarket.data.datasource.firebase

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sm.keepmarket.data.datasource.datasourceInterface.ILoginDatasource
import com.sm.keepmarket.data.model.UserEntity
import com.sm.keepmarket.data.model.UserLoginEntity
import com.sm.keepmarket.domain.model.Constants.USERS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class LoginFirebaseDatasourceImpl(private val auth: FirebaseAuth, private val firestore: FirebaseFirestore) : ILoginDatasource {

    private val TAG = "LoginFirebaseDatasourceImpl"

    override suspend fun getCurrentUser(): Flow<UserEntity?> {
        return flow {
            val currentUser = auth.currentUser
            var userEntity: UserEntity? = null

            currentUser?.let {
                userEntity = getUserByUID(it.uid).firstOrNull()
            }

            emit(userEntity)
        }
    }

    override suspend fun getUserByUID(userUUID: String): Flow<UserEntity?> =
        flow {

            val docRef = firestore.collection(USERS).document(userUUID)
            val documentSnapshot = docRef.get().await()
            val userEntity = documentSnapshot.toObject(UserEntity::class.java)
            emit(userEntity)
        }.catch { e ->
            Log.e(TAG, "Erro ao buscar usuário", e)
            emit(null)
        }

    override suspend fun makeLogin(userLoginEntity: UserLoginEntity): Flow<UserEntity?> = flow {
        val authResult = auth.signInWithEmailAndPassword(
            userLoginEntity.email,
            userLoginEntity.password
        ).await()

        val firebaseUser = authResult.user
        val user = auth.currentUser

        if (user != null) {
            Log.d(TAG, "signInWithEmailAndPassword:success -> ${user.uid}")
            val userEntity = getUserByUID(user.uid).firstOrNull()
            emit(userEntity)
        } else {
            Log.w(TAG, "signInWithEmailAndPassword: user null")
            emit(null)
        }
    }.catch { e ->
        Log.e(TAG, "signInWithEmailAndPassword:failure", e)
        emit(null)
    }

}