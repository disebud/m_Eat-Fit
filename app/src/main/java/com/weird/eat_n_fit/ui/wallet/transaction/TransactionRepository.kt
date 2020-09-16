package com.weird.eat_n_fit.ui.wallet.transaction


import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionRepository(val transactionAPI: TransactionAPI) {
//    var transactionListLiveData = MutableLiveData<List<Transaction>>()
    var TransactionWalletListLiveData = MutableLiveData<List<TransactionWallet>>()
//    var amountLiveData = MutableLiveData<Amount>()

    fun PostTopUp(token: String,amount : Amount ,id:String){
        var authToken = "Bearer ${token}"
        println("ini amount ${amount.amount}")
        println("ini id ${id}")
        transactionAPI.PostTopUp(authToken,amount,id).enqueue(object : Callback<RespAmount>{

            override fun onResponse(call: Call<RespAmount>, response: Response<RespAmount>) {
                println("=============================")
                print("FETCHING POST TRANSACTION SUCCESS -> ")

                println(response.code())
                println(response.body())
                println("=============================")
            }

            override fun onFailure(call: Call<RespAmount>, t: Throwable) {
                println("=============================")
                print("FETCHING POST TRANSACTION FAILED -> ")
                println(t)
                println("=============================")
            }
        })
    }

    fun TransactionWalletList(token: String,id:String){
        var authToken = "Bearer ${token}"
        transactionAPI.TransactionWalletList(authToken,id).enqueue(object : Callback<List<TransactionWallet>>{
            override fun onResponse(call: Call<List<TransactionWallet>>, response: Response<List<TransactionWallet>>) {
                TransactionWalletListLiveData.value = response.body()
            }

            override fun onFailure(call: Call<List<TransactionWallet>>, t: Throwable) {
                println("=============================")
                print("FETCHING USER TRANSACTION FAILED -> ")
                println(t)
                println("=============================")
            }
        })
    }

//    fun fetchUserTransactionList(id:String){
//        transactionAPI.fetchUserTransactionList(id).enqueue(object : Callback<List<Transaction>>{
//            override fun onResponse(call: Call<List<Transaction>>, response: Response<List<Transaction>>) {
//                transactionListLiveData.value = response.body()
//            }
//
//            override fun onFailure(call: Call<List<Transaction>>, t: Throwable) {
//                println("=============================")
//                print("FETCHING USER TRANSACTION FAILED -> ")
//                println(t)
//                println("=============================")
//            }
//        })
//    }
//
//    fun fetchPostNewTransaction(transaction: Transaction){
//        transactionAPI.fetchPostNewTransaction(transaction).enqueue(object : Callback<Transaction>{
//            override fun onResponse(call: Call<Transaction>, response: Response<Transaction>) {
//                println("=============================")
//                print("FETCHING POST TRANSACTION SUCCESS -> ")
//                println(response.code())
//                println("=============================")
//            }
//
//            override fun onFailure(call: Call<Transaction>, t: Throwable) {
//                println("=============================")
//                print("FETCHING POST TRANSACTION FAILED -> ")
//                println(t)
//                println("=============================")
//            }
//
//        })
//    }
}