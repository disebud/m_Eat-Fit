package com.weird.eat_n_fit.model.packet

import com.weird.eat_n_fit.model.food.Food

class Packet(   var packet_id: String,
                var packet_name: String,
                var packet_price:String,
                var packet_desc:String,
                var packet_status:String
) {

}

class WrapperDetail ( var packet:Packet,
var foodList:List<Food>){

}