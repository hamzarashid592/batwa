package com.example.batwa.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.batwa.database.Account
import com.example.batwa.database.BatwaDAO
import com.example.batwa.database.WalletTransaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class BatwaViewModel @ViewModelInject constructor(
    private val batwaDAO: BatwaDAO,
    private val scope: CoroutineScope
) : ViewModel() {

    //--------------------------------------------Program Properties--------------------------------------------
    private var selectedAccount : Account? =null

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

    //--------------------------------------------DB Operation Functions--------------------------------------------
    fun insertAccount(account: Account) {
        scope.launch {
            batwaDAO.insertAccount(account)
        }
    }

    fun insertTransaction(transaction: WalletTransaction) {
        scope.launch {
            batwaDAO.insertTransaction(transaction)
        }
    }

}