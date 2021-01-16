package com.example.batwa

class Account (
        var accountBalance: Double = 0.0,
        var accountName: String = "",
        var accountID : Int? = 0,
        var accountNumRecords : Int = 0,
        var type : Int =0
){
    companion object{
        val ACCOUNT_CARD=0
        val ACCOUNT_LIST=1
        val ACCOUNT_SETTINGS=2

    }
}

data class Transaction(

        var transactionID: Int? =0,
        var transactionDate: String="",
        var transactionTime: String="",
        var transactionAmount: Double=0.0,
        var transactionType : String="",
        var accountID : Int = 0,
        var transactionComments: String=""
)