package com.weird.eat_n_fit.ui.wallet

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.enigma_bank.ui.user.User
import com.example.enigma_bank.ui.user.UserViewModel
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.ui.sign.signIn.screen.SignInActivity
import com.weird.eat_n_fit.ui.wallet.adapter.WalletAdapter
import com.weird.eat_n_fit.ui.wallet.model.Wallet
import kotlinx.android.synthetic.main.activity_wallet.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class WalletActivity : AppCompatActivity() {

    private var sharedPreferences: SharedPreferences? = null
    private val userViewModel by viewModels<UserViewModel>()
    private var user: User = User()

    private var dataList = ArrayList<Wallet>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)
        sharedPreferences = getSharedPreferences(
            getString(R.string.shared_preferences_name),
            Context.MODE_PRIVATE
        )

        dataUser()
        loadDummyData()
    }


    private fun dataUser(){
        val id = sharedPreferences?.getString(getString(R.string.user_id), "")
        val token = sharedPreferences?.getString(getString(R.string.auth_token), "")
        if (id != null && token != null) {
            println("TOKENN $token")
            userViewModel.getUserByID(token, id)
            userViewModel.user.observe(this, {
                currecy(it.user_balance!!.toDouble(), idr_balance)
            })
        } else {
//            view.findNavController().navigate(R.id.action_to_signInActivity)
            val intent = Intent(
                this@WalletActivity,
                SignInActivity::class.java)
            startActivity(intent)
        }

        if (!sharedPreferences!!.contains(getString(R.string.auth_token))) {
            //            view.findNavController().navigate(R.id.action_to_signInActivity)
            val intent = Intent(
                this@WalletActivity,
                SignInActivity::class.java)
            startActivity(intent)
        }
    }

    fun currecy(harga:Double, textView: TextView) {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        textView.setText(formatRupiah.format(harga as Double))
    }


    private fun initListener() {
        rv_transaksi.layoutManager = LinearLayoutManager(this)
        rv_transaksi.adapter = WalletAdapter(dataList){

        }

        btn_top_up.setOnClickListener {
            startActivity(Intent(this, TopUpActivity::class.java))
            overridePendingTransition( R.anim.slide_in_right,R.anim.slide_out_left);
            finish()
        }
    }

    private fun loadDummyData() {
        dataList.add(
            Wallet(
                "Acara Ulang Tahun",
                "Sabtu 12 Jan, 2020",
                700000.0,
                "0"
            )
        )
        dataList.add(
            Wallet(
                "Top Up",
                "Sabtu 12 Jan, 2020",
                1700000.0,
                "1"
            )
        )
        dataList.add(
            Wallet(
                "Paket Khitanan",
                "Sabtu 12 Jan, 2020",
                700000.0,
                "0"
            )
        )

        initListener()
    }

}