package com.example.batwa.database

import androidx.room.*

@Entity
data class Account(
    @PrimaryKey(autoGenerate = true) val accountID: Int? = 0,
    val accountName: String = "",
    val accountBalance: Double = 0.0,
    val accountNumRecords: Int = 0
)

@Entity
data class WalletTransaction(
    @PrimaryKey(autoGenerate = true) val transactionID: Int? = 0,
    val transactionAmount: Double = 0.0,
    val transactionDate: String = "",
    val transactionComments: String = "",
    val accountID: Int? = 0,
    val transactionType : String=""
){
//    Static members
    companion object{
        var INCOME="Income"
        var EXPENSE="Expense"
    }
}

data class AccountTransactionRelation(
    @Embedded val account : Account,
    @Relation(
        parentColumn = "accountID",
        entityColumn = "accountID"
    )
    val accountTransactions: List<WalletTransaction>
)

@DatabaseView("select B.transactionID,B.transactionAmount," +
        "B.transactionDate,B.transactionComments,B.transactionType" +
        ",A.accountName from Account as A, " +
        "WalletTransaction as B where A.accountID=B.accountID;")
data class AccountTransactionView(
    val transactionID: Int,
    val transactionAmount: Double,
    val transactionDate: String,
    val transactionComments: String,
    val transactionType : String,
    val accountName: String
)







