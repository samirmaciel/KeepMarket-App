package com.sm.keepmarket.data.datasource.datasourceInterface

import com.sm.keepmarket.data.model.LoginEntity
import com.sm.keepmarket.domain.model.LoginModel
import kotlinx.coroutines.flow.Flow

interface ILoginDatasource {

    suspend fun getCurrentLogin(): Flow<LoginEntity?>
    suspend fun getAllLogin(): Flow<List<LoginEntity>>
    suspend fun insertLogin(loginEntity: LoginEntity)
    suspend fun deleteLogin(loginEntity: LoginEntity)
}