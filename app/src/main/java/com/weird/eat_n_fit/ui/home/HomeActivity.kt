package com.weird.eat_n_fit.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.ui.home.order.OrderFragment
import com.weird.eat_n_fit.ui.home.settings.SettingsFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val fragmentOrder = OrderFragment()
        val fragmentSettings = SettingsFragment()
        val fragmentHome = DashboardFragment()

        setFragment(fragmentHome)

        iv_menu1.setOnClickListener {
            setFragment(fragmentHome)

            changeIcon(iv_menu1, R.drawable.ic_home_active)
            changeIcon(iv_menu2, R.drawable.ic_order_not_active)
            changeIcon(iv_menu3, R.drawable.ic_profile_not_active)
        }

        iv_menu2.setOnClickListener {
            setFragment(fragmentOrder)

            changeIcon(iv_menu1, R.drawable.ic_home_not_active)
            changeIcon(iv_menu2, R.drawable.ic_order_active)
            changeIcon(iv_menu3, R.drawable.ic_profile_not_active)
        }

        iv_menu3.setOnClickListener {
            setFragment(fragmentSettings)

            changeIcon(iv_menu1, R.drawable.ic_home_not_active)
            changeIcon(iv_menu2, R.drawable.ic_order_not_active)
            changeIcon(iv_menu3, R.drawable.ic_profile_active)
        }
    }

    protected fun setFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_frame, fragment)
        fragmentTransaction.commit()
    }

    private fun changeIcon(imageView: ImageView, int: Int){
        imageView.setImageResource(int)
    }
}