package com.weird.eat_n_fit.ui.order

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.squareup.picasso.Picasso
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.model.food.FoodViewModel
import kotlinx.android.synthetic.main.activity_detail_menu.*
import kotlinx.android.synthetic.main.row_packet.*

class DetailMenuActivity : AppCompatActivity() {
    private val foodViewModel by viewModels<FoodViewModel>()
    private var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_menu)

        sharedPreferences = getSharedPreferences(
            getString(R.string.shared_preferences_name),
            Context.MODE_PRIVATE
        )

        val token = sharedPreferences?.getString(getString(R.string.auth_token), "")
        val id = intent.getStringExtra("foodID")

        with(sharedPreferences?.edit()) {
            this?.putString("foodOrderID", id)
            this?.apply()
        }

        foodViewModel.getFoodByID(token!!, id!!)
        foodViewModel.foodByID.observe(this, {
            Picasso
                .get()
                .load("${getString(R.string.image_link)}$id.jpg")
                .into(iv_poster_image)
            tv_nama_menu_selected.text = it.food_name
            tv_idr_menu_selected.text = it.food_price

        })

        btn_order_menu.setOnClickListener {
            val intent = Intent(this, NextOrderActivity::class.java)
            startActivity(intent)
        }

    }
}