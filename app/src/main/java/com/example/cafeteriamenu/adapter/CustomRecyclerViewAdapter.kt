package com.example.cafeteriamenu.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cafeteriamenu.R
import com.example.cafeteriamenu.model.Recipe
import com.example.cafeteriamenu.util.Utils

class CustomRecyclerViewAdapter(private val context: Context): RecyclerView.Adapter<CustomRecyclerViewAdapter.RecyclerViewItemHolder>() {
    private var recyclerItemValues = emptyList<Recipe>()

    fun setData(items:List<Recipe>){
        recyclerItemValues = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerViewItemHolder {
        val inflator = LayoutInflater.from(viewGroup.context)
        val itemView: View = inflator.inflate(R.layout.item_layout, viewGroup, false)
        return RecyclerViewItemHolder(itemView)
    }

    override fun onBindViewHolder(myRecyclerViewItemHolder: RecyclerViewItemHolder, position: Int) {
        val item = recyclerItemValues[position]
        myRecyclerViewItemHolder.tvItemRecipeName.text = item.name.toString()

        var imgUrlAddress = Utils.baseUrlForImage+item.img
        Log.d("IMG URL", imgUrlAddress)

        Glide.with(context)
            .load(imgUrlAddress)
            .override(400)
            .into(myRecyclerViewItemHolder.imgItemRecipe)
    }

    override fun getItemCount(): Int {
        return recyclerItemValues.size
    }

    inner class RecyclerViewItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var itemLayout: LinearLayout
        lateinit var tvItemRecipeName: TextView
        lateinit var btnItemDetail: TextView
        lateinit var imgItemRecipe: ImageView
        init {
            itemLayout = itemView.findViewById(R.id.itemLayout)
            tvItemRecipeName = itemView.findViewById(R.id.tvItemRecipeName)
            btnItemDetail = itemView.findViewById(R.id.btnItemDetail)
            imgItemRecipe = itemView.findViewById(R.id.imgItemRecipe)

            //Event can be added here for each item also, or add event in onBindViewHolder method
            btnItemDetail.setOnClickListener{
                var curentitemPosition= layoutPosition
                Toast.makeText(context, recyclerItemValues[curentitemPosition].toString(), Toast.LENGTH_LONG).show()
            }
        }
    }
}