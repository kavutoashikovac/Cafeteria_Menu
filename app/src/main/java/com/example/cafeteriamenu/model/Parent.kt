package com.example.cafeteriamenu.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Parent (
    @SerializedName("owner")
    @Expose
    var owner: Owner,
    @SerializedName("recipes")
    var recipes: List<Recipe>?)
{
    override fun toString(): String {
        return "${owner.toString()} ${recipes.toString()}\n"
    }
}