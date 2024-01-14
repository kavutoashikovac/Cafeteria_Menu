package com.example.cafeteriamenu.retrofit

import com.example.cafeteriamenu.model.Parent
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("posts")
    fun getRecipes(): Call<Parent>
}