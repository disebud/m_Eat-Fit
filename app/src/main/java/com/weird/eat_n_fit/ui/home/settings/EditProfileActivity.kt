package com.weird.eat_n_fit.ui.home.settings

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.enigma_bank.ui.user.User
import com.example.enigma_bank.ui.user.UserViewModel
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.ui.home.HomeActivity
import com.weird.eat_n_fit.ui.sign.signIn.screen.SignInActivity
import com.weird.eat_n_fit.ui.sign.signUp.SignUpActivity
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.fragment_settings.*

class EditProfileActivity : AppCompatActivity() {

    private var sharedPreferences: SharedPreferences? = null
    private val userViewModel by viewModels<UserViewModel>()
    private var user: User = User()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        sharedPreferences = getSharedPreferences(
            getString(R.string.shared_preferences_name),
            Context.MODE_PRIVATE
        )

        val id = sharedPreferences?.getString(getString(R.string.user_id), "")
        val token = sharedPreferences?.getString(getString(R.string.auth_token), "")
        if (id != null && token != null) {
            println("TOKENN $token")
            userViewModel.getUserByID(token, id)
            userViewModel.user.observe(this, {
                user = it
                et_f_name_edit_profile.hint = it.user_f_name
                et_l_name_edit_profile.hint = it.user_l_name
                email_edit_profile.hint = it.user_email
                et_gender_edit_profile.hint = it.user_gender

            })
        } else {
            val intent = Intent(
                this@EditProfileActivity,
                SignInActivity::class.java)
            startActivity(intent)
        }

        if (!sharedPreferences!!.contains(getString(R.string.auth_token))) {
            //            view.findNavController().navigate(R.id.action_to_signInActivity)
            val intent = Intent(
                this@EditProfileActivity,
                SignInActivity::class.java)
            startActivity(intent)
        }

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

        back_edit_profile.setOnClickListener {
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