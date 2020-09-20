package com.weird.eat_n_fit.ui.home.order

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
import com.weird.eat_n_fit.model.food.FoodListAdapter
import com.weird.eat_n_fit.model.order.OrderViewModel
import com.weird.eat_n_fit.model.order.TransactionRecycleView
import com.weird.eat_n_fit.model.packet.PacketListAdapter
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_order.*

class OrderFragment : Fragment() {

    private var sharedPreferences: SharedPreferences? = null
    private val orderViewModel by activityViewModels<OrderViewModel>()
    lateinit var transactionRecycleView: TransactionRecycleView
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
        return inflater.inflate(R.layout.fragment_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val token = sharedPreferences?.getString(getString(R.string.auth_token), "")
        val userId = sharedPreferences?.getString(getString(R.string.user_id), "")

        orderViewModel.getTransaction(token!!,userId!!)

        rc_transaksi.layoutManager=LinearLayoutManager(activity)
        orderViewModel.transactionLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            transactionRecycleView = TransactionRecycleView(orderViewModel.transactionLiveData.value!!)
            rc_transaksi.adapter = transactionRecycleView
        })
    }
}