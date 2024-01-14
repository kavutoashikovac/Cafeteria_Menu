package com.example.cafeteriamenu.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cafeteriamenu.util.Utils
import com.google.gson.annotations.SerializedName

//Model class created according to JSON object and Table
@Entity(tableName = Utils.TABLENAME_RECIPE)
class Recipe (
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    @SerializedName("name") //name match with the key of JSON object, GSON according to that name will map the object to data members
    val name: String = "",
    @SerializedName("ingredients")
    val ingredients: String = "",
    @SerializedName("details")
    val details: String = "",
    @SerializedName("img")
    val img: String = "")
{
    override fun toString(): String {
        return "Recipe\nName: $name\nIngredients=$ingredients\nDetails='$details\n"
    }
}