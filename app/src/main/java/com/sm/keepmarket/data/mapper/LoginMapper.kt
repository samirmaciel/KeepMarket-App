package com.sm.keepmarket.data.mapper

import com.sm.keepmarket.data.model.LoginEntity
import com.sm.keepmarket.domain.model.LoginModel

object LoginMapper : IMapper<LoginModel, LoginEntity> {

    override fun toModel(entity: LoginEntity): LoginModel {
        return LoginModel(
            id = entity.id,
            login = entity.login,
            password = entity.password,
            createdDate = entity.createdDate,
            enabled = entity.enabled
        )
    }

    override fun toEntity(model: LoginModel): LoginEntity {
        return LoginEntity(
            id = model.id,
            login = model.login,
            password = model.password,
            createdDate = model.createdDate,
            enabled = model.enabled
        )
    }
}