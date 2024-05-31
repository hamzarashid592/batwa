package com.example.batwa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.batwa.databinding.FragmentRecordsEntryExpenseBinding

class RecordsEntryFragmentExpense : Fragment() {

    private var _binding : FragmentRecordsEntryExpenseBinding? = null;
    private val binding get() = _binding!!

    val dbHelper by lazy { DBHelper(context, null) };
    var accounts = ArrayList<String>()

    val args : RecordsEntryFragmentExpenseArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    //    Adding the calculator and record entry stuff here.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRecordsEntryExpenseBinding.inflate(inflater, container, false)

//        -------------------------------------------------------THE OTHER BLOCK-------------------------------------------------------

//        If we get a select account from any fragment
        if (args.selectedAccountName!=null)
            binding.accountSelectionRecordEntry.text=args.selectedAccountName

//        Getting the account list.
        binding.accountSelectionRecordEntry.setOnClickListener {
            val navDirections=RecordsEntryFragmentExpenseDirections.actionRecordsEntryFragmentExpenseToAccountsListFragment()
            binding.root.findNavController().navigate(navDirections)
        }


//        -------------------------------------------------------THE CALCULATOR BLOCK-------------------------------------------------------

        var operands = ArrayList<Int>()
        var operators = ArrayList<Char>()

//        The on click listeners for the individual keys
        binding.button0.setOnClickListener {

//          Do not enable the user to type 0 if there is nothing on the screen.
            if (binding.textViewAccountBalanceEntry.text != "")
                binding.textViewAccountBalanceEntry.text =
                    binding.textViewAccountBalanceEntry.text.toString() + '0'

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
            binding.textViewAccountBalanceEntry.text =
                binding.textViewAccountBalanceEntry.text.toString() + '.'
        }

        binding.buttonBackspace.setOnClickListener {
            binding.textViewAccountBalanceEntry.text =
                binding.textViewAccountBalanceEntry.text.toString().dropLast(1)

//            Setting the hint to 0 if there is no text.
            if (binding.textViewAccountBalanceEntry.text.length == 0) {
                binding.textViewAccountBalanceEntry.hint = "0"
            }
        }

        binding. buttonBackspace.setOnLongClickListener {
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

//            List to store all the operands given by the user
                var operandList = ArrayList<Double>()
//            To store the operators
                var operators: String = ""


//            Following parses the operands and operators in different entities.

//            To store a single operand.
                var operand = ""
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

//            Following applies the operations to the operands and generates the final result.

                var answer: Double = operandList[0] //To store the final result
                for (i in 0..operators.length - 1) {

                    when (operators[i]) {
                        '+' -> answer += operandList[i + 1]
                        '-' -> answer -= operandList[i + 1]
                        '*' -> answer *= operandList[i + 1]
                        '/' -> answer /= operandList[i + 1]
                    }
                }

//            Displaying the result.
                binding.textViewAccountBalanceEntry.text = answer.toString()
            }


        }



        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //    Fetching the account names to be displayed on the spinner
        accounts = dbHelper!!.getAccountNamesList()

    }
}