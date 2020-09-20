package com.weird.eat_n_fit.model.order

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.model.packet.Packet
import com.weird.eat_n_fit.model.packet.PacketViewHolder
import com.weird.eat_n_fit.ui.order.DetailPaketActivity
import com.weird.eat_n_fit.ui.order.DetailTransactionActivity

class TransactionRecycleView(private val transList: List<TransactionUserList>): RecyclerView.Adapter<TransactionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val view=
            LayoutInflater.from(parent.context).inflate(R.layout.row_item_order_transaction,parent,false)
        return TransactionViewHolder(view)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.transId.text = transList[position].trans_id
        holder.tanggal.text = transList[position].trans_date

        Picasso
            .get()
            .load("http://34.101.198.49:8082/images/${transList[position].packet_id}.jpg")
            .into(holder.Image)
        val activity = holder.itemView.context as Activity
        holder.itemView.setOnClickListener {
            val intent = Intent(activity, DetailTransactionActivity::class.java)
            intent.putExtra("idTras", transList[position].trans_id)
            intent.putExtra("idPacket", transList[position].packet_id)
            intent.putExtra("tanggal", transList[position].start_date)
            intent.putExtra("waktu", transList[position].start_time)
            intent.putExtra("price", transList[position].total_price)
            intent.putExtra("porsi", transList[position].portion)
            intent.putExtra("alamat", transList[position].address)
            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
       return transList.size
    }
}

class TransactionViewHolder(v: View) :RecyclerView.ViewHolder(v){
    val transId = v.findViewById<TextView>(R.id.idtrans)
    val Image = v.findViewById<ImageView>(R.id.iv_poster_image)
    val tanggal = v.findViewById<TextView>(R.id.date)
}