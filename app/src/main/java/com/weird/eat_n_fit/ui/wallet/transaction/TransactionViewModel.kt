package com.weird.eat_n_fit.ui.wallet.transaction


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.weird.eat_n_fit.config.RetrofitBuilder

class TransactionViewModel : ViewModel() {
    private val transactionRepository =
        TransactionRepository(RetrofitBuilder.createRetrofit().create(TransactionAPI::class.java))

    fun getTransactionList() = transactionRepository.transactionListLiveData as LiveData<List<Transaction>>

    fun fetchUserTransactionList(id: String) = transactionRepository.fetchUserTransactionList(id)

    fun fetchPostNewTransaction(transaction: Transaction) = transactionRepository.fetchPostNewTransaction(transaction)

    fun TransactionWalletList(id: String) = transactionRepository.TransactionTopUpList(id)

    fun PostTopUp(token: String,amount: Amount,id: String) = transactionRepository.PostTopUp(token,amount, id)
}