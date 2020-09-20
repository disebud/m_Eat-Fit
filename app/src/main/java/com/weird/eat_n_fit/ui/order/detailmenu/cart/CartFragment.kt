package com.weird.eat_n_fit.ui.order.detailmenu.cart

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.weird.eat_n_fit.R
import com.weird.eat_n_fit.model.cart.KeranjangRecycleView
import com.weird.eat_n_fit.model.cart.KeranjangViewModel
import com.weird.eat_n_fit.model.food.FoodListAdapter
import kotlinx.android.synthetic.main.activity_list_cart.*
import kotlinx.android.synthetic.main.fragment_cart.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.rv_coming_soon

class CartFragment : Fragment() {
    private var sharedPreferences: SharedPreferences? = null
    val cartViewModel by activityViewModels<KeranjangViewModel>()
    lateinit var cartRecyclerView: KeranjangRecycleView
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
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rv_transaksi.layoutManager = LinearLayoutManager(context)

        cartViewModel.cartLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            cartRecyclerView = KeranjangRecycleView(cartViewModel.cartLiveData.value!!)
            rv_transaksi.adapter = cartRecyclerView
        })


    }
}