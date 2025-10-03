package com.sm.keepmarket.data.repository

import com.sm.keepmarket.data.datasource.datasourceInterface.ILoginDatasource
import com.sm.keepmarket.data.mapper.LoginMapper
import com.sm.keepmarket.data.repository.repositoryInterface.ILoginRepository
import com.sm.keepmarket.domain.model.LoginModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class LoginRepositoryImpl(private val loginDatasource: ILoginDatasource): ILoginRepository {

    override suspend fun getCurrentLogin(): Flow<LoginModel?> {
        return flow {
            loginDatasource.getCurrentLogin().collect {
                it?.let {
                    emit(LoginMapper.toModel(it))
                } ?: run {
                    emit(null)
                }
            }
        }
    }

    override suspend fun getValidateLogin(
        login: String,
        password: String
    ): Flow<LoginModel?> {
        return flow {
            val loginEntity = loginDatasource.getLoginByName(login).first()

            loginEntity?.let {
                val loginModel = LoginMapper.toModel(it)

                if(loginModel.password == password){
                    emit(loginModel)
                }else{
                    emit(null)
                }
            } ?: run {
                emit(null)
            }
        }

    }

    override suspend fun getAllLogin(): Flow<List<LoginModel>> {
        return flow {
            loginDatasource.getAllLogin().collect {

                emit(it.map {  LoginMapper.toModel(it) })

            }
        }
    }

    override suspend fun insertLogin(loginModel: LoginModel) {
        loginDatasource.insertLogin(LoginMapper.toEntity(loginModel))
    }

    override suspend fun deleteLogin(loginModel: LoginModel) {
        loginDatasource.deleteLogin(LoginMapper.toEntity(loginModel))
    }
}