package com.example.listadereceitas.presentation.recipe

import com.example.listadereceitas.domain.model.RecipeDomain

sealed interface RecipeState {
    object Loading: RecipeState
    object Empty: RecipeState
    data class Success(val recipe: List<RecipeDomain>): RecipeState
    data class Error (val message: String): RecipeState
}