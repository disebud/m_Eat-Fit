package com.weird.eat_n_fit.model.packet

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.model.food.Food
import com.weird.eat_n_fit.model.food.FoodViewHolder

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
            .load("http://b4e5342d462b.ngrok.io/images/${packetList[position].packet_id}.jpg")
            .into(holder.foodImage)
    }

    override fun getItemCount(): Int {
        return packetList.size
    }
}
class PacketViewHolder(v: View) :RecyclerView.ViewHolder(v){
    val namePacket = v.findViewById<TextView>(R.id.tv_nama_packet)
    val foodImage = v.findViewById<ImageView>(R.id.packet_image)
}