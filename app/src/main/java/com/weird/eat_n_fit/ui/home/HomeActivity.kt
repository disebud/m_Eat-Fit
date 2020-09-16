package com.weird.eat_n_fit.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.ui.home.order.OrderFragment
import com.weird.eat_n_fit.ui.home.settings.SettingsFragment
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.fragment_dashboard.*

class HomeActivity : AppCompatActivity() {

//    var imageSlide = intArrayOf(
//        R.drawable.cr_1,
//        R.drawable.cr_2,
//        R.drawable.cr_3,
//        R.drawable.cr_4,
//        R.drawable.cr_5
//    )
//
//    var nameImg = arrayOf(
//        "Fresh",
//        "Refresh",
//        "Seger",
//        "Delicious",
//        "Healthy"
//    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

//        carouselView.pageCount = nameImg.size
//
//        carouselView.setImageListener{position, imageView ->
//            imageView.setImageResource(imageSlide[position])
//        }
//
//        carouselView.setImageClickListener { position ->
//            Toast.makeText(applicationContext, nameImg[position], Toast.LENGTH_SHORT).show()
//        }

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