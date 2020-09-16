package com.weird.eat_n_fit.ui.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.enigma_bank.ui.user.User
import com.example.enigma_bank.ui.user.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.ui.home.dashboard.FoodViewModel
import com.weird.eat_n_fit.ui.home.dashboard.foodListAdapter
import com.weird.eat_n_fit.ui.order.DetailPaketActivity
import com.weird.eat_n_fit.ui.sign.signIn.screen.SignInActivity
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.util.Observer


class DashboardFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    private var sharedPreferences: SharedPreferences? = null
    private val userViewModel by activityViewModels<UserViewModel>()
    private var user: User = User()
    val foodViewModel by activityViewModels<FoodViewModel>()
    lateinit var foodRecycleView: foodListAdapter

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
        val gridRecyclerView = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        rv_coming_soon.layoutManager = gridRecyclerView
        foodRecycleView =
            foodListAdapter(foodViewModel.foodLiveData.value!!)
        rv_coming_soon.adapter = foodRecycleView
        foodViewModel.foodLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer { foodRecycleView.notifyDataSetChanged() })

        val id = sharedPreferences?.getString(getString(R.string.user_id), "")
        val token = sharedPreferences?.getString(getString(R.string.auth_token), "")
        if (id != null && token != null) {
            println("TOKENN $token")
            userViewModel.getUserByID(token, id)
            userViewModel.user.observe(viewLifecycleOwner, {
                val bal = "Rp. ${it.user_balance}"
                tv_saldo.text = bal
                val name = "${it.user_f_name}"
                tv_nama.text = name
                user = it
                Picasso.get().load("${getString(R.string.image_link)}$id.jpg").into(iv_profile)
            })
        } else {
//            view.findNavController().navigate(R.id.action_to_signInActivity)
            val intent = Intent(
                context,
                SignInActivity::class.java)
            startActivity(intent)
        }

        if (!sharedPreferences!!.contains(getString(R.string.auth_token))) {
            //            view.findNavController().navigate(R.id.action_to_signInActivity)
            val intent = Intent(
                context,
                SignInActivity::class.java)
            startActivity(intent)
        }




        paketSehat.setOnClickListener{
            val intent = Intent(activity, DetailPaketActivity::class.java
            )
            startActivity(intent)
        }

    }


}