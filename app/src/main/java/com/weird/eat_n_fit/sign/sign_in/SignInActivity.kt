package com.weird.eat_n_fit.sign.sign_in

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
//import com.google.firebase.database.*
import com.weird.eat_n_fit.home.HomeActivity
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.sign.register.RegisterActivity
import com.weird.eat_n_fit.utils.Preferences
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    lateinit var iUsername: String
    lateinit var iPassword: String

    //    lateinit var mDatabase: DatabaseReference
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        supportActionBar?.hide()

//        mDatabase = FirebaseDatabase.getInstance().getReference("User")

        btn_signIn.setOnClickListener {
            iUsername = username_signIn.text.toString()
            iPassword = password_signIn.text.toString()

            if (iUsername.equals("")) {
                username_signIn.error = "Silahkan tulis Username Anda"
                username_signIn.requestFocus()
            } else if (iPassword.equals("")) {
                password_signIn.error = "Silahkan tulis Password Anda"
                password_signIn.requestFocus()
            } else {
//                pushLogin(iUsername, iPassword)
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


//    private fun pushLogin(iUsername: String, iPassword: String) {
//        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {

//            override fun onDataChange(dataSnapshot: DataSnapshot) {

//                val user = dataSnapshot.getValue(User::class.java)

//                if (user == null) {
//                    Toast.makeText(this@SignInActivity, "User tidak ditemukan", Toast.LENGTH_LONG).show()
//
//                } else {
//                    if (user.password.equals(iPassword)){
//                        Toast.makeText(this@SignInActivity, "Selamat Datang", Toast.LENGTH_LONG).show()

//                        preferences.setValues("nama", user.nama.toString())
//                        preferences.setValues("user", user.username.toString())
//                        preferences.setValues("url", user.url.toString())
//                        preferences.setValues("email", user.email.toString())
//                        preferences.setValues("saldo", user.saldo.toString())
//                        preferences.setValues("status", "1")

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

////            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(this@SignInActivity, ""+error.message, Toast.LENGTH_LONG).show()
//            }
//        })
//    }
}