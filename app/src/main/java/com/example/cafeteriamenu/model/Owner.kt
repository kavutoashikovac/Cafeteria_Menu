package com.example.cafeteriamenu.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.cafeteriamenu.util.Utils
import com.google.gson.annotations.SerializedName


//Model class for database and  JSON parser
@Entity(tableName = Utils.TABLENAME_OWNER)
class Owner (
    @PrimaryKey(autoGenerate = true)
    val id: Int=0,
    @SerializedName("name") //name match with the key of JSON object, GSON according to that name will map the object to data members
    val name: String = "",
    @SerializedName("date")
    val date: String = "") {

    override fun toString(): String {
        return "Recipe\nName: $name\nIngredients=$date\n"
    }
}
