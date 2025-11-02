package com.sm.keepmarket.data.repository

import com.sm.keepmarket.data.datasource.datasourceInterface.IUserDatasource
import com.sm.keepmarket.data.model.toUserModel
import com.sm.keepmarket.data.repository.repositoryInterface.IUserRepository
import com.sm.keepmarket.domain.model.UserModel
import com.sm.keepmarket.domain.model.UserRegisterModel
import com.sm.keepmarket.domain.model.toUserRegisterEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserRepositoryImpl(private val datasource: IUserDatasource): IUserRepository {

    override suspend fun registerUser(userRegisterModel: UserRegisterModel): Flow<UserModel?> = flow {
        datasource.registerUser(userRegisterModel.toUserRegisterEntity()).collect {
            emit(it?.toUserModel())
        }
    }

    override suspend fun getCurrentUser(): Flow<UserModel?> = flow {
        datasource.getCurrentUser().collect {
            emit(it?.toUserModel())
        }
    }

    override suspend fun updateUser(userRegisterModel: UserRegisterModel): Flow<UserModel?> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser(userModel: UserModel): Flow<UserModel?> {
        TODO("Not yet implemented")
    }

    override suspend fun signOut(): Flow<Boolean> = flow {
        datasource.signOut().collect {
            emit(it)
        }
    }

    override suspend fun deleteAccount(): Flow<Boolean> = flow {
        datasource.deleteAccount().collect {
            emit(it)
        }
    }


}