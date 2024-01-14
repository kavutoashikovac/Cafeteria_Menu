package com.example.cafeteriamenu.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cafeteriamenu.model.Owner
import com.example.cafeteriamenu.model.Recipe
import com.example.cafeteriamenu.util.Utils

@Database(
    entities = [Owner::class, Recipe::class],
    version = 1,
    exportSchema = false
)
abstract class RecipeRoomDatabase : RoomDatabase() {

    abstract fun ownerDao(): OwnerDAO

    abstract fun recipeDao(): RecipeDAO

    companion object{
        @Volatile  //it makes that instance to visible to other threads
        private var INSTANCE: RecipeRoomDatabase?=null

        fun getDatabase(context: Context): RecipeRoomDatabase {
            val tempInstance = INSTANCE
            if(tempInstance !=null){
                return  tempInstance
            }
            /*
            everthing in this block protected from concurrent execution by multiple threads.In this block database instance is created
            same database instance will be used. If many instance are used, it will be so expensive
             */
            synchronized(this){
                val  instance = Room.databaseBuilder(context.applicationContext, RecipeRoomDatabase::class.java, Utils.DATABASENAME).build()
                INSTANCE = instance
                return instance
            }
        }

    }
}
