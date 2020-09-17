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

class PacketListAdapter(private val packetList: List<Packet>): RecyclerView.Adapter<PacketViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PacketViewHolder {
        val view=
            LayoutInflater.from(parent.context).inflate(R.layout.row_packet,parent,false)
        return PacketViewHolder(view)
    }

    override fun onBindViewHolder(holder: PacketViewHolder, position: Int) {
        holder.namePacket.text = packetList[position].packet_name

        Picasso
            .get()
            .load("http://34.101.198.49:8082/images/${packetList[position].packet_id}.jpg")
            .into(holder.foodImage)
        val activity = holder.itemView.context as Activity
        holder.itemView.setOnClickListener {

            val intent = Intent(activity, DetailPaketActivity::class.java)
            intent.putExtra("idPacket", packetList[position].packet_id)
            intent.putExtra("namaPacket", packetList[position].packet_name)
            activity.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return packetList.size
    }
}
class PacketViewHolder(v: View) :RecyclerView.ViewHolder(v){
    val namePacket = v.findViewById<TextView>(R.id.tv_nama_packet)
    val foodImage = v.findViewById<ImageView>(R.id.packet_image)
}