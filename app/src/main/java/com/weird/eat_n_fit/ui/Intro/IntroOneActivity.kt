package com.weird.eat_n_fit.ui.Intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.ui.sign.signIn.screen.SignInActivity
import kotlinx.android.synthetic.main.activity_intro_one.*
import com.weird.eat_n_fit.ui.utils.Preferences

class IntroOneActivity : AppCompatActivity() {

    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_one)
        supportActionBar?.hide()

        preferences = Preferences(this)

        if (preferences.getValues("Intro").equals("1")) {
            finishAffinity()

            val intent = Intent(this@IntroOneActivity,
                SignInActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish()

        }

        next_one.setOnClickListener {
            val intent = Intent(this@IntroOneActivity,
                IntroTwoActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish()
        }

        skip_one.setOnClickListener {
            preferences.setValues("Intro", "1")
            finishAffinity()

            val intent = Intent(this@IntroOneActivity,
                SignInActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish()
        }
    }
}