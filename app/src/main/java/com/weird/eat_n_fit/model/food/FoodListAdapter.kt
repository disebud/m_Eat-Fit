package com.weird.eat_n_fit.model.food

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.weird.eat_n_fit.R
import java.text.NumberFormat
import java.util.*

class FoodListAdapter (private val foodList: List<Food>):RecyclerView.Adapter<FoodViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view=
            LayoutInflater.from(parent.context).inflate(R.layout.row_food,parent,false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.nameFood.text = foodList[position].food_name
       // holder.price.text = foodList[position].food_price
        currency(foodList[position].food_price!!.toDouble(), holder.price)
        Picasso
            .get()
            .load("http://34.101.198.49:8082/images/${foodList[position].food_id}.jpg")
            .into(holder.foodImage)
    }
    fun currency(harga:Double, textView: TextView) {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        textView.setText(formatRupiah.format(harga as Double))
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