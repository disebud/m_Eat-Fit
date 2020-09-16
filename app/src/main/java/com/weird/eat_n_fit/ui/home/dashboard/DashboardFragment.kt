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
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.enigma_bank.ui.user.User
import com.example.enigma_bank.ui.user.UserViewModel
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.ui.sign.signIn.screen.SignInActivity
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.tv_saldo
import java.text.NumberFormat
import java.util.*


class DashboardFragment : Fragment() {

    private var sharedPreferences: SharedPreferences? = null
    private val userViewModel by activityViewModels<UserViewModel>()
    private var user: User = User()


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
        if (id != null && token != null) {
            println("TOKENN $token")
            userViewModel.getUserByID(token, id)
            userViewModel.user.observe(viewLifecycleOwner, {
                val bal = "Rp. ${it.user_balance}"
                tv_saldo.text = bal
//                currecy(it.user_balance!!.toDouble(), idr_balance)
                val name = "${it.user_f_name}"
                tv_nama.text = name
                user = it
//                Picasso.get().load("${getString(R.string.image_link)}$id.jpg").into(iv_profile)
                Glide.with(this)
                    .load("${getString(R.string.image_link)}$id.jpg")
                    .apply(RequestOptions.circleCropTransform())
                    .into(iv_profile)
            })
        } else {
//            view.findNavController().navigate(R.id.action_to_signInActivity)
            val intent = Intent(
                context,
                SignInActivity::class.java
            )
            startActivity(intent)
        }

        if (!sharedPreferences!!.contains(getString(R.string.auth_token))) {
            //            view.findNavController().navigate(R.id.action_to_signInActivity)
            val intent = Intent(
                context,
                SignInActivity::class.java
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