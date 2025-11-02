package com.sm.keepmarket.data.datasource.datasourceInterface

import com.sm.keepmarket.data.model.UserEntity
import com.sm.keepmarket.data.model.UserRegisterEntity
import kotlinx.coroutines.flow.Flow

interface IUserDatasource {
    suspend fun createNewUser(userUUID: String, userName: String, email: String): Flow<UserEntity?>
    suspend fun getCurrentUser(): Flow<UserEntity?>
    suspend fun getUserByUID(userUUID: String): Flow<UserEntity?>
    suspend fun registerUser(userRegisterEntity: UserRegisterEntity): Flow<UserEntity?>
    suspend fun updateUser(userRegisterEntity: UserRegisterEntity): Flow<UserRegisterEntity?>
    suspend fun deleteUser(userEntity: UserEntity): Flow<UserEntity?>

}