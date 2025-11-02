package com.sm.keepmarket.data.datasource.firebase

import android.util.Log
import androidx.collection.emptyIntSet
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.sm.keepmarket.data.datasource.datasourceInterface.IUserDatasource
import com.sm.keepmarket.data.model.UserEntity
import com.sm.keepmarket.data.model.UserRegisterEntity
import com.sm.keepmarket.domain.model.Constants.USERS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

class UserFirebaseDatasourceImpl(
    private val auth: FirebaseAuth,
    private val firestore: FirebaseFirestore
) : IUserDatasource {

    private val TAG = "UserFirebaseDatasourceImpl"

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

    override suspend fun createNewUser(
        userUUID: String,
        userName: String,
        email: String
    ): Flow<UserEntity?> =
        flow {
            val userEntity = UserEntity(userUUID, userName, email)
            val docRef = firestore.collection(USERS).document(userUUID)
            docRef.set(userEntity).await()

            emit(userEntity)
        }.catch { e ->
            Log.e(TAG, "Erro ao criar usuário", e)
        }

    override suspend fun registerUser(userRegisterEntity: UserRegisterEntity): Flow<UserEntity?> =
        flow {
            val authResult = auth.createUserWithEmailAndPassword(
                userRegisterEntity.email,
                userRegisterEntity.password
            ).await()

            val firebaseUser = authResult.user

            if (firebaseUser != null) {
                Log.d(TAG, "createUserWithEmail:success -> ${firebaseUser.uid}")
                val userEntity = createNewUser(
                    firebaseUser.uid,
                    userRegisterEntity.name,
                    userRegisterEntity.email
                ).firstOrNull()

                emit(userEntity)
            } else {
                Log.w(TAG, "createUserWithEmail: user null")
                emit(null)
            }
        }.catch { e ->
            Log.e(TAG, "createUserWithEmail:failure", e)
            emit(null)
        }


    override suspend fun updateUser(userRegisterEntity: UserRegisterEntity): Flow<UserRegisterEntity?> {
        return flow {
            emit(null)
        }
    }

    override suspend fun deleteUser(userEntity: UserEntity): Flow<UserEntity?> {
        return flow {
            emit(null)
        }
    }

    override suspend fun signOut(): Flow<Boolean> = flow {
        auth.signOut()
        emit(true)
    }.catch { e ->
        Log.e(TAG, "Erro ao sair da conta", e)
        emit(false)
    }

    override suspend fun deleteAccount(): Flow<Boolean> =
        flow {
            val user = auth.currentUser ?: throw IllegalStateException("Usuário não autenticado")

            user.delete().await()

            emit(true)
        }.catch { e ->
            Log.e(TAG, "Erro ao deletar conta", e)
            emit(false)
        }
}