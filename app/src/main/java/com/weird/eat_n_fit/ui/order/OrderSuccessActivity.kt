package com.weird.eat_n_fit.ui.order

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_order_success.*

class OrderSuccessActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_success)

        btn_view_transaction.setOnClickListener {

        }

        btn_home_success.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            finish()
            startActivity(intent)
        }
    }
}