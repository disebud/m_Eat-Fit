package com.weird.eat_n_fit.model.packet
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.model.food.Food
import com.weird.eat_n_fit.model.food.FoodViewHolder
import com.weird.eat_n_fit.ui.order.DetailMenuActivity
import com.weird.eat_n_fit.ui.order.DetailPaketActivity
import kotlinx.android.synthetic.main.row_packet.view.*
import java.text.NumberFormat
import java.util.*

class DetailPacketAdapter(private val listFood: List<Food>): RecyclerView.Adapter<DetailPacketAdapterHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailPacketAdapterHolder {
        val view=
            LayoutInflater.from(parent.context).inflate(R.layout.row_detail_paket,parent,false)
        return DetailPacketAdapterHolder(view)
    }

    override fun onBindViewHolder(holder: DetailPacketAdapterHolder, position: Int) {
        println("detail adapter")
        println("${listFood[position].food_name}")
        holder.nameFood.text = listFood[position].food_name
        // holder.price.text = foodList[position].food_price
        currency(listFood[position].food_price!!.toDouble(), holder.price)
        Picasso
            .get()
            .load("http://34.101.198.49:8082/images/${listFood[position].food_id}.jpg")
            .into(holder.foodImage)
    }
    fun currency(harga:Double, textView: TextView) {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        textView.setText(formatRupiah.format(harga as Double))
    }
    override fun getItemCount(): Int {
       return listFood.size
    }

}
class DetailPacketAdapterHolder(v: View) :RecyclerView.ViewHolder(v){
    val nameFood = v.findViewById<TextView>(R.id.tv_nama_menu)
    val price= v.findViewById<TextView>(R.id.tv_idr)
    val foodImage = v.findViewById<ImageView>(R.id.iv_poster_image)
}