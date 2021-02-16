package com.example.batwa.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Account")
data class Account(
    @PrimaryKey(autoGenerate = true) val accountID: Int? = 0,
    val accountName: String = "",
    val accountBalance: Double = 0.0,
    val accountNumRecords: Int = 0
)

@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true) val TransactionID: Int? = 0,
    val transactionAmount: Double = 0.0,
    val transactionDate: String = "",
    val transactionComments: String = ""
)
