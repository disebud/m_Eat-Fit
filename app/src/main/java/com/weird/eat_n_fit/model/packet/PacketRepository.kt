package com.weird.eat_n_fit.model.packet

import androidx.lifecycle.MutableLiveData
import com.weird.eat_n_fit.model.food.Food
import com.weird.eat_n_fit.model.food.FoodAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PacketRepository(private val packetAPI: PacketAPI) {
    val packet: MutableLiveData<List<Packet>> = MutableLiveData<List<Packet>>()

    fun getAllPacket(token: String, page: String, limit: String, keyword: String) {
        packetAPI.getAllPacket(token, page, limit, keyword).enqueue(object : Callback<List<Packet>> {
            override fun onResponse(call: Call<List<Packet>>, response: Response<List<Packet>>) {
                packet.value = response.body()
            }

            override fun onFailure(call: Call<List<Packet>>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}