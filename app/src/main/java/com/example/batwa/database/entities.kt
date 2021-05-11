package com.example.batwa.database

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Account(
    @PrimaryKey(autoGenerate = true) val accountID: Int? = 0,
    var accountName: String = "",
    var accountBalance: Double = 0.0,
    var accountNumRecords: Int = 0
) : Parcelable

@Entity
data class WalletTransaction(
    @PrimaryKey(autoGenerate = true) val transactionID: Int? = 0,
    var transactionAmount: Double = 0.0,
    var transactionDate: String = "",
    var transactionTime: String = "",
    var transactionComments: String = "",
    var accountID: Int? = 0,
    var transactionType : String=""
){
//    Empty overloaded constructor
    constructor():this(
    null,
    0.0,
    "",
    "",
    "",
    null,
    ""
    )

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

@Parcelize
@DatabaseView("select B.transactionID,B.transactionAmount," +
        "B.transactionDate,B.transactionTime,B.transactionComments,B.transactionType" +
        ",A.accountName,A.accountID from Account as A, " +
        "WalletTransaction as B where A.accountID=B.accountID;")
data class AccountTransactionView(
    var transactionID: Int?,
    var transactionAmount: Double,
    var transactionDate: String,
    var transactionTime: String,
    var transactionComments: String,
    var transactionType : String,
    var accountName: String,
    var accountID: Int?
) : Parcelable
{
    constructor() : this(null,
                    0.0,
                    null.toString(),
                    null.toString(),
                    null.toString(),
                    null.toString(),
                    null.toString(),
                    null
    )
}







