package com.weird.eat_n_fit.model.food

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.squareup.picasso.Picasso
import com.weird.eat_n_fit.R

class FoodListAdapter (private val foodList: List<Food>):RecyclerView.Adapter<FoodViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view=
            LayoutInflater.from(parent.context).inflate(R.layout.row_detail_paket,parent,false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.nameFood.text = foodList[position].food_name
        holder.price.text = foodList[position].food_price
        Picasso
            .get()
            .load("http://b4e5342d462b.ngrok.io/images/${foodList[position].food_id}.jpg")
            .into(holder.foodImage)
    }

    override fun getItemCount(): Int {
        return foodList.size
    }
}

class FoodViewHolder(v: View) :RecyclerView.ViewHolder(v){
    val nameFood = v.findViewById<TextView>(R.id.tv_nama_menu)
    val price= v.findViewById<TextView>(R.id.tv_idr)
    val foodImage = v.findViewById<ImageView>(R.id.iv_poster_image)
}