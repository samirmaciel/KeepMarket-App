package com.sm.keepmarket.data.repository

import com.sm.keepmarket.data.datasource.datasourceInterface.ILoginDatasource
import com.sm.keepmarket.data.model.toUserModel
import com.sm.keepmarket.data.repository.repositoryInterface.ILoginRepository
import com.sm.keepmarket.domain.model.UserLoginModel
import com.sm.keepmarket.domain.model.UserModel
import com.sm.keepmarket.domain.model.toUserLoginEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginRepositoryImpl(private val loginDataSource: ILoginDatasource) : ILoginRepository {

    override suspend fun getCurrentUser(): Flow<UserModel?> = flow {
        loginDataSource.getCurrentUser().collect {
            emit(it?.toUserModel())
        }
    }

    override suspend fun makeLogin(userLoginModel: UserLoginModel): Flow<UserModel?> = flow {
        loginDataSource.makeLogin(userLoginModel.toUserLoginEntity()).collect {
            emit(it?.toUserModel())
        }
    }
}