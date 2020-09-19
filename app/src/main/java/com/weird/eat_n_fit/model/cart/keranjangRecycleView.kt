package com.weird.eat_n_fit.model.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.weird.eat_n_fit.R

class KeranjangRecycleView(
    private val keranjang: MutableList<Keranjang>
) :
    RecyclerView.Adapter<KeranjangViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeranjangViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_list_cart, parent, false)
        return KeranjangViewHolder(view)
    }

    override fun getItemCount(): Int {
        return keranjang.size
    }

    override fun onBindViewHolder(holder: KeranjangViewHolder, position: Int) {
        val productID = keranjang[position].foodId
        val foodQuantity = keranjang[position].qty
        val productPrice = keranjang[position].price
        val productNote = keranjang[position].desc
        val productName = keranjang[position].foodName
        holder.productID.text = productID
        holder.productName.text = productName
        holder.foodQuantity.text = foodQuantity.toString()
        holder.productPrice.text = productPrice.toString()
        holder.productNote.text = productNote
    }

}


class KeranjangViewHolder(v:View): RecyclerView.ViewHolder(v) {
    val productID = v.findViewById<TextView>(R.id.idProduct)
    val productName = v.findViewById<TextView>(R.id.productName)
    val foodQuantity = v.findViewById<TextView>(R.id.foodQuantity)
    val productPrice = v.findViewById<TextView>(R.id.foodPrice)
    val productNote = v.findViewById<TextView>(R.id.foodNote)

    val buttonRemove = v.findViewById<Button>(R.id.button_remove)

}