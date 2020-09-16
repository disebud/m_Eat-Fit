package com.weird.eat_n_fit.ui.home.settings

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.weird.eat_n_fit.ui.home.dashboard.User
import com.weird.eat_n_fit.ui.home.dashboard.UserViewModel
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.ui.sign.signIn.screen.SignInActivity
import com.weird.eat_n_fit.ui.utils.Preferences
import kotlinx.android.synthetic.main.fragment_settings.*


class SettingsFragment : Fragment(), View.OnClickListener {

    lateinit var preferences: Preferences
    private var sharedPreferences: SharedPreferences? = null
    private val userViewModel by activityViewModels<UserViewModel>()
    private var user: User = User()

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
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferences = Preferences(view.context)

        tv_sign_out.setOnClickListener(this)

        val id = sharedPreferences?.getString(getString(R.string.user_id), "")
        val token = sharedPreferences?.getString(getString(R.string.auth_token), "")
        if (id != null && token != null) {
            println("TOKENN $token")
            userViewModel.getUserByID(token, id)
            userViewModel.user.observe(viewLifecycleOwner, {
                val email = it.user_email
                tv_email.text = email
                val name = "${it.user_f_name} ${it.user_l_name}"
                tv_nama_profile.text = name
                user = it
//                Picasso.get().load("${getString(R.string.image_link)}$id.jpg").into(iv_profile_settings)
                Glide.with(this)
                    .load("${getString(R.string.image_link)}$id.jpg")
                    .apply(RequestOptions.circleCropTransform())
                    .into(iv_profile_settings)
            })
        } else {
//            view.findNavController().navigate(R.id.action_to_signInActivity)
            val intent = Intent(
                context,
                SignInActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }

        if (!sharedPreferences!!.contains(getString(R.string.auth_token))) {
            //            view.findNavController().navigate(R.id.action_to_signInActivity)
            val intent = Intent(
                context,
                SignInActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onClick(v: View?) {
        when (v) {
            tv_sign_out -> {
                preferences.setValues("status", "0")
                with(sharedPreferences?.edit()) {
                    this?.remove(getString(R.string.user_id))
                    this?.remove(getString(R.string.auth_token))
                    this?.apply()
                    val intent = Intent(activity, SignInActivity::class.java)
                    activity?.finish()
                    startActivity(intent)
                }
            }
        }
    }


}