package com.weird.eat_n_fit.ui.Intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.sign.sign_in.SignInActivity
import com.weird.eat_n_fit.ui.utils.Preferences
import kotlinx.android.synthetic.main.activity_intro_two.*

class IntroTwoActivity : AppCompatActivity() {

    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro_two)
        supportActionBar?.hide()

        preferences = Preferences(this)

        if (preferences.getValues("Intro").equals("1")) {
            finishAffinity()

            val intent = Intent(this@IntroTwoActivity,
                SignInActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish()

        }

        next_two.setOnClickListener {
            val intent = Intent(this@IntroTwoActivity,
                IntroThreeActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish()
        }

        skip_two.setOnClickListener {
            preferences.setValues("Intro", "1")
            finishAffinity()

            val intent = Intent(this@IntroTwoActivity,
                SignInActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish()
        }
    }
}