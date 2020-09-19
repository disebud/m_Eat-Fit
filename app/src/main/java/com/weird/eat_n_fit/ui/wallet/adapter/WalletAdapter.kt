package com.weird.eat_n_fit.ui.wallet.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.bumptech.glide.request.RequestOptions
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.ui.wallet.model.Wallet
import com.weird.eat_n_fit.ui.wallet.transaction.TransactionWallet
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.text.NumberFormat
import java.util.*

class WalletAdapter(private var data: List<TransactionWallet>, private val listener: (TransactionWallet) -> Unit)
    : RecyclerView.Adapter<WalletAdapter.TransactionWalletListViewHolder>() {

    lateinit var ContextAdapter : Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionWalletListViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        ContextAdapter = parent.context
        val inflatedView: View = layoutInflater.inflate(R.layout.row_item_wallet_transaksi, parent, false)

        return TransactionWalletListViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: TransactionWalletListViewHolder, position: Int) {
        holder.bindItem(data[position], listener, ContextAdapter, position)
    }

    override fun getItemCount(): Int = data.size

    class TransactionWalletListViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val tvTitle: TextView = view.findViewById(R.id.tv_paket)
        private val tvSub: TextView = view.findViewById(R.id.tv_date)
        private val tvMoney: TextView = view.findViewById(R.id.tv_money)

        fun bindItem(data: TransactionWallet, listener: (TransactionWallet) -> Unit, context : Context, position : Int) {

            tvTitle.text = data.balhistory_title
            tvSub.text = data.balhistory_date

            val localID = Locale("in", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localID)


            if (data.balhistory_type == "1"){
                tvMoney.text = "  + "+formatRupiah.format(data.amount.toDouble())+"  "
                tvMoney.setTextColor(Color.GREEN)
            } else {
                tvMoney.text = "- "+formatRupiah.format(data.amount.toDouble())
//
            }


            itemView.setOnClickListener {
                listener(data)
            }
        }


    }

}

