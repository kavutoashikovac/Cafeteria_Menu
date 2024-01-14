package com.example.cafeteriamenu.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cafeteriamenu.model.Owner
import com.example.cafeteriamenu.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
it provides data to the UI and survive configuration changes. It acts as a communication center between repository and the UI
 */
class DbViewModel(application: Application): AndroidViewModel(application) {
    val readAllDataOwner: LiveData<List<Owner>>
    val readAllDataRecipe: LiveData<List<Recipe>>
    private val repository:Repository
    init {
        val recipeRoomDatabase = RecipeRoomDatabase.getDatabase(application)
        val ownerDAO= recipeRoomDatabase.ownerDao()
        val recipeDAO= recipeRoomDatabase.recipeDao()

        repository = Repository(ownerDAO, recipeDAO)

        readAllDataOwner = repository.readAlldataOwner
        readAllDataRecipe = repository.readAlldataRecipe
    }
    fun addOwner(owner: Owner){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.insertOwner(owner)
        }
    }
    fun addRecipe(recipe: Recipe){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.insertRecipe(recipe)
        }
    }
    fun deleteOwner(customer: Owner){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.deleteOwner(customer)
        }
    }

    fun updateOwner(owner: Owner){
        viewModelScope.launch(Dispatchers.IO){ // that code will be run in background thread, coroutine scope
            repository.updateOwner(owner)
        }
    }
}