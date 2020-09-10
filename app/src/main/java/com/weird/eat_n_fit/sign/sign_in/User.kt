package com.weird.eat_n_fit.sign.sign_in

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


class User (
    var email: String ?="",
    var nama: String ?="",
    var password: String ?="",
    var url: String ?="",
    var username: String ?="",
    var saldo: String ?=""
)