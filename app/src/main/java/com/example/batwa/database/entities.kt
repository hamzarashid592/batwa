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
data class Transaction(
    @PrimaryKey(autoGenerate = true) val transactionID: Int? = 0,
    val transactionAmount: Double = 0.0,
    val transactionDate: String = "",
    val transactionComments: String = "",
    val accountID: Int? = 0
)

data class AccountTransactionRelation(
    @Embedded val account : Account,
    @Relation(
        parentColumn = "accountID",
        entityColumn = "accountID"
    )
    val accountTransactions: List<Transaction>
)