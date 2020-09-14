package com.weird.eat_n_fit.ui.sign.signIn.screen

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.ui.home.DashboardFragment
import com.weird.eat_n_fit.ui.home.HomeActivity
import com.weird.eat_n_fit.ui.sign.register.RegisterActivity
import com.weird.eat_n_fit.ui.sign.signIn.SigninResponse
import com.weird.eat_n_fit.ui.sign.signIn.UserSignInModel
import com.weird.eat_n_fit.ui.sign.signIn.UserSignInViewModel
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.fragment_settings.*


class SignInActivity : AppCompatActivity() {

    private val signInViewModel by viewModels<UserSignInViewModel>()
    private var userData: SigninResponse = SigninResponse()
//    private lateinit var navController: NavController
    private var sharedPreferences: SharedPreferences? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        supportActionBar?.hide()

        sharedPreferences = getSharedPreferences(
            getString(R.string.shared_preferences_name),
            Context.MODE_PRIVATE
        )

        if (sharedPreferences!!.contains(getString(R.string.auth_token))) {
//        navController.navigate(R.id.action_to_userHomeFragment)
            val intent = Intent(
                this@SignInActivity,
                HomeActivity::class.java
            )
            startActivity(intent)
        }

        btn_signIn.setOnClickListener {
            val inputEmail = email_signIn_input.text.toString()
            val inputPassword = password_signIn_input.text.toString()

            if (inputEmail == "") {
                email_signIn_input.error = "Please Fill Your Email"
                email_signIn_input.requestFocus()
                Toast.makeText(
                    this@SignInActivity,
                    "User credentials must be filled!",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (inputPassword == "") {
                password_signIn_input.error = "Please Fill Your Password"
                password_signIn_input.requestFocus()
                Toast.makeText(
                    this@SignInActivity,
                    "User credentials must be filled!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                signInViewModel.signIn(UserSignInModel(inputEmail, inputPassword))
                signInViewModel.userData.observe(this, {
                    if (it != null) {
                        if (it.user.user_level == "2") {
                            with(sharedPreferences?.edit()) {
                                this?.putString(
                                    getString(R.string.auth_token),
                                    it.token
                                )
                                this?.putString(
                                    getString(R.string.user_id),
                                    it.user.user_id
                                )
                                Toast.makeText(
                                    this@SignInActivity,
                                    "Welcome ${it.user.user_f_name}!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                val intent = Intent(
                                    this@SignInActivity,
                                    HomeActivity::class.java)
                                startActivity(intent)
                                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                                finish()
                                this?.apply()
                            }
                        }
                    } else {
                        Toast.makeText(
                            this@SignInActivity,
                            "Username atau Password salah",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
            }

        }

        btn_register.setOnClickListener {
            val intent = Intent(
                this@SignInActivity,
                RegisterActivity::class.java
            )
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish()
        }
    }
}








//
//
//
//    private fun pushLogin(iUsername: String, iPassword: String) {
//        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {
//
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//
//                val user = dataSnapshot.getValue(User::class.java)
//
//                if (user == null) {
//                    Toast.makeText(this@SignInActivity, "User tidak ditemukan", Toast.LENGTH_LONG).show()
//
//                } else {
//                    if (user.password.equals(iPassword)){
//                        Toast.makeText(this@SignInActivity, "Selamat Datang", Toast.LENGTH_LONG).show()
//
//                        preferences.setValues("nama", user.nama.toString())
//                        preferences.setValues("user", user.username.toString())
//                        preferences.setValues("url", user.url.toString())
//                        preferences.setValues("email", user.email.toString())
//                        preferences.setValues("saldo", user.saldo.toString())
//                        preferences.setValues("status", "1")
//
//                        finishAffinity()
//
//                        val intent = Intent(this@SignInActivity,
//                            HomeActivity::class.java)
//                        startActivity(intent)
//
//                    } else {
//                        Toast.makeText(this@SignInActivity, "Password Anda Salah", Toast.LENGTH_LONG).show()
//                    }
//
//                }
//            }
//
////            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(this@SignInActivity, ""+error.message, Toast.LENGTH_LONG).show()
//            }
//        })
//    }
//}