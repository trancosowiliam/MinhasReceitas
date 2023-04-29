package com.example.listadereceitas.data.mapper

import com.example.listadereceitas.data.entity.RecipeEntity
import com.example.listadereceitas.domain.model.RecipeDomain


fun RecipeDomain.toEntity() = RecipeEntity(
    id = id,
    name = name
)

fun RecipeEntity.toDomain() = RecipeDomain(
    id = id,
    name = name
)