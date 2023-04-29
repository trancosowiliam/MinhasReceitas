package com.example.listadereceitas.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.listadereceitas.data.entity.RecipeEntity


@Dao
interface RecipeDao{

    @Query("SELECT * FROM recipe")
    fun getAllRecipes(): List<RecipeEntity>

    @Insert
    fun insert (recipe: RecipeEntity)


}