package com.weird.eat_n_fit.ui.order

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.model.food.FoodListAdapter
import com.weird.eat_n_fit.model.packet.DetailPacketAdapter
import com.weird.eat_n_fit.model.packet.PacketListAdapter
import com.weird.eat_n_fit.model.packet.PacketViewModel
import kotlinx.android.synthetic.main.activity_detail_paket.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import okhttp3.internal.notify

class DetailPaketActivity : AppCompatActivity() {
    private var sharedPreferences: SharedPreferences? = null
    val packetViewModel by viewModels<PacketViewModel>()
    lateinit var packetRecycleView : DetailPacketAdapter
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

        val gridRecyclerView = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rc_paket.layoutManager = gridRecyclerView


        packetViewModel.listFood.observe(this, Observer{
            packetRecycleView = DetailPacketAdapter(it)
            rc_paket.adapter = packetRecycleView
        })




    }
}