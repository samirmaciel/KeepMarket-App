package com.sm.keepmarket.data.datasource

import com.sm.keepmarket.data.datasource.datasourceInterface.ILoginDatasource
import com.sm.keepmarket.data.db.dao.LoginDao
import com.sm.keepmarket.data.model.LoginEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime
import java.util.UUID

class LoginDatasourceImpl(private val dao: LoginDao) : ILoginDatasource {

    override suspend fun getCurrentLogin(): Flow<LoginEntity?> {
        return flow {
            emit(LoginEntity(UUID.randomUUID().toString(), "sds", "sdsds", LocalDateTime.now(), true))
        }
    }

    override suspend fun getAllLogin(): Flow<List<LoginEntity>> {
        return flow {
            emit(dao.getAll())
        }
    }

    override suspend fun insertLogin(loginEntity: LoginEntity) {
        dao.insert(loginEntity)
    }

    override suspend fun deleteLogin(loginEntity: LoginEntity) {
        dao.delete(loginEntity)
    }
}