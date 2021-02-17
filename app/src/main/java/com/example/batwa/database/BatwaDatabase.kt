package com.example.batwa.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = arrayOf(Account::class,Transaction::class),version = 1)
abstract class BatwaDatabase : RoomDatabase() {

     abstract fun getDAO(): BatwaDAO

}

public class BatwaCallback @Inject constructor(
     val myDB : Provider<BatwaDatabase>,
     val scope : CoroutineScope
) : RoomDatabase.Callback(){

     override fun onCreate(db: SupportSQLiteDatabase) {
          super.onCreate(db)

          val dao : BatwaDAO=myDB.get().getDAO()

          scope.launch {

               dao.insertAccount(Account(null,"Transport",0.0,0))
               dao.insertAccount(Account(null,"Test Account 2",0.0,0))

               dao.insertTransaction(Transaction(null,10.2,))
          }



     }
}
