package com.weird.eat_n_fit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.weird.eat_n_fit.Intro.IntroTwoActivity
import kotlinx.android.synthetic.main.activity_intro_one.*
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        supportActionBar?.hide()

        btn_register.setOnClickListener {
            val intent = Intent(this@SignInActivity,
                RegisterActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish()
        }
    }
}