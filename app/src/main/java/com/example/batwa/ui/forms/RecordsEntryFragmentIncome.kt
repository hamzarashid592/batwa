package com.example.batwa.ui.forms

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.batwa.database.WalletTransaction
import com.example.batwa.databinding.FragmentRecordsEntryIncomeBinding
import com.example.batwa.ui.BatwaViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class RecordsEntryFragmentIncome : Fragment() {

    //    Creating the view model.
    private val batwaViewModel: BatwaViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    //    Adding the calculator and record entry stuff here.
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentRecordsEntryIncomeBinding.inflate(inflater, container, false)


//        -------------------------------------------------------THE NAVIGATION BLOCK-------------------------------------------------------


//        Setting the default text of the record entry text view.
        if (batwaViewModel.getRecordEntryBuffer()!=null)
            binding.textViewAccountBalanceEntry.text= batwaViewModel.getRecordEntryBuffer().toString()

//        Navigating to the account selection fragment
        binding.accountSelectionRecordEntry.setOnClickListener {
//            Storing the state of the record entry text view if it is not null.
//            Following if is to avoid crashing as null value can't be converted into a string or a double.
            if (binding.textViewAccountBalanceEntry.text=="")
                batwaViewModel.clearRecordEntryBuffer()
            else
                batwaViewModel.setRecordEntryBuffer(binding.textViewAccountBalanceEntry.text.toString().toDouble())
//            Navigating...
            findNavController().navigate(RecordsEntryFragmentIncomeDirections.actionRecordsEntryFragmentIncomeToAccountsListFragment())
        }

//        Navigating to the comments entry fragment.
        binding.textViewAddComments.setOnClickListener {
//            Storing the state of the record entry text view if it is not null.
//            Following if is to avoid crashing as null value can't be converted into a string or a double.
            if (binding.textViewAccountBalanceEntry.text=="")
                batwaViewModel.clearRecordEntryBuffer()
            else
                batwaViewModel.setRecordEntryBuffer(binding.textViewAccountBalanceEntry.text.toString().toDouble())
//            Navigating...
            findNavController().navigate(RecordsEntryFragmentIncomeDirections.actionRecordsEntryFragmentIncomeToTransactionCommentsFragment())
        }

//        Setting the comments text view with the comments entered.
        if (batwaViewModel.getCurrentTransactionComments()!="")
            binding.textViewAddComments.text=batwaViewModel.getCurrentTransactionComments()

//        Setting the value of the selected account got from the account selection fragment
        if (batwaViewModel.getSelectedAccount() != null)
            binding.accountSelectionRecordEntry.text =
                batwaViewModel.getSelectedAccount()!!.accountName

//        -------------------------------------------------------THE SUBMIT BLOCK-------------------------------------------------------

//        The submit button action.
        binding.buttonSubmit.setOnClickListener {
//            Getting the current date and time.
            var currentDate=LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            var currentTime=LocalDateTime.now().format(DateTimeFormatter.ofPattern("hh:mm a"))

//            If the user hasn't entered a anything
            if (binding.textViewAccountBalanceEntry.text.toString().length == 0)
                Toast.makeText(context, "Please enter a valid amount", Toast.LENGTH_SHORT).show()

//            If the user hasn't selected an account
            else if (batwaViewModel.getSelectedAccount() == null)
                Toast.makeText(context, "Please select an account", Toast.LENGTH_SHORT).show()


//            If all works well.
            else {
                batwaViewModel.insertTransaction(
                    WalletTransaction(
                        null,
                        binding.textViewAccountBalanceEntry.text.toString().toDouble(),
                        currentDate,
                        currentTime,
                        batwaViewModel.getCurrentTransaction().transactionComments,
                        batwaViewModel.getSelectedAccount()!!.accountID,
                        WalletTransaction.INCOME
                    )
                )
                Toast.makeText(context, "Transaction added successfully", Toast.LENGTH_SHORT).show()

//                Clearing the record entry buffer.
                batwaViewModel.clearRecordEntryBuffer()

                findNavController().popBackStack()
            }

        }


//        -------------------------------------------------------THE CALCULATOR BLOCK-------------------------------------------------------

//        The on click listeners for the individual keys
        binding.button0.setOnClickListener {
//            Getting the user input.
            var userInput = binding.textViewAccountBalanceEntry.text.toString()

//          Do not enable the user to type 0 if there is nothing on the screen AND preventing division by 0.
            if (userInput != "" && userInput[userInput.length - 1] != '/'){
                binding.textViewAccountBalanceEntry.text =
                    binding.textViewAccountBalanceEntry.text.toString() + '0'
            }
        }

        binding.button1.setOnClickListener {
            binding.textViewAccountBalanceEntry.text =
                binding.textViewAccountBalanceEntry.text.toString() + '1'
        }

        binding.button2.setOnClickListener {
            binding.textViewAccountBalanceEntry.text =
                binding.textViewAccountBalanceEntry.text.toString() + '2'
        }

        binding.button3.setOnClickListener {
            binding.textViewAccountBalanceEntry.text =
                binding.textViewAccountBalanceEntry.text.toString() + '3'
        }

        binding.button4.setOnClickListener {
            binding.textViewAccountBalanceEntry.text =
                binding.textViewAccountBalanceEntry.text.toString() + '4'
        }

        binding.button5.setOnClickListener {
            binding.textViewAccountBalanceEntry.text =
                binding.textViewAccountBalanceEntry.text.toString() + '5'
        }

        binding.button6.setOnClickListener {
            binding.textViewAccountBalanceEntry.text =
                binding.textViewAccountBalanceEntry.text.toString() + '6'
        }

        binding.button7.setOnClickListener {
            binding.textViewAccountBalanceEntry.text =
                binding.textViewAccountBalanceEntry.text.toString() + '7'
        }

        binding.button8.setOnClickListener {
            binding.textViewAccountBalanceEntry.text =
                binding.textViewAccountBalanceEntry.text.toString() + '8'
        }

        binding.button9.setOnClickListener {
            binding.textViewAccountBalanceEntry.text =
                binding.textViewAccountBalanceEntry.text.toString() + '9'
        }

        binding.buttonPoint.setOnClickListener {

//            Getting the user input.
            var userInput = binding.textViewAccountBalanceEntry.text.toString()

//            Bool variable to keep track of whether user has already entered a decimal
//            before or not
            var decimalEntered: Boolean = false

//            Iterating the entire user input to find whether decimal exists or not
            userInput.forEach {
                if (it == '.')
                    decimalEntered = true
            }

//            Allow decimal to be entered only if the user has not entered it before.
            if (decimalEntered == false) {
                binding.textViewAccountBalanceEntry.text =
                    binding.textViewAccountBalanceEntry.text.toString() + '.'
            }
        }

        binding.buttonBackspace.setOnClickListener {
            binding.textViewAccountBalanceEntry.text =
                binding.textViewAccountBalanceEntry.text.toString().dropLast(1)

//            Setting the hint to 0 if there is no text.
            if (binding.textViewAccountBalanceEntry.text.length == 0) {
                binding.textViewAccountBalanceEntry.hint = "0"
            }
        }

        binding.buttonBackspace.setOnLongClickListener {
            var temp = binding.textViewAccountBalanceEntry.text.length
            binding.textViewAccountBalanceEntry.text =
                binding.textViewAccountBalanceEntry.text.toString().dropLast(temp + 1)

//            Setting the hint to 0 if there is no text.
            binding.textViewAccountBalanceEntry.hint = 0.toString()

            true
        }


        binding.buttonPlus.setOnClickListener {

//            Getting the user input.
            var userInput = binding.textViewAccountBalanceEntry.text.toString()

//          Do not enable the user to type operator sign if there is nothing on the screen.
//            Also ensuring whether the user is not typing another operator after an operator
            if (userInput != "" &&
                userInput[userInput.length - 1] != '+'
                && userInput[userInput.length - 1] != '-'
                && userInput[userInput.length - 1] != '/'
                && userInput[userInput.length - 1] != '*'
            ) {
                binding.textViewAccountBalanceEntry.text =
                    binding.textViewAccountBalanceEntry.text.toString() + '+'
            }
        }

        binding.buttonMinus.setOnClickListener {
//            Getting the user input.
            var userInput = binding.textViewAccountBalanceEntry.text.toString()

//          Do not enable the user to type operator sign if there is nothing on the screen.
//            Also ensuring whether the user is not typing another operator after an operator
            if (userInput != "" &&
                userInput[userInput.length - 1] != '+'
                && userInput[userInput.length - 1] != '-'
                && userInput[userInput.length - 1] != '/'
                && userInput[userInput.length - 1] != '*'
            ) {
                binding.textViewAccountBalanceEntry.text =
                    binding.textViewAccountBalanceEntry.text.toString() + '-'
            }
        }

        binding.buttonMultiply.setOnClickListener {
//            Getting the user input.
            var userInput = binding.textViewAccountBalanceEntry.text.toString()

//          Do not enable the user to type operator sign if there is nothing on the screen.
//            Also ensuring whether the user is not typing another operator after an operator
            if (userInput != "" &&
                userInput[userInput.length - 1] != '+'
                && userInput[userInput.length - 1] != '-'
                && userInput[userInput.length - 1] != '/'
                && userInput[userInput.length - 1] != '*'
            ) {
                binding.textViewAccountBalanceEntry.text =
                    binding.textViewAccountBalanceEntry.text.toString() + '*'
            }
        }

        binding.buttonDivide.setOnClickListener {

//            Getting the user input.
            var userInput = binding.textViewAccountBalanceEntry.text.toString()

//          Do not enable the user to type operator sign if there is nothing on the screen.
//            Also ensuring whether the user is not typing another operator after an operator
            if (userInput != "" &&
                userInput[userInput.length - 1] != '+'
                && userInput[userInput.length - 1] != '-'
                && userInput[userInput.length - 1] != '/'
                && userInput[userInput.length - 1] != '*'
            ) {
                binding.textViewAccountBalanceEntry.text =
                    binding.textViewAccountBalanceEntry.text.toString() + '/'
            }
        }

//        The equal button
        binding.buttonEquals.setOnClickListener {

//            Data from the text view.
            var userInput = binding.textViewAccountBalanceEntry.text.toString()


//          Do not enable the user to type equals sign if there is no operand after the operator.
//            The code will proceed if the following condition is true.

            if (userInput != "" &&
                userInput[userInput.length - 1] != '+'
                && userInput[userInput.length - 1] != '-'
                && userInput[userInput.length - 1] != '/'
                && userInput[userInput.length - 1] != '*'
            ) {

                //Calling the view model function to parse the user input and generate the result.
                var resultBalance = batwaViewModel.generateResultBalance(userInput)

                //Displaying the result.
                binding.textViewAccountBalanceEntry.text = resultBalance.toString()
            }
        }


        return binding.root
    }
}