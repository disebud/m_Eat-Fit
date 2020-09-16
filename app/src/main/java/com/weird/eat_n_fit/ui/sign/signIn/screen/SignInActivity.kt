package com.weird.eat_n_fit.ui.sign.signIn.screen

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.ui.home.HomeActivity
import com.weird.eat_n_fit.ui.sign.signIn.SigninResponse
import com.weird.eat_n_fit.ui.sign.signIn.UserSignInModel
import com.weird.eat_n_fit.ui.sign.signIn.UserSignInViewModel
import com.weird.eat_n_fit.ui.sign.signUp.SignUpActivity
import com.weird.eat_n_fit.ui.utils.Preferences
import kotlinx.android.synthetic.main.activity_sign_in.*
import kotlinx.android.synthetic.main.fragment_settings.*


class SignInActivity : AppCompatActivity() {
    companion object {
        private const val RC_SIGN_IN = 666
    }


    private val signInViewModel by viewModels<UserSignInViewModel>()
    private var userData: SigninResponse = SigninResponse()

    //    private lateinit var navController: NavController
    private var sharedPreferences: SharedPreferences? = null

    lateinit var preferences: Preferences


    private lateinit var mAuth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()


        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()


        googleSignInClient = GoogleSignIn.getClient(this, gso)

        //Autifikasi ke firebase
        mAuth = FirebaseAuth.getInstance()


        preferences = Preferences(this)
        preferences.setValues("Intro", "2")
        if (preferences.getValues("status").equals("0")) {
            finishAffinity()

            val intent = Intent(
                this@SignInActivity,
                HomeActivity::class.java
            )
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish()
        }

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
//                        if (it.user.user_level == "2") {

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
                                HomeActivity::class.java
                            )
                            startActivity(intent)
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                            finish()
                            this?.apply()
                            preferences.setValues("status", "1")
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

        btn_sign_up.setOnClickListener {
            val intent = Intent(
                this@SignInActivity,
                SignUpActivity::class.java
            )
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish()
        }
        iv_google.setOnClickListener {
            signIn()
        }
    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d("TAG", "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e)
                // ...
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("TAG", "signInWithCredential:success")
                    val user = mAuth.currentUser
                    val nameDisplay = user?.displayName
                    val emailTampung = user?.email

                    val intent = Intent(this, SignUpActivity::class.java)
                    intent.putExtra("nameDisplay", nameDisplay)
                    intent.putExtra("email", emailTampung)
                    startActivity(intent)
                    finish()


                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("TAG", "signInWithCredential:failure", task.exception)
                    // ...
                    Snackbar.make(view, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()

                }

                // ...
            }
    }
}






