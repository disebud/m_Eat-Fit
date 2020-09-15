package com.weird.eat_n_fit.ui.wallet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.ui.wallet.adapter.WalletAdapter
import com.weird.eat_n_fit.ui.wallet.model.Wallet
import kotlinx.android.synthetic.main.activity_wallet.*

class WalletActivity : AppCompatActivity() {

    private var dataList = ArrayList<Wallet>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wallet)

        loadDummyData()
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