package com.weird.eat_n_fit.ui.order.detailmenu

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.model.cart.KeranjangViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail_menu.*
import kotlinx.android.synthetic.main.activity_next_order.*
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.iv_poster_image_detail_menu
import kotlinx.android.synthetic.main.fragment_detail.tv_idr_menu_selected
import kotlinx.android.synthetic.main.fragment_detail.tv_nama_menu_selected
import kotlinx.android.synthetic.main.fragment_detail.tv_porsi_menu_selected
import kotlinx.android.synthetic.main.fragment_detail.val_cal
import kotlinx.android.synthetic.main.fragment_detail.val_carbs
import kotlinx.android.synthetic.main.fragment_detail.val_desc_menu
import kotlinx.android.synthetic.main.fragment_detail.val_fat
import kotlinx.android.synthetic.main.fragment_detail.val_protein
import java.text.NumberFormat
import java.util.*


class DetailFragment : Fragment(), View.OnClickListener {
    lateinit var picker1: NumberPicker
    private lateinit var mAuth: FirebaseAuth
    lateinit var navController: NavController
    private var sharedPreferences: SharedPreferences? = null
    val cartViewModel by activityViewModels<KeranjangViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = activity?.getSharedPreferences(
            getString(R.string.shared_preferences_name),
            Context.MODE_PRIVATE
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val token = sharedPreferences?.getString(getString(R.string.auth_token), "")
        val iduser = sharedPreferences?.getString(getString(R.string.user_id), "")
        picker1 = view.findViewById(R.id.porsi_makananan) as NumberPicker
        picker1.setMaxValue(1000)
        picker1.setMinValue(1)
        println("dari fragmen ${cartViewModel.detailFood}")

        var idFood = ""
        var foodName = ""
        var price= ""
        var desc =""
        var fat=  ""
        var proteiin =  ""
        var carbo =  ""
        var calories= ""
        var portion = ""


        for (i in cartViewModel.detailFood.indices) {
            idFood=(cartViewModel.detailFood[i].food_id)
            foodName = (cartViewModel.detailFood[i].food_name)
            price = (cartViewModel.detailFood[i].food_price)
            calories = (cartViewModel.detailFood[i].food_calories)
            proteiin = (cartViewModel.detailFood[i].food_protein)
            carbo = (cartViewModel.detailFood[i].food_carbs)
            fat = (cartViewModel.detailFood[i].food_fat)
            desc = (cartViewModel.detailFood[i].food_desc)
            portion = (cartViewModel.detailFood[i].food_portion)
        }
        println(token)
        tv_nama_menu_selected.text=foodName
        currency(price!!.toDouble(), tv_idr_menu_selected)
        tv_porsi_menu_selected.text="per ${portion} porsi"
        val_desc_menu.text=desc
        val_cal.text=calories
        val_fat.text=fat
        val_carbs.text=carbo
        val_protein.text=proteiin
        Picasso
            .get()
            .load("http://34.101.198.49:8082/images/${idFood}.jpg")

            .into(iv_poster_image_detail_menu)
        val porsi =view.findViewById(R.id.porsi_makananan) as NumberPicker

        btn_cart.setOnClickListener {
           val portion = porsi.value.toString()
            cartViewModel.addCartList(portion,idFood,foodName,iduser!!,price,desc)
            view.findNavController().navigate(R.id.action_detailFragment_to_cartFragment)
        }

    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }

    fun currency(harga:Double, textView: TextView) {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        textView.setText( formatRupiah.format(harga as Double))

    }
}