package com.weird.eat_n_fit.ui.order

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.icu.number.Precision.currency
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.squareup.picasso.Picasso
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.model.food.FoodListAdapter
import com.weird.eat_n_fit.model.packet.DetailPacketAdapter
import com.weird.eat_n_fit.model.packet.Packet
import com.weird.eat_n_fit.model.packet.PacketListAdapter
import com.weird.eat_n_fit.model.packet.PacketViewModel
import kotlinx.android.synthetic.main.activity_detail_paket.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import okhttp3.internal.notify
import java.text.NumberFormat
import java.util.*

class DetailPaketActivity : AppCompatActivity() {
    private var sharedPreferences: SharedPreferences? = null
    val packetViewModel by viewModels<PacketViewModel>()
    lateinit var packetRecycleView : DetailPacketAdapter
    var price =""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_paket)
        sharedPreferences = getSharedPreferences(
            getString(R.string.shared_preferences_name),
            Context.MODE_PRIVATE
        )
        val token = sharedPreferences?.getString(getString(R.string.auth_token), "")
        val idPacket = intent.getStringExtra("idPacket")
        val namaPacket = intent.getStringExtra("namaPacket")
        title_name_paket.text= namaPacket

        packetViewModel.getPacketByID(token!!,idPacket!!)

        packetViewModel.detailPacket.observe(this, {
            Picasso.get().load("${getString(R.string.image_link)}${idPacket}.jpg").into(iv_poster_image_detail_packet)
            desc_packet.text = it.packet_desc
            porsi_packet.text = "/ ${it.packet_portion} Porsi"
            currency(it.packet_price!!.toDouble(), idr_packet_detail)
            price=it.packet_price
        })

        val gridRecyclerView = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rc_paket.layoutManager = gridRecyclerView

        println("data food ${packetViewModel.listFood}")
        packetViewModel.listFood.observe(this, Observer{
            packetRecycleView = DetailPacketAdapter(it)
            rc_paket.adapter = packetRecycleView
        })

        order_packet.setOnClickListener {
            val intent = Intent(this, NextOrderActivity::class.java)
            intent.putExtra("idPacket",idPacket).putExtra("price",price).putExtra("namaPacket",namaPacket)
            overridePendingTransition( R.anim.slide_in_right,R.anim.slide_out_left);
            startActivity(intent)
        }

    }


    fun currency(harga:Double, textView: TextView) {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        textView.setText( formatRupiah.format(harga as Double))
    }
}