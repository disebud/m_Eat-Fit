package com.weird.eat_n_fit.ui.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.ui.home.HomeActivity
import kotlinx.android.synthetic.main.activity_success_top_up.*

class SuccessTopUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success_top_up)

        btn_home_success_topUp.setOnClickListener {
            finishAffinity()

            val intent = Intent(this@SuccessTopUpActivity,
                HomeActivity::class.java)
            startActivity(intent)
            overridePendingTransition( R.anim.slide_in_right,R.anim.slide_out_left);
            finish()
        }

        btn_view_wallet.setOnClickListener {
            finishAffinity()

            val intent = Intent(this@SuccessTopUpActivity,
                WalletActivity::class.java)
            startActivity(intent)
            overridePendingTransition( R.anim.slide_in_right,R.anim.slide_out_left);
            finish()
        }
    }
}