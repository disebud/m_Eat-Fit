package com.weird.eat_n_fit.ui.order

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.model.order.OrderFoodPacket
import com.weird.eat_n_fit.model.order.OrderViewModel
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.activity_detail_paket.*
import java.text.NumberFormat
import java.util.*

class CheckoutActivity : AppCompatActivity() {

    private var sharedPreferences: SharedPreferences? = null
    val orderViewModel by viewModels<OrderViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        sharedPreferences = getSharedPreferences(
            getString(R.string.shared_preferences_name),
            Context.MODE_PRIVATE
        )
        val iduser = sharedPreferences?.getString(getString(R.string.user_id), "")
        val token = sharedPreferences?.getString(getString(R.string.auth_token), "")
        val idPacket = intent.getStringExtra("idPacket")
        val price = intent.getStringExtra("price")
        val alamat = intent.getStringExtra("alamat")
        val waktu = intent.getStringExtra("waktu")
        val tanggal = intent.getStringExtra("tanggal")
        val porsi = intent.getStringExtra("porsi")
        val packet = intent.getStringExtra("packet")
        tv_item_name.text= packet
        tv_result_date_ck.text=tanggal
        tv_result_time_ck.text=waktu
        currency(price!!.toDouble(), tv_idr_ck)
        tv_porsi_idr_ck.text=porsi
        var total = price.toInt() * porsi!!.toInt()
            currency(total.toDouble(), total_price)
        tv_content_address.text=alamat
        btn_payment_order.setOnClickListener {

            var order = OrderFoodPacket(iduser!!,idPacket!!,porsi,tanggal!!,waktu!!,alamat!!)
            orderViewModel.orderFoodPacket(token!!,order)
            val intent = Intent(this,OrderSuccessActivity::class.java)
            overridePendingTransition( R.anim.slide_in_right,R.anim.slide_out_left);
            startActivity(intent)
            finish()
        }
    }

    fun currency(harga:Double, textView: TextView) {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        textView.setText( formatRupiah.format(harga as Double))
    }
}