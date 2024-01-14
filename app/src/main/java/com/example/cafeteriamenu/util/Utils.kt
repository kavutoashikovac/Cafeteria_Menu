package com.example.cafeteriamenu.util

import com.example.cafeteriamenu.model.Parent

object Utils {

    const val TAGFORLAGCAT="WORKERJSONDATABASE"

    const val TABLENAME_RECIPE="recipes"
    const val TABLENAME_OWNER="owner"
    const val DATABASENAME="RecipeDB.db"

    var baseUrl: String = "https://ctis.bilkent.edu.tr/ctis487/recipesnesJSONObjectWithJSONObjectAndJSONArray.php/"
    var baseUrlForImage: String = "https://ctis.bilkent.edu.tr/ctis487/"

    lateinit var parent: Parent

}