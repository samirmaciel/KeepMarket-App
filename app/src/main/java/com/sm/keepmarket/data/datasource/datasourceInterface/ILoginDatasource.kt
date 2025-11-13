package com.sm.keepmarket.data.datasource.datasourceInterface

import com.sm.keepmarket.data.model.UserEntity
import com.sm.keepmarket.data.model.UserLoginEntity
import com.sm.keepmarket.domain.model.LoginModel
import kotlinx.coroutines.flow.Flow

interface ILoginDatasource {

    suspend fun getUserByUID(userUUID: String): Flow<UserEntity?>
    suspend fun makeLogin(userLoginEntity: UserLoginEntity): Flow<UserEntity?>
}