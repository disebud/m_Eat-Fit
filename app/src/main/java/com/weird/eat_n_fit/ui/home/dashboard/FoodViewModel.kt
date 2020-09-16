package com.weird.eat_n_fit.ui.home.dashboard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FoodViewModel : ViewModel() {
    val foodList = mutableListOf<FoodList>(
        FoodList(   "Nasi Goreng",
            "180000","enak sekali",
            "https://www.resepmasakterbaru.com/wp-content/uploads/2020/01/resep-nasi-goreng-rendang.jpg"),
        FoodList( "Mie Goreng",
            "180000","enak sekali",
            "https://cdn0-production-images-kly.akamaized.net/pRSX09qL0wIxOc3WDYpw0Oih9iA=/673x379/smart/filters:quality(75):strip_icc():format(jpeg)/kly-media-production/medias/3129172/original/099632200_1589527804-shutterstock_1455941861.jpg"),
        FoodList( "Nasi Goreng",
            "180000","enak sekali",
            "https://www.resepmasakterbaru.com/wp-content/uploads/2020/01/resep-nasi-goreng-rendang.jpg"),
        FoodList(  "Nasi Goreng",
            "180000","enak sekali",
            "https://www.maangchi.com/wp-content/uploads/2019/07/haemulkimchibokkeumbap.jpg")
    )

    val foodLiveData: MutableLiveData<MutableList<FoodList>> = MutableLiveData(foodList)


}
class FoodList(name: String, price: String, desc: String,image:String) {

    var name = name
    var price = price
    var desc = desc
    var imageUrl = image

}
