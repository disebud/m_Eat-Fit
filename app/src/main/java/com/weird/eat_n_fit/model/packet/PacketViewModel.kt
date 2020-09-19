package com.weird.eat_n_fit.model.packet

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.weird.eat_n_fit.config.RetrofitBuilder
import com.weird.eat_n_fit.model.food.Food
import com.weird.eat_n_fit.model.food.FoodAPI
import com.weird.eat_n_fit.model.food.FoodRepository
import java.sql.Wrapper

class PacketViewModel:ViewModel() {
    private val packetRepo :PacketRepository

    init {
        val packetAPI = RetrofitBuilder.createRetrofit().create(PacketAPI::class.java)
        packetRepo = PacketRepository(packetAPI)
    }

    val PacketLiveData: MutableLiveData<List<Packet>> = packetRepo.packet
    val DetailPacketLiveData: MutableLiveData<WrapperDetail> = packetRepo.packetById
    val detailPacket : MutableLiveData<Packet> = packetRepo.detailPaket
    val listFood : MutableLiveData<List<Food> > = packetRepo.listFood
    fun getAllPacket(token: String, page: String, limit: String, keyword: String) {
        val authToken = "Bearer $token"
        println(authToken)
        packetRepo.getAllPacket(authToken, page, limit, keyword)
    }
    fun getPacketByID(token: String, id: String) {
        val authToken = "Bearer $token"
        packetRepo.getPacketByID(authToken, id)
    }
}