package com.example.batwa.ui

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.batwa.database.Account
import com.example.batwa.database.AccountTransactionView
import com.example.batwa.database.BatwaDAO
import com.example.batwa.database.WalletTransaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.job
import kotlinx.coroutines.launch

class BatwaViewModel @ViewModelInject constructor(
    private val batwaDAO: BatwaDAO,
    private val scope: CoroutineScope
) : ViewModel() {

    //--------------------------------------------Program Properties--------------------------------------------
    private var selectedAccount : Account? =null
    private var currentTransaction : WalletTransaction= WalletTransaction()

//    This variable is for storing the state of the expense/income entry text view. When the user selects an account or
//    adds a comment, the fragment state is destroyed due to which the expense/income entry text view text is cleared which we don't want.
//    Therefore we store the state of expense/income entry text view in this property.
    private var recordEntryBuffer : Double?=null

    //--------------------------------------------Live Data--------------------------------------------
    var allAccounts = batwaDAO.getAllAccounts().asLiveData()
    var allTransactions = batwaDAO.getAllTransactions().asLiveData()


    //--------------------------------------------Program Functions--------------------------------------------
    fun generateResultBalance(userInput: String): Double {
        //List to store all the operands given by the user
        var operandList = ArrayList<Double>()
        //To store the operators
        var operators: String = ""


        //Following parses the operands and operators in different entities.

        //To store a single operand.
        var operand = ""
        //Iterating the userInput.
        for (i in 0..userInput.length - 1) {
            if (userInput[i] == '+' || userInput[i] == '-' || userInput[i] == '*' || userInput[i] == '/') {

                operators = operators + userInput[i]
                operandList.add(operand.toDouble())
                operand = ""
                continue
            }
            operand = operand + userInput[i] //Getting the operand character by character
        }
        operandList.add(operand.toDouble()) //Getting the last operand

        //Following applies the operations to the operands and generates the final result.

        var answer: Double = operandList[0] //To store the final result
        for (i in 0..operators.length - 1) {

            when (operators[i]) {
                '+' -> answer += operandList[i + 1]
                '-' -> answer -= operandList[i + 1]
                '*' -> answer *= operandList[i + 1]
                '/' -> answer /= operandList[i + 1]
            }
        }
        return answer
    }

    fun setSelectedAccount(account: Account) {
        selectedAccount = account
    }

    fun getSelectedAccount(): Account? = selectedAccount

    fun setCurrentTransaction(walletTransaction: WalletTransaction){
        currentTransaction=walletTransaction
    }
    fun setCurrentTransactionComments(comments : String){
        currentTransaction.transactionComments=comments
    }

    fun setRecordEntryBuffer(record : Double?){
        recordEntryBuffer=record
    }
    fun clearRecordEntryBuffer(){
        recordEntryBuffer=null
    }


    fun getCurrentTransaction() : WalletTransaction = currentTransaction
    fun getCurrentTransactionComments(): String?=currentTransaction.transactionComments

    fun getRecordEntryBuffer(): Double?=recordEntryBuffer

    //--------------------------------------------DB Operation Functions--------------------------------------------
    fun insertAccount(account: Account) {
        scope.launch {
            batwaDAO.insertAccount(account)
        }
    }

    fun updateAccount(account: Account) {
        scope.launch {
            batwaDAO.updateAccount(account)
        }
    }

    fun deleteAccount(account: Account) {
        scope.launch {
            batwaDAO.deleteAccount(account)
        }
    }

    fun insertTransaction(transaction: WalletTransaction) {
        scope.launch {
            batwaDAO.insertTransaction(transaction)
        }
    }

    fun updateTransaction(transaction: WalletTransaction){
        scope.launch {
            batwaDAO.updateTransaction(transaction)
        }
    }

    fun deleteTransaction(transaction: WalletTransaction){
        scope.launch {
            batwaDAO.deleteTransaction(transaction)
        }
    }

    fun fetchWalletTransactionFromTranID(tranID : Int) : WalletTransaction{
        var tran = WalletTransaction()
        scope.launch {
            tran=batwaDAO.fetchWalletTransactionFromTranID(tranID)

//            this.coroutineContext.job.join()
            Log.d("hamza","In DAO inside scope")
            Log.d("hamza","tran.transactionID ${tran.transactionID}")
            Log.d("hamza","tran.transactionAmount ${tran.transactionAmount}")
            Log.d("hamza","tran.transactionDate ${tran.transactionDate}")
            Log.d("hamza","tran.transactionTime ${tran.transactionTime}")
            Log.d("hamza","tran.transactionType ${tran.transactionType}")
        }
        Log.d("hamza","In DAO outside scope")
        Log.d("hamza","tran.transactionID ${tran.transactionID}")
        Log.d("hamza","tran.transactionAmount ${tran.transactionAmount}")
        Log.d("hamza","tran.transactionDate ${tran.transactionDate}")
        Log.d("hamza","tran.transactionTime ${tran.transactionTime}")
        Log.d("hamza","tran.transactionType ${tran.transactionType}")


        return tran
    }
    fun fetchAccountTransactionViewObjectFromTranID(tranID: Int) : AccountTransactionView{
        var tran =AccountTransactionView()
        scope.launch {
            tran=batwaDAO.fetchAccountTransactionViewObjectFromTranID(tranID)
        }
        return tran
    }

}