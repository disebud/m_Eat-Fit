package com.weird.eat_n_fit.model.packet

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.weird.eat_n_fit.model.food.Food
import com.weird.eat_n_fit.model.food.FoodAPI
import com.weird.eat_n_fit.model.user.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PacketRepository(private val packetAPI: PacketAPI) {
    val packet: MutableLiveData<List<Packet>> = MutableLiveData<List<Packet>>()
    val packetById: MutableLiveData<WrapperDetail> = MutableLiveData<WrapperDetail>()
    val detailPaket: MutableLiveData<Packet> = MutableLiveData<Packet>()
    val listFood: MutableLiveData<List<Food>> = MutableLiveData<List<Food>>()

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
    fun getPacketByID(token:String, id: String) {
        packetAPI.getPacketByID(token, id).enqueue(object : Callback<WrapperDetail> {
            override fun onResponse(call: Call<WrapperDetail>, response: Response<WrapperDetail>) {
                println(response.body())
                if (response.isSuccessful) {

                    val res= response.body()
                    detailPaket.value=res?.packet
                    listFood.value=res?.foodList

                }
            }

            override fun onFailure(call: Call<WrapperDetail>, t: Throwable) {
                t.printStackTrace()
            }

        })
    }
}