package com.example.batwa.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@Database(entities = arrayOf(Account::class,WalletTransaction::class),views = arrayOf(AccountTransactionView::class),
     version = 1)
abstract class BatwaDatabase : RoomDatabase() {

     abstract fun getDAO(): BatwaDAO

}

public class BatwaCallback @Inject constructor(
     val myDB : Provider<BatwaDatabase>,
     val scope : CoroutineScope
) : RoomDatabase.Callback(){

     override fun onCreate(db: SupportSQLiteDatabase) {
          super.onCreate(db)


//          Creating triggers for the DB. Using the old boilerplate method as Room doesn't allow triggers.

//          On insert trigger
          val on_insert="create trigger modify_balance_insert after insert on WalletTransaction\n" +
                  "begin\n" +
                  "    update Account set accountBalance=\n" +
                  "    case \n" +
                  "        when new.transactionType=\"Expense\" then accountBalance-new.transactionAmount \n" +
                  "        else accountBalance+new.transactionAmount \n" +
                  "    end, \n" +
                  "    accountNumRecords=accountNumRecords+1 where accountID=new.accountID;\n" +
                  "end"

//          On delete trigger
          val on_delete="create trigger modify_balance_delete after delete on WalletTransaction\n" +
                  "begin\n" +
                  "    update Account set accountBalance=\n" +
                  "    case\n" +
                  "        when old.transactionType=\"Expense\" then accountBalance+old.transactionAmount \n" +
                  "        else accountBalance-old.transactionAmount \n" +
                  "    end, \n" +
                  "    accountNumRecords=accountNumRecords-1 where accountID=old.accountID;\n" +
                  "end"

//          On update trigger
          val on_update="create trigger modify_balance_update after update on WalletTransaction\n" +
                  "begin\n" +
                  "update Account set accountBalance=\n" +
                  "    case \n" +
                  "        when new.transactionType=\"Expense\" then accountBalance-new.transactionAmount \n" +
                  "        when new.transactionType=\"Income\" then accountBalance+new.transactionAmount \n" +
                  "    end;\n" +
                  "end "

          db.execSQL(on_insert)
          db.execSQL(on_delete)
          db.execSQL(on_update)

          val dao : BatwaDAO=myDB.get().getDAO()

          scope.launch {

               dao.insertAccount(Account(null,"Test",0.0,0))

               dao.insertTransaction(WalletTransaction(null,1000.0,"24/09/2021","12:00 AM",
               "Test transaction",1,transactionType = WalletTransaction.EXPENSE))
          }



     }
}
