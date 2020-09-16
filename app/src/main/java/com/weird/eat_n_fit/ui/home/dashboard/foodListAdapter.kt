package com.weird.eat_n_fit.ui.home.dashboard

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.makeText
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.ui.home.DashboardFragment
import com.weird.eat_n_fit.ui.home.HomeActivity
import com.weird.eat_n_fit.ui.order.DetailMenuActivity
import kotlinx.android.synthetic.main.row_detail_paket.view.*

class foodListAdapter (private val foodList: MutableList<FoodList>):RecyclerView.Adapter<FoodViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view=
            LayoutInflater.from(parent.context).inflate(R.layout.row_detail_paket,parent,false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.nameFood.text = foodList[position].name
        holder.price.text = foodList[position].price
        holder.foodImage.load(foodList[position].imageUrl)

        holder.itemView.detailFood.setOnClickListener {
            Toast.makeText(it.context,"this is toast message",Toast.LENGTH_SHORT).show()
        }
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