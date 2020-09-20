package com.weird.eat_n_fit.model.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.weird.eat_n_fit.model.food.Food
import javax.inject.Inject

class KeranjangViewModel  @Inject constructor (): ViewModel() {
    val cartList = mutableListOf<Keranjang>()
    val cartLiveData: MutableLiveData<MutableList<Keranjang>> = MutableLiveData(cartList)
    val detail = mutableListOf<Keranjang>()
    val detailFood = mutableListOf<Food>()


    fun addCartList(qty:String,productID:String,productName:String,userID:String, price:String,note:String){
        println("dari add model cart "+qty+productID+productName+userID+price+note)
        cartList.add(
            Keranjang(
                qty,
                productID,
                productName,
                userID,
                price,
                note
            )
        )

        cartLiveData.value = cartList

        println("view model keranjang "+cartLiveData)
    }

    fun detailList(qty:String,productID:String,productName:String,userID:String, price:String,note:String){
        println("dari add model cart "+qty+productID+productName+userID+price+note)
        detail.add(
            Keranjang(
                qty,
                productID,
                productName,
                userID,
                price,
                note
            )
        )

        cartLiveData.value = cartList

        println("view model keranjang "+cartLiveData)
    }
    fun removeCart(position: Int){
        cartList.removeAt(position)
        cartLiveData.value = cartList
    }

}