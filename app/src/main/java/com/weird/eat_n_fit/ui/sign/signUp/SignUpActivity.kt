//package com.weird.eat_n_fit.ui.sign.signUp
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Toast
//import com.weird.eat_n_fit.R
//import com.weird.eat_n_fit.ui.sign.signIn.User
//import com.weird.eat_n_fit.ui.utils.Preferences
//import kotlinx.android.synthetic.main.activity_sign_up.*
//
//class SignUpActivity : AppCompatActivity() {
//
//    private lateinit var preferences: Preferences
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_sign_up)
//
//        preferences = Preferences(this)
//
//        next_register.setOnClickListener {
//            val Fname = et_f_name_register.text.toString()
//            val Lname = et_l_name_register.text.toString()
//            val emailUser = email_register.text.toString()
//            val passwordUser = password_register.text.toString()
//            val genderUser = et_gender_register.text.toString()
//
//
//            if (Fname.equals("")) {
//            et_f_name_register.error = "Silahkan isi Username"
//            et_f_name_register.requestFocus()
//            } else if (Lname.equals("")) {
//                et_l_name_register.error = "Silahkan isi Password"
//                et_l_name_register.requestFocus()
//            }  else if (emailUser.equals("")) {
//                email_register.error = "Silahkan isi Email"
//                email_register.requestFocus()
//            }else if (passwordUser.equals("")) {
//                password_register.error = "Silahkan isi Password"
//                password_register.requestFocus()
//            }else if (genderUser.equals("")) {
//                et_gender_register.error = "Silahkan isi Nama"
//                et_gender_register.requestFocus()
//            } else {
//                var statusFname = Fname.indexOf(".")
//                var statusLname = Lname.indexOf(".")
//                if (statusFname >=0) {
//                    et_f_name_register.error = "Silahkan tulis Firstname Anda tanpa ."
//                    et_f_name_register.requestFocus()
//                } else if (statusLname >=0) {
//                    et_l_name_register.error = "Silahkan tulis Lastname Anda tanpa ."
//                    et_l_name_register.requestFocus()
//                }else {
//                    saveUser(Fname, Lname, emailUser, passwordUser,genderUser)
//                }
//
//            }
//        }
//    }
//
//    private fun saveUser(Fname: String, Lname: String, emailUser: String, passwordUser: String, genderUser: String) {
//
//        val user = User()
//        user.user_f_name = Fname
//        user.user_l_name = Lname
//        user.user_email = emailUser
//        user.user_password = passwordUser
//        user.user_gender = genderUser
//
//        if (emailUser != null) {
//            checkingUsername(emailUser, user)
//
//        }
//
//    }
//
//    private fun checkingUsername(emailUser: String, data: User) {
//
//        mFirebaseDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//
//                val user = dataSnapshot.getValue(User::class.java)
//                if (user == null) {
//                    mFirebaseDatabase.child(iUsername).setValue(data)
//
//                    preferences.setValues("Fnama", data.user_f_name.toString())
//                    preferences.setValues("Lname", data.user_l_name.toString())
//                    preferences.setValues("Uemail", data.user_email.toString())
//                    preferences.setValues("Upassword", data.user_password.toString())
//                    preferences.setValues("Ugender", data.user_gender.toString())
//                    preferences.setValues("Ubalance", "0")
//                    preferences.setValues("Ulevel", "3")
//                    preferences.setValues("Ustatus", "1")
//                    preferences.setValues("status", "1")
//
//                    val intent = Intent(this@SignUpActivity,
//                        SignUpPhotoActivity::class.java).putExtra("data", data)
//                    startActivity(intent)
//
//                } else {
//                    Toast.makeText(this@SignUpActivity, "User sudah digunakan", Toast.LENGTH_LONG).show()
//
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(this@SignUpActivity, ""+error.message, Toast.LENGTH_LONG).show()
//            }
//        })
//    }
//}