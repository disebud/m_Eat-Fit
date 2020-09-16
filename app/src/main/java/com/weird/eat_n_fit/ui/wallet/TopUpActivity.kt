package com.weird.eat_n_fit.ui.wallet

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import com.weird.eat_n_fit.ui.home.dashboard.User
import com.weird.eat_n_fit.ui.home.dashboard.UserViewModel
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.ui.sign.signIn.screen.SignInActivity
import com.weird.eat_n_fit.ui.wallet.transaction.Amount
import com.weird.eat_n_fit.ui.wallet.transaction.TransactionViewModel
import kotlinx.android.synthetic.main.activity_top_up.*
import java.lang.NumberFormatException
import java.text.NumberFormat
import java.util.*

class TopUpActivity : AppCompatActivity() {

    private var sharedPreferences: SharedPreferences? = null
    private val transactionViewModel by viewModels<TransactionViewModel>()
    private val userViewModel by viewModels<UserViewModel>()
    private var user: User = User()
    var idUser = ""
    private var status100K : Boolean = false
    private var status250K : Boolean = false
    private var status500K : Boolean = false
    private var status750K : Boolean = false
    private var status1000K : Boolean = false
    private var status2500K : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_up)
        sharedPreferences = getSharedPreferences(
            getString(R.string.shared_preferences_name),
            Context.MODE_PRIVATE
        )

        dataUser()
        initListener()
    }

    private fun dataUser(){
        val id = sharedPreferences?.getString(getString(R.string.user_id), "")
        val token = sharedPreferences?.getString(getString(R.string.auth_token), "")
        if (id != null && token != null) {
            println("TOKENN $token")
            userViewModel.getUserByID(token, id)

            userViewModel.user.observe(this, {
                user = it
                currecy(it.user_balance!!.toDouble(), idr_balance)
                idUser = it.user_id
            })


        } else {
//            view.findNavController().navigate(R.id.action_to_signInActivity)
            val intent = Intent(
                this@TopUpActivity,
                SignInActivity::class.java)
            startActivity(intent)
        }

        if (!sharedPreferences!!.contains(getString(R.string.auth_token))) {
            //            view.findNavController().navigate(R.id.action_to_signInActivity)
            val intent = Intent(
                this@TopUpActivity,
                SignInActivity::class.java)
            startActivity(intent)
        }

    }

    private fun initListener() {

        tv_100k.setOnClickListener {
            if (status100K) {
                deselectMoney(tv_100k)
            } else {
                selectMoney(tv_100k)
            }
        }

        et_amount.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(
                s: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) { }

            override fun beforeTextChanged(
                s: CharSequence,
                start: Int,
                count: Int,
                after: Int
            ) {  }

            override fun afterTextChanged(s: Editable) {
                try {
                    if (s.toString().toInt() >= 100000) {
                        btn_top_up2.visibility = View.VISIBLE
                    } else {
                        tv_100k.setTextColor(resources.getColor(R.color.colorBlack))
                        tv_100k.setBackgroundResource(R.drawable.shape_line_white)
                        status100K = false
                        btn_top_up2.visibility = View.INVISIBLE
                    }
                } catch (e : NumberFormatException) {
                    tv_100k.setTextColor(resources.getColor(R.color.colorBlack))
                    tv_100k.setBackgroundResource(R.drawable.shape_line_white)
                    status100K = false
                    btn_top_up2.visibility = View.INVISIBLE
                }
            }
        })




        btn_top_up2.setOnClickListener {
            val token = sharedPreferences?.getString(getString(R.string.auth_token), "")
            val et_amout = et_amount.text.toString()
            println( "ini adalah inputan = ${et_amout}" )
            transactionViewModel.PostTopUp(token!!,Amount(amount = et_amount.text.toString()),id = idUser)
            startActivity(Intent(this, SuccessTopUpActivity::class.java))
            overridePendingTransition( R.anim.slide_in_right,R.anim.slide_out_left);
            finish()
        }

    }

    private fun selectMoney(textView: TextView){
        textView.setTextColor(resources.getColor(R.color.orangeThird))
        textView.setBackgroundResource(R.drawable.shape_line_pink)
        status100K = true

        btn_top_up2.visibility = View.VISIBLE
        et_amount.setText("100000")
    }

    private fun deselectMoney(textView: TextView){
        textView.setTextColor(resources.getColor(R.color.colorWhite))
        textView.setBackgroundResource(R.drawable.shape_line_white)
        status100K = false

        btn_top_up2.visibility = View.INVISIBLE
        et_amount.setText("")
    }

    fun currecy(harga:Double, textView: TextView) {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        textView.setText(formatRupiah.format(harga as Double))
    }



}