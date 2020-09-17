package com.weird.eat_n_fit.ui.order

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_checkout.*

class CheckoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        btn_payment_order.setOnClickListener {
            val intent = Intent(this, OrderSuccessActivity::class.java)
            startActivity(intent)
        }

        btn_cancel_order.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            finish()
            startActivity(intent)
        }
    }
}