package com.weird.eat_n_fit.ui.wallet.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Wallet (
    var title: String ?="",
    var date: String ?="",
    var money: Double ,
    var status: String ?=""
): Parcelable