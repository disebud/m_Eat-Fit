package com.weird.eat_n_fit.ui.home.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.weird.eat_n_fit.R
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_order.*

class OrderFragment : Fragment() {

    val orderViewModel by activityViewModels<OrderViewModel>()
    lateinit var orderRecycleView: orderListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
        rc_transaksi.layoutManager = LinearLayoutManager(activity)
        orderRecycleView = orderListAdapter(orderViewModel.orderLiveData.value!!)
        rc_transaksi.adapter = orderRecycleView
        orderViewModel.orderLiveData.observe(viewLifecycleOwner, androidx.lifecycle.Observer { orderRecycleView.notifyDataSetChanged() })

    }
}