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

//    val foodList: MutableList<Food> = mutableListOf(
//        Food(   "Nasi Goreng",
//            "180000","enak sekali",
//            "https://www.resepmasakterbaru.com/wp-content/uploads/2020/01/resep-nasi-goreng-rendang.jpg"),
//        Food( "Mie Goreng",
//            "180000","enak sekali",
//            "https://cdn0-production-images-kly.akamaized.net/pRSX09qL0wIxOc3WDYpw0Oih9iA=/673x379/smart/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/3129172/original/099632200_1589527804-shutterstock_1455941861.jpg"),
//        Food( "Nasi Goreng",
//            "180000","enak sekali",
//            "https://www.resepmasakterbaru.com/wp-content/uploads/2020/01/resep-nasi-goreng-rendang.jpg"),
//        Food(  "Nasi Goreng",
//            "180000","enak sekali",
//            "https://www.maangchi.com/wp-content/uploads/2019/07/haemulkimchibokkeumbap.jpg")
//    )

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