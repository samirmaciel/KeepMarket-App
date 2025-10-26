package com.sm.keepmarket.data.repository.repositoryInterface

import com.sm.keepmarket.data.model.UserEntity
import com.sm.keepmarket.data.model.UserLoginEntity
import com.sm.keepmarket.domain.model.LoginModel
import com.sm.keepmarket.domain.model.UserLoginModel
import com.sm.keepmarket.domain.model.UserModel
import kotlinx.coroutines.flow.Flow

interface ILoginRepository {

    suspend fun getCurrentUser(): Flow<UserModel?>
    suspend fun makeLogin(userLoginModel: UserLoginModel): Flow<UserModel?>
}