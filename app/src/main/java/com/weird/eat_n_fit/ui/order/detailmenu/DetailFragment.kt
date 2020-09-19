package com.weird.eat_n_fit.ui.order.detailmenu

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.weird.eat_n_fit.R
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment(), View.OnClickListener {
    private lateinit var mAuth: FirebaseAuth
    lateinit var navController: NavController
    private var sharedPreferences: SharedPreferences? = null

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
        val testName= arguments?.getString("name")
        val myValue = this.arguments?.getString("message")
        println(myValue)
        println(token)
        tv_nama_menu_selected.text=myValue
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }


}