package com.weird.eat_n_fit.ui.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.weird.eat_n_fit.model.user.User
import com.weird.eat_n_fit.model.user.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.model.food.FoodListAdapter
import com.weird.eat_n_fit.model.food.FoodViewModel
import com.weird.eat_n_fit.model.packet.PacketListAdapter
import com.weird.eat_n_fit.model.packet.PacketRepository
import com.weird.eat_n_fit.model.packet.PacketViewModel
import com.weird.eat_n_fit.ui.order.DetailPaketActivity
import com.weird.eat_n_fit.ui.sign.signIn.screen.SignInActivity
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.text.NumberFormat
import java.util.*

class DashboardFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    private var sharedPreferences: SharedPreferences? = null
    private val userViewModel by activityViewModels<UserViewModel>()
    private var user: User = User()
    val foodViewModel by activityViewModels<FoodViewModel>()
    lateinit var foodRecycleView: FoodListAdapter

    val packetViewModel by activityViewModels<PacketViewModel>()
    lateinit var packetRecycleView : PacketListAdapter

    var imageSlide = intArrayOf(
    R.drawable.cr_1,
    R.drawable.cr_2,
    R.drawable.cr_3,
    R.drawable.cr_4,
    R.drawable.cr_5
    )

    var nameImg = arrayOf(
        "Fresh",
        "Refresh",
        "Seger",
        "Delicious",
        "Healthy"
    )

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
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val token = sharedPreferences?.getString(getString(R.string.auth_token), "")

        foodViewModel.getAllFoods(token!!, "1", "1000", "")
        packetViewModel.getAllPacket(token!!, "1", "1000", "")
        val gridRecyclerView = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rv_coming_soon.layoutManager = gridRecyclerView

        foodViewModel.foodLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            foodRecycleView = FoodListAdapter(foodViewModel.foodLiveData.value!!)
            rv_coming_soon.adapter = foodRecycleView
        })
        val grid= StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rv_packet.layoutManager = grid

        packetViewModel.PacketLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            packetRecycleView = PacketListAdapter(packetViewModel.PacketLiveData.value!!)
            rv_packet.adapter = packetRecycleView
        })

        // carousel
        carouselView.setImageListener{position, imageView ->
            imageView.setImageResource(imageSlide[position])
        }

        carouselView.pageCount = nameImg.size

        carouselView.setImageClickListener { position ->
            Toast.makeText(context, nameImg[position],Toast.LENGTH_SHORT).show()
        }

        GetDataUser()

        }


    fun GetDataUser() {

        val id = sharedPreferences?.getString(getString(R.string.user_id), "")
        val token = sharedPreferences?.getString(getString(R.string.auth_token), "")
        if (id != null) {
            println("TOKENN $token")
            userViewModel.getUserByID(token!!, id)
            userViewModel.user.observe(viewLifecycleOwner, {
                Picasso.get().load("${getString(R.string.image_link)}$id.jpg").into(iv_profile)
                val bal = "Rp. ${it.user_balance}"
                tv_saldo.text = bal
                val name = "${it.user_f_name} ${it.user_l_name}"
                tv_nama.text = name
                user = it
            })
        } else {
            val intent = Intent(
                context,
                SignInActivity::class.java
            )
            startActivity(intent)
        }

        if (!sharedPreferences!!.contains(getString(R.string.auth_token))) {
            val intent = Intent(
                context,
                SignInActivity::class.java
            )
            startActivity(intent)
        }

        paketSehat.setOnClickListener{
            val intent = Intent(activity, DetailPaketActivity::class.java
            )
            startActivity(intent)
        }

    }

    fun currecy(harga:Double, textView: TextView) {
        val localeID = Locale("in", "ID")
        val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
        textView.setText(formatRupiah.format(harga as Double))
    }

}