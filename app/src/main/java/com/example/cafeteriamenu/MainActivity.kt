package com.example.cafeteriamenu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.cafeteriamenu.databinding.ActivityMainBinding
import com.example.cafeteriamenu.retrofit.ApiClient
import com.example.cafeteriamenu.retrofit.ApiService
import com.example.cafeteriamenu.adapter.CustomRecyclerViewAdapter
import com.example.cafeteriamenu.model.Parent
import com.example.cafeteriamenu.util.Utils

class MainActivity : AppCompatActivity() {

    lateinit var apiService: ApiService
    lateinit var adapter: CustomRecyclerViewAdapter
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        apiService = ApiClient.getClient()
            .create(ApiService::class.java) // By that reference retrofit understands which requests will be sent to server
        var request = apiService.getRecipes()

        binding.recyclerRecipies.layoutManager = LinearLayoutManager(this)
        adapter = CustomRecyclerViewAdapter(this)
        binding.recyclerRecipies.adapter = adapter

        Log.d("JSONARRAYPARSE", "Before Request")
        request.enqueue(object : Callback<Parent> {
            override fun onFailure(call: Call<Parent>, t: Throwable) {
                Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_LONG).show()
                Log.d("JSONARRAYPARSE", "Error: " + t.message.toString())
            }

            override fun onResponse(call: Call<Parent>, response: Response<Parent>) {
                Log.d("JSONARRAYPARSE", "Response taken")
                if (response.isSuccessful) {
                    Utils.parent = (response.body() as Parent?)!!
                    Log.d("JSONARRAYPARSE", "Recipes taken from server" + parent.toString())
                    adapter.setData(Utils.parent.recipes!!)
                }
            }
        })
        binding.btnOwner.setOnClickListener {
            val intent = Intent(this, OwnerMainActivity::class.java)
            startActivity(intent)
        }
    }
}