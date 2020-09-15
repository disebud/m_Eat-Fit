package com.weird.eat_n_fit.ui.home.settings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.ui.home.HomeActivity
import com.weird.eat_n_fit.ui.sign.signUp.SignUpActivity
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_sign_in.*

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        save_edit_profile.setOnClickListener {
            Toast.makeText(
                this@EditProfileActivity,
                "Success Edit Profile",
                Toast.LENGTH_SHORT
            ).show()
            val intent = Intent(
                this@EditProfileActivity,
                HomeActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish()
        }


    }
}