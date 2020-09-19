package com.weird.eat_n_fit.ui.order

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.squareup.picasso.Picasso
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.model.cart.KeranjangViewModel
import com.weird.eat_n_fit.model.food.FoodViewModel
import com.weird.eat_n_fit.ui.keranjang.ListCartActivity
import kotlinx.android.synthetic.main.activity_detail_menu.*

class DetailMenuActivity : AppCompatActivity() {
    private var sharedPreferences: SharedPreferences? = null
    val foodViewModel by viewModels<FoodViewModel>()
    val cartViewModel by viewModels<KeranjangViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_menu)
        sharedPreferences = getSharedPreferences(
            getString(R.string.shared_preferences_name),
            Context.MODE_PRIVATE
        )

        val idFood = intent.getStringExtra("idFood")
        val namaFood = intent.getStringExtra("nameFood")
       val price= intent.getStringExtra("price")
        val desc = intent.getStringExtra("desc")
        val fat=  intent.getStringExtra("fat")
        val proteiin =  intent.getStringExtra("protein")
        val carbo =  intent.getStringExtra("carbo")
        val calories= intent.getStringExtra("calories")
        val portion = intent.getStringExtra("portion")
        tv_nama_menu_selected.text= namaFood
        tv_idr_menu_selected.text=price
        tv_porsi_menu_selected.text= "porsi ${portion}"
        kalori.text=calories
        karbohidrat.text=carbo
        protein.text=proteiin
        lemak.text=fat
        Picasso
            .get()
            .load("http://34.101.198.49:8082/images/${idFood}.jpg")
            .into(iv_poster_image)

        btn_order_menu.setOnClickListener {
            val intent = Intent(this,NextOrderActivity::class.java)
            intent.putExtra("idFood",idFood)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            startActivity(intent)

        }

        cartButton.setOnClickListener{
            cartViewModel.addCartList("1",idFood!!,namaFood!!,"12",price!!,desc!! )
            Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,ListCartActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }
}