package com.sm.keepmarket.data.repository.repositoryInterface

import com.sm.keepmarket.domain.model.UserModel
import com.sm.keepmarket.domain.model.UserRegisterModel
import kotlinx.coroutines.flow.Flow

interface IUserRepository {

    suspend fun registerUser(userRegisterModel: UserRegisterModel): Flow<UserModel?>
    suspend fun updateUser(userRegisterModel: UserRegisterModel): Flow<UserModel?>
    suspend fun deleteUser(userModel: UserModel): Flow<UserModel?>
}