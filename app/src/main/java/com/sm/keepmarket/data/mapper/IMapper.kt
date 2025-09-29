package com.sm.keepmarket.data.mapper

interface IMapper<MODEL,ENTITY> {

    fun toModel(entity: ENTITY) : MODEL
    fun toEntity(model: MODEL) : ENTITY
}