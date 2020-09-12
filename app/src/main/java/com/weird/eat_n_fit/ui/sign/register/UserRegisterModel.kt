package com.weird.eat_n_fit.ui.sign.register

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class UserRegisterModel(
    var user_email: String ?="",
    var user_password: String ?="",
    var user_f_name: String ?="",
    var user_l_name: String ?="",
    var user_gender: String ?="",
    var url: String ?="",
    var saldo: Int = 0
){}


class UserRegisterResponseData (
    var user_email: String ?="",
    var user_password: String ?="",
    var user_f_name: String ?="",
    var user_l_name: String ?="",
    var user_gender: String ?="",
    var url: String ?="",
    var saldo: Int = 0
){}