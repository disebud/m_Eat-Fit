package com.weird.eat_n_fit.ui.sign.signIn

class UserSignInModel(
    var user_email: String = "",
    var user_password: String = "",
){}


class SigninResponse(
    var user: User = User(),
    var token: String = ""
)


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
)

//class Token(
//    var token: String ?="",
//){}