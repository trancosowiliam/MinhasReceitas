package com.example.listadereceitas.presentation.recipe

import android.util.Log
import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.listadereceitas.data.db
import com.example.listadereceitas.data.repository.RecipeRepositoryImplementation
import com.example.listadereceitas.domain.model.RecipeDomain
import com.example.listadereceitas.domain.repository.RecipeRepository
import com.example.listadereceitas.domain.usecase.GetAllRecipesUseCase
import com.example.listadereceitas.domain.usecase.InsertRecipeUseCase
import kotlinx.coroutines.launch

class RecipeViewModel(private val getAllRecipesUseCase: GetAllRecipesUseCase,
                      private val insertRecipeUseCase: InsertRecipeUseCase): ViewModel() {

      val state: LiveData<RecipeState> = liveData{
        emit(RecipeState.Loading)
          val state = try {
              val recipes = getAllRecipesUseCase()
              if (recipes.isEmpty()){
                  RecipeState.Empty
              } else{
                  RecipeState.Success(recipes)
              }

          }catch (e: Exception) {
              Log.e("Error", e.message.toString())
              RecipeState.Error(e.message.toString())
          }
          emit(state)
      }

    fun insert(name:String) = viewModelScope.launch {
        insertRecipeUseCase(RecipeDomain(name = name))
    }

    class Factory: ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
            val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])
            val repository = RecipeRepositoryImplementation(application.db.recipeDao())
            return RecipeViewModel(getAllRecipesUseCase = GetAllRecipesUseCase(repository),
                                    insertRecipeUseCase = InsertRecipeUseCase(repository)) as T
        }
    }

}