package com.weird.eat_n_fit.model.signin

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class UserSignInModel(
    var user_email: String = "",
    var user_password: String = "",
){}


class SigninResponse(
    var user: User = User(),
    var token: String = ""
)

@Parcelize
class User (
    var user_id: String = "",
    var user_email: String = "",
    var user_password: String = "",
    var user_f_name: String = "",
    var user_l_name: String = "",
    var user_gender: String = "",
    var user_balance: String = "",
    var user_level: String = "",
    var user_status: String = "",
): Parcelable

//class Token(
//    var token: String ?="",
//){}