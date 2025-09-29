package com.sm.keepmarket.data.repository.repositoryInterface

import com.sm.keepmarket.domain.model.LoginModel
import kotlinx.coroutines.flow.Flow

interface ILoginRepository {

    suspend fun getCurrentLogin(): Flow<LoginModel?>
    suspend fun getAllLogin(): Flow<List<LoginModel>>
    suspend fun insertLogin(loginModel: LoginModel)
    suspend fun deleteLogin(loginModel: LoginModel)
}