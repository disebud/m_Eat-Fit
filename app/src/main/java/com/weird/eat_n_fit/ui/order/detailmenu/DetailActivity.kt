package com.weird.eat_n_fit.ui.order.detailmenu

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.model.cart.Keranjang
import com.weird.eat_n_fit.model.cart.KeranjangViewModel
import com.weird.eat_n_fit.model.food.Food


class DetailActivity : AppCompatActivity() {

    val cartViewModel by viewModels<KeranjangViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val idFood = intent.getStringExtra("idFood")
        val namaFood = intent.getStringExtra("nameFood")
        val price= intent.getStringExtra("price")
        val desc = intent.getStringExtra("desc")
        val fat=  intent.getStringExtra("fat")
        val proteiin =  intent.getStringExtra("protein")
        val carbo =  intent.getStringExtra("carbo")
        val calories= intent.getStringExtra("calories")
        val portion = intent.getStringExtra("portion")

        cartViewModel.detailFood.add(Food(idFood!!,portion!!,namaFood!!,calories!!,fat!!,carbo!!,proteiin!!,price!!,desc!!))
        println(cartViewModel.detailFood)
    }




}