package com.example.batwa.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.batwa.database.Account
import com.example.batwa.database.BatwaDAO
import com.example.batwa.database.WalletTransaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class BatwaViewModel @ViewModelInject constructor(
    private val batwaDAO: BatwaDAO,
    private val scope: CoroutineScope
) : ViewModel() {

    //--------------------------------------------Live Data--------------------------------------------
    var allAccounts = batwaDAO.getAllAccounts().asLiveData()
    var allTransactions = batwaDAO.getAllTransactions().asLiveData()


    //--------------------------------------------Mathematical Functions--------------------------------------------


    //--------------------------------------------DB Operation Functions--------------------------------------------
    fun insertAccount(account: Account) {
        scope.launch {
            batwaDAO.insertAccount(account)
        }
    }

    fun insertTransaction(transaction: WalletTransaction) {
        scope.launch {
            batwaDAO.insertTransaction(transaction)
        }
    }

}