package com.weird.eat_n_fit.ui.order

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.model.order.OrderFoodPacket
import com.weird.eat_n_fit.model.order.OrderViewModel
import com.weird.eat_n_fit.model.user.User
import com.weird.eat_n_fit.model.user.UserViewModel
import com.weird.eat_n_fit.ui.home.HomeActivity
import com.weird.eat_n_fit.ui.sign.signIn.screen.SignInActivity
import com.weird.eat_n_fit.ui.wallet.WalletActivity
import com.weird.eat_n_fit.ui.wallet.transaction.Amount
import com.weird.eat_n_fit.ui.wallet.transaction.TransactionViewModel

import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.activity_detail_paket.*
import java.text.NumberFormat
import java.util.*



    class CheckoutActivity : AppCompatActivity() {

        private var sharedPreferences: SharedPreferences? = null
        private val transactionViewModel by viewModels<TransactionViewModel>()
        private val userViewModel by viewModels<UserViewModel>()
        private var user: User = User()
        lateinit var idUser: String
        val orderViewModel by viewModels<OrderViewModel>()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_checkout)
            sharedPreferences = getSharedPreferences(
                getString(R.string.shared_preferences_name),
                Context.MODE_PRIVATE
            )

            dataUser()


            val iduser = sharedPreferences?.getString(getString(R.string.user_id), "")
            val token = sharedPreferences?.getString(getString(R.string.auth_token), "")
            val idPacket = intent.getStringExtra("idPacket")
            val price = intent.getStringExtra("price")
            val alamat = intent.getStringExtra("alamat")
            val waktu = intent.getStringExtra("waktu")
            val tanggal = intent.getStringExtra("tanggal")
            val porsi = intent.getStringExtra("porsi")
            val packet = intent.getStringExtra("packet")
            val totalPorsi = intent.getStringExtra("totalPorsi")
            println("ini packet ck : $packet")
            tv_item_name.text = packet
            tv_result_date_ck.text = tanggal
            tv_result_time_ck.text = waktu
            currency(price!!.toDouble(), tv_idr_ck)
            tv_porsi_idr_ck.text = "${porsi}/${totalPorsi} Porsi"
            var total = price.toInt() * porsi!!.toInt()
            currency(total.toDouble(), total_price)
            tv_content_address.text = alamat





            btn_payment_order.setOnClickListener {

                var order =
                    OrderFoodPacket(iduser!!, idPacket!!, porsi, tanggal!!, waktu!!, alamat!!)
                orderViewModel.orderFoodPacket(token!!, order)
                val topUp = Amount(total.toString(),packet!!)
                transactionViewModel.MinTopUp(token!!,topUp, id = idUser)
                val intent = Intent(this, OrderSuccessActivity::class.java)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                startActivity(intent)
                finish()
            }

            btn_topUp_order.setOnClickListener {
                val intent = Intent(this, WalletActivity::class.java)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                startActivity(intent)
                finish()
            }

            btn_cancel_order.setOnClickListener {
                val intent = Intent(this, HomeActivity::class.java)
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                startActivity(intent)
                finish()
            }
        }

        private fun dataUser() {
            val id = sharedPreferences?.getString(getString(R.string.user_id), "")
            val token = sharedPreferences?.getString(getString(R.string.auth_token), "")
            val porsi = intent.getStringExtra("porsi")
            val price = intent.getStringExtra("price")
            var total = price!!.toInt() * porsi!!.toInt()

            if (id != null && token != null) {
                println("TOKENN $token")
                userViewModel.getUserByID(token, id)

                userViewModel.user.observe(this, {
                    user = it
                    currency(it.user_balance!!.toDouble(), tv_real_balance)
                    idUser = it.user_id
                    var saldoWallet = it.user_balance.toInt()

                    if (saldoWallet > total) {
                        btn_payment_order.visibility = View.VISIBLE
                        saldo_tidak_cukup.visibility = View.INVISIBLE
                    } else {
                        btn_payment_order.visibility = View.INVISIBLE
                        saldo_tidak_cukup.visibility = View.VISIBLE
                    }
                    println("ini wall $saldoWallet")
                    println("ini tot $total")
                })


            } else {
                val intent = Intent(
                    this@CheckoutActivity,
                    SignInActivity::class.java
                )
                startActivity(intent)
            }

            if (!sharedPreferences!!.contains(getString(R.string.auth_token))) {
                val intent = Intent(
                    this@CheckoutActivity,
                    SignInActivity::class.java
                )
                startActivity(intent)
            }

        }


        fun currency(harga: Double, textView: TextView) {
            val localeID = Locale("in", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
            textView.setText(formatRupiah.format(harga as Double))

        }
    }
