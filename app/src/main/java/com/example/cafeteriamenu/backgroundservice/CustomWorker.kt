package com.example.cafeteriamenu.backgroundservice

import android.content.Context
import android.util.Log
import androidx.work.Data
import androidx.work.ListenableWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.cafeteriamenu.db.RecipeRoomDatabase
import com.example.cafeteriamenu.db.Repository
import com.example.cafeteriamenu.model.Owner
import com.example.cafeteriamenu.util.Utils

class CustomWorker(var context: Context, var workerParams: WorkerParameters, ):
    Worker(context, workerParams){
    override fun doWork(): ListenableWorker.Result {

        /* context = applicationContext */
        val name: String = getInputData().getString("ownerName").toString()
        val date: String = getInputData().getString("ownerDate").toString()

        val recipeRoomDatabase = RecipeRoomDatabase.getDatabase(context)
        val ownerDAO= recipeRoomDatabase.ownerDao() //to manage owner table
        val recipeDAO= recipeRoomDatabase.recipeDao() //to manage recipe table

        val repository = Repository(ownerDAO, recipeDAO)

        return try {
            Log.d(Utils.TAGFORLAGCAT, "doWork Called, inputs: $name $date")

            repository.insertOwner(Owner(0, name, date))
            //create the output of worker
            val outputData = Data.Builder().putString("result", "Owner inserted").build()
            Log.d(Utils.TAGFORLAGCAT, "End of worker")
            ListenableWorker.Result.success(outputData) //this will return
        } catch (throwable: Throwable) {
            Log.d(Utils.TAGFORLAGCAT, "Error Sending Notification" + throwable.message)
            ListenableWorker.Result.failure() //this will return
        }
    }
}