package com.weird.eat_n_fit.ui.wallet.transaction

import android.icu.text.CaseMap
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class Transaction(
    val id: String = "",
    val userID: String = "",
    val nominal: String = "",
    val created: String = "",
)


class TransactionWallet(
    val balhistory_id: String = "",
    val balhistory_date: String = "",
    val user_id: String = "",
    val amount: String = "",
    val balhistory_type: String = "",
    val balhistory_title: String = ""
)

class Amount(
    val amount: String = "",
    val title: String=""
)

class RespAmount (
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