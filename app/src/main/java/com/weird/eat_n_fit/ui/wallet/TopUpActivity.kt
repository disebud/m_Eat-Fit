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
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.model.user.User
import com.weird.eat_n_fit.model.user.UserViewModel
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

        tv_250k.setOnClickListener {
            if (status250K) {
                deselectMoney(tv_250k)
            } else {
                selectMoney(tv_250k)
            }
        }

        tv_500k.setOnClickListener {
            if (status500K) {
                deselectMoney(tv_500k)
            } else {
                selectMoney(tv_500k)
            }
        }
        tv_750k.setOnClickListener {
            if (status750K) {
                deselectMoney(tv_750k)
            } else {
                selectMoney(tv_750k)
            }
        }
        tv_1000k.setOnClickListener {
            if (status1000K) {
                deselectMoney(tv_1000k)
            } else {
                selectMoney(tv_1000k)
            }
        }
        tv_2500k.setOnClickListener {
            if (status2500K) {
                deselectMoney(tv_2500k)
            } else {
                selectMoney(tv_2500k)
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
                        tv_250k.setTextColor(resources.getColor(R.color.colorBlack))
                        tv_250k.setBackgroundResource(R.drawable.shape_line_white)
                        tv_500k.setTextColor(resources.getColor(R.color.colorBlack))
                        tv_500k.setBackgroundResource(R.drawable.shape_line_white)
                        tv_750k.setTextColor(resources.getColor(R.color.colorBlack))
                        tv_750k.setBackgroundResource(R.drawable.shape_line_white)
                        tv_1000k.setTextColor(resources.getColor(R.color.colorBlack))
                        tv_1000k.setBackgroundResource(R.drawable.shape_line_white)
                        tv_2500k.setTextColor(resources.getColor(R.color.colorBlack))
                        tv_2500k.setBackgroundResource(R.drawable.shape_line_white)
                        status100K = false
                        status250K=false
                        status500K  = false
                        status750K  = false
                        status1000K  = false
                        status2500K  = false
                        tv_250k.setTextColor(resources.getColor(R.color.colorBlack))
                        tv_250k.setBackgroundResource(R.drawable.shape_line_white)
                        btn_top_up2.visibility = View.INVISIBLE
                    }
                } catch (e : NumberFormatException) {
                    tv_100k.setTextColor(resources.getColor(R.color.colorBlack))
                    tv_100k.setBackgroundResource(R.drawable.shape_line_white)
                    tv_250k.setTextColor(resources.getColor(R.color.colorBlack))
                    tv_250k.setBackgroundResource(R.drawable.shape_line_white)
                    tv_500k.setTextColor(resources.getColor(R.color.colorBlack))
                    tv_500k.setBackgroundResource(R.drawable.shape_line_white)
                    tv_750k.setTextColor(resources.getColor(R.color.colorBlack))
                    tv_750k.setBackgroundResource(R.drawable.shape_line_white)
                    tv_1000k.setTextColor(resources.getColor(R.color.colorBlack))
                    tv_1000k.setBackgroundResource(R.drawable.shape_line_white)
                    tv_2500k.setTextColor(resources.getColor(R.color.colorBlack))
                    tv_2500k.setBackgroundResource(R.drawable.shape_line_white)
                    status100K = false
                    status250K=false
                    status500K  = false
                    status750K  = false
                    status1000K  = false
                    status2500K  = false
                    btn_top_up2.visibility = View.INVISIBLE
                }
            }
        })




        btn_top_up2.setOnClickListener {
            val token = sharedPreferences?.getString(getString(R.string.auth_token), "")
            val et_amout = et_amount.text.toString()
            println( "ini adalah inputan = ${et_amout}" )
            val topUp = Amount(et_amout,"topUp")
            transactionViewModel.PostTopUp(token!!,topUp, id = idUser)
            startActivity(Intent(this, SuccessTopUpActivity::class.java))
            overridePendingTransition( R.anim.slide_in_right,R.anim.slide_out_left);
            finish()
        }

    }

    private fun selectMoney(textView: TextView){
        textView.setTextColor(resources.getColor(R.color.orangeThird))
        textView.setBackgroundResource(R.drawable.shape_line_pink)

        if (tv_100k == textView){
            status100K = true
            btn_top_up2.visibility = View.VISIBLE
            et_amount.setText("100000")
        }else if (tv_250k == textView){
            status250K= true
            btn_top_up2.visibility = View.VISIBLE
            et_amount.setText("250000")
        }else if (tv_500k == textView){
            status500K= true
            btn_top_up2.visibility = View.VISIBLE
            et_amount.setText("500000")
        }else if (tv_750k == textView){
            status750K= true
            btn_top_up2.visibility = View.VISIBLE
            et_amount.setText("750000")
        }else if (tv_1000k == textView){
            status1000K= true
            btn_top_up2.visibility = View.VISIBLE
            et_amount.setText("1000000")
        }else if (tv_2500k == textView){
            status2500K= true
            btn_top_up2.visibility = View.VISIBLE
            et_amount.setText("2500000")
        }


    }

    private fun deselectMoney(textView: TextView){
        textView.setTextColor(resources.getColor(R.color.colorWhite))
        textView.setBackgroundResource(R.drawable.shape_line_white)
        if (tv_100k == textView){
            status100K = false
            btn_top_up2.visibility = View.INVISIBLE
            et_amount.setText("")
        }else if (tv_250k == textView){
            status250K= false
            btn_top_up2.visibility = View.INVISIBLE
            et_amount.setText("")
        }else if (tv_500k == textView){
            status500K= false
            btn_top_up2.visibility = View.INVISIBLE
            et_amount.setText("")
        }else if (tv_750k == textView){
            status750K= false
            btn_top_up2.visibility = View.INVISIBLE
            et_amount.setText("")
        }else if (tv_1000k == textView){
            status1000K= false
            btn_top_up2.visibility = View.INVISIBLE
            et_amount.setText("")
        }else if (tv_2500k == textView){
            status2500K= false
            btn_top_up2.visibility = View.INVISIBLE
            et_amount.setText("")
        }

    }

    fun currecy(harga:Double, textView: TextView) {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        textView.setText(formatRupiah.format(harga as Double))
    }



}