package com.weird.eat_n_fit.ui.home.order

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.weird.eat_n_fit.R
import kotlinx.android.synthetic.main.row_detail_paket.view.*
import kotlinx.android.synthetic.main.row_detail_paket.view.detailFood
import kotlinx.android.synthetic.main.row_item_order_transaction.view.*


class orderListAdapter (private val orderList : MutableList<OrderList>): RecyclerView.Adapter<FoodViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view=
            LayoutInflater.from(parent.context).inflate(R.layout.row_item_order_transaction,parent,false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.nameFood.text = orderList[position].name
        holder.price.text = orderList[position].price
        holder.foodImage.load(orderList[position].imageUrl)

        holder.itemView.orderlist.setOnClickListener {
            Toast.makeText(it.context,"this is toast message", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return orderList.size
    }
}

class FoodViewHolder(v: View) : RecyclerView.ViewHolder(v){
    val nameFood = v.findViewById<TextView>(R.id.tv_id_transaksi)
    val price= v.findViewById<TextView>(R.id.tv_tanggal)
    val foodImage = v.findViewById<ImageView>(R.id.iv_poster_image)
}