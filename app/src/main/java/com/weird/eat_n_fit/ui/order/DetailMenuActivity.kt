package com.weird.eat_n_fit.ui.order

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker

import android.widget.Toast

import android.widget.TextView

import androidx.activity.viewModels
import com.squareup.picasso.Picasso
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.model.cart.KeranjangViewModel
import com.weird.eat_n_fit.model.food.FoodViewModel
import com.weird.eat_n_fit.ui.keranjang.ListCartActivity
import kotlinx.android.synthetic.main.activity_detail_menu.*


import kotlinx.android.synthetic.main.activity_detail_paket.*
import kotlinx.android.synthetic.main.activity_detail_paket.title_name_paket
import java.text.NumberFormat
import java.util.*


class DetailMenuActivity : AppCompatActivity() {
    private var sharedPreferences: SharedPreferences? = null
    val foodViewModel by viewModels<FoodViewModel>()
    val cartViewModel by viewModels<KeranjangViewModel>()
    lateinit var picker1: NumberPicker
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
//        tv_idr_menu_selected.text=price
        currency(price!!.toDouble(), tv_idr_menu_selected)
        tv_porsi_menu_selected.text= "per ${portion} porsi"
        val_cal.text=calories
        val_carbs.text=carbo
        val_protein.text=proteiin
        val_fat.text=fat
        val_desc_menu.text=desc
        Picasso
            .get()
            .load("http://34.101.198.49:8082/images/${idFood}.jpg")

            .into(iv_poster_image_detail_menu)

        picker1 = findViewById(R.id.porsi_makananan)
        picker1.setMaxValue(1000)
        picker1.setMinValue(1)
    }

    fun currency(harga:Double, textView: TextView) {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        textView.setText( formatRupiah.format(harga as Double))

    }
}