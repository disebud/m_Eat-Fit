package com.weird.eat_n_fit.ui.keranjang

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels

import androidx.recyclerview.widget.LinearLayoutManager
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.model.cart.KeranjangRecycleView
import com.weird.eat_n_fit.model.cart.KeranjangViewModel


import com.weird.eat_n_fit.ui.home.HomeActivity
import com.weird.eat_n_fit.ui.utils.MyApplication
import kotlinx.android.synthetic.main.activity_list_cart.*
import javax.inject.Inject

class ListCartActivity : AppCompatActivity() {
    private var sharedPreferences: SharedPreferences? = null
//    val cartViewModel by viewModels<KeranjangViewModel>()
@Inject lateinit var  cartViewModel: KeranjangViewModel
    lateinit var cartRecyclerView: KeranjangRecycleView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_cart)
        sharedPreferences = getSharedPreferences(
            getString(R.string.shared_preferences_name),
            Context.MODE_PRIVATE
        )
        (applicationContext as MyApplication).appContainer.cartViewModel
        cartListRecycleView.layoutManager = LinearLayoutManager(this)


//        cartRecyclerView = KeranjangRecycleView(cartViewModel.cartLiveData.value!!)
//        cartListRecycleView.adapter = cartRecyclerView
//        println(cartViewModel.cartLiveData.value)
//        println(cartViewModel.cartList)

        cartViewModel.cartLiveData.observe(this, androidx.lifecycle.Observer {
            cartRecyclerView = KeranjangRecycleView(it)
            cartListRecycleView.adapter = cartRecyclerView
//            cartRecyclerView.notifyDataSetChanged()
        })

        buttonContinue.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            startActivity(intent)
        }

    }
}