package com.example.listadereceitas.domain.repository

import com.example.listadereceitas.domain.model.RecipeDomain


interface RecipeRepository {

    suspend fun getAll(): List<RecipeDomain>

    suspend fun insert(recipe: RecipeDomain)

}