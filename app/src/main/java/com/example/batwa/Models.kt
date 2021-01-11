package com.example.batwa

data class Account (
        var accountBalance: Double = 0.0,
        var accountName: String = "",
        var accountID : Int? = 0,
        var accountNumRecords : Int = 0
)

data class Transaction(

        var transactionID: Int? =0,
        var transactionDate: String="",
        var transactionTime: String="",
        var transactionAmount: Double=0.0,
        var transactionType : String="",
        var accountID : Int = 0,
        var transactionComments: String=""
)