package com.example.listadereceitas.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.listadereceitas.data.dao.RecipeDao
import com.example.listadereceitas.data.entity.RecipeEntity

@Database(
    entities = [RecipeEntity::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun recipeDao(): RecipeDao


}