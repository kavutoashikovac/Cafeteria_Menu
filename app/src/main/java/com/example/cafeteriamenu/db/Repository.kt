package com.example.cafeteriamenu.db

import androidx.lifecycle.LiveData
import com.example.cafeteriamenu.model.Owner
import com.example.cafeteriamenu.model.Recipe

/*
Used to access multiple data sources. It is used to seperate code and the architecture
*/
class Repository(private val oDao: OwnerDAO, private val rDao: RecipeDAO) {
    val readAlldataOwner: LiveData<List<Owner>> = oDao.getAllOwners()
    val readAlldataRecipe: LiveData<List<Recipe>> = rDao.getAllRecipes()
    fun insertOwner(customer: Owner){
        oDao.insertOwner(customer)
    }
    fun updateOwner(customer: Owner){
        oDao.updateOwner(customer)
    }
    fun deleteOwner(customer: Owner){
        oDao.deleteOwner(customer)
    }
    fun getAllOwner(): LiveData<List<Owner>> {
        return oDao.getAllOwners()
    }
    /******************************/
    fun insertRecipe(recipe: Recipe){
        rDao.insertRecipe(recipe)
    }
    fun updateRecipe(recipe: Recipe){
        rDao.updateRecipe(recipe)
    }
    fun deleteRecipe(recipe: Recipe){
        rDao.deleteRecipe(recipe)
    }
    fun getAllRecipes(): LiveData<List<Recipe>> {
        return rDao.getAllRecipes()
    }
}