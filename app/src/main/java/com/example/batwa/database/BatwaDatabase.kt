package com.example.batwa.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Account::class,Transaction::class),version = 1)
abstract class BatwaDatabase : RoomDatabase() {

     abstract fun getDAO(): BatwaDAO

}
