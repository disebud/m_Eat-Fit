package com.weird.eat_n_fit.model.order

class OrderFood( var user_id: String,
                var food_id: String,
                 var porsi: String,
                 var tanggal: String,
                 var waktu: String,
                 var alamat:String,
                ) {
}

class OrderFoodPacket(var user_id: String,
                      var packet_id: String,
                      var portion:String,
                      var  start_date:String,
                      var  start_time:String,
                    var   address:String,
) {
}

class ResponseOrder(var trans_id: String,
                    var trans_date: String,
                    var user_id: String,
                    var packet_id: String,
                    var portion:String,
                    var total_price: String,
                    var start_date: String,
                    var start_time: String,
                    var address: String,
                    var payment_id: String,
                    var order_status:String,
                    var trans_status: String
) {
}
