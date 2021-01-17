package com.example.batwa

class Account (
        var accountBalance: Double = 0.0,
        var accountName: String = "",
        var accountID : Int? = 0,
        var accountNumRecords : Int = 0,
        var type : Int =0
){
    companion object{
//        For the account to be displayed on the main screen as cards
        val ACCOUNT_CARD=0

//        For the account to be displayed for selection during record entry
        val ACCOUNT_LIST=1

//        For the account to be displayed in the accounts settings fragment
        val ACCOUNT_SETTINGS=2

    }
}

class Transaction(

        var transactionID: Int? =0,
        var transactionDate: String="",
        var transactionTime: String="",
        var transactionAmount: Double=0.0,
        var transactionType : String="",
        var accountID : Int = 0,
        var transactionComments: String="",
        var type : Int=0
){
    companion object{
//        For the transaction to be displayed on the main screen
        val TRANSACTION_MAIN_SCREN=0

        //        For the transaction to be displayed in its own fragment
        val TRANSACTION_FRAGMENT=1
    }
}