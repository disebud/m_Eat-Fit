package com.weird.eat_n_fit.ui.home

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.weird.eat_n_fit.model.user.User
import com.weird.eat_n_fit.model.user.UserViewModel
import com.google.firebase.auth.FirebaseAuth
import com.squareup.picasso.Picasso
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.model.food.FoodListAdapter
import com.weird.eat_n_fit.model.food.FoodViewModel
import com.weird.eat_n_fit.ui.order.DetailPaketActivity
import com.weird.eat_n_fit.ui.sign.signIn.screen.SignInActivity
import kotlinx.android.synthetic.main.fragment_dashboard.*


class DashboardFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    private var sharedPreferences: SharedPreferences? = null
    private val userViewModel by activityViewModels<UserViewModel>()
    private var user: User = User()
    val foodViewModel by activityViewModels<FoodViewModel>()
    lateinit var foodRecycleView: FoodListAdapter

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
        rv_coming_soon.layoutManager = LinearLayoutManager(activity)
        foodViewModel.foodLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            foodRecycleView = FoodListAdapter(it)
            rv_coming_soon.adapter = foodRecycleView
//            foodRecycleView.notifyDataSetChanged()
        })

        val id = sharedPreferences?.getString(getString(R.string.user_id), "")
        if (id != null) {
            println("TOKENN $token")
            userViewModel.getUserByID(token, id)
            userViewModel.user.observe(viewLifecycleOwner, {
                Picasso.get().load("${getString(R.string.image_link)}$id.jpg").into(iv_profile)
                val bal = "Rp. ${it.user_balance}"
                tv_saldo.text = bal
                val name = "${it.user_f_name} ${it.user_l_name}"
                tv_nama.text = name
                user = it
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