package com.weird.eat_n_fit.ui.utils

import com.weird.eat_n_fit.ui.keranjang.ListCartActivity

interface AplicationComponent {
    fun inject(listCartActivity: ListCartActivity)
}