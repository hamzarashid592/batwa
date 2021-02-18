package com.example.batwa.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BatwaDAO{

    @Insert
    suspend fun insertAccount(account: Account)

    @Insert
    suspend fun insertTransaction(transaction: Transaction)

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)

    @Delete
    suspend fun deleteAccount(account: Account)

    @Update
    suspend fun updateTransaction(transaction: Transaction)

    @Update
    suspend fun updateAccount(account: Account)

    @Query("Select * from `Transaction`")
    fun getAllTransactions() : Flow<List<Transaction>>

    @Query("Select * from `Account`")
    fun getAllAccounts() : Flow<List<Account>>

    @androidx.room.Transaction
    @Query("Select * from `Account`")
    fun getAccountTransactions() : Flow<List<AccountTransactionRelation>>
}