package com.example.batwa

//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.navigation.findNavController
//import androidx.navigation.fragment.navArgs
//import kotlinx.android.synthetic.main.fragment_records_entry_expense.view.*
//
//
//class RecordsEntryFragmentExpense : Fragment() {
//
//    val dbHelper by lazy { DBHelper(context, null) };
//    var accounts = ArrayList<String>()
//
//    val args : RecordsEntryFragmentExpenseArgs by navArgs()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//
//    //    Adding the calculator and record entry stuff here.
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        var view = inflater.inflate(R.layout.fragment_records_entry_expense, container, false)
//
//
////        -------------------------------------------------------THE OTHER BLOCK-------------------------------------------------------
//
////        If we get a select account from any fragment
//        if (args.selectedAccountName!=null)
//            view.account_selection_record_entry.text=args.selectedAccountName
//
////        Getting the account list.
//        view.account_selection_record_entry.setOnClickListener {
//            val navDirections=RecordsEntryFragmentExpenseDirections.actionRecordsEntryFragmentExpenseToAccountsListFragment()
//            view.findNavController().navigate(navDirections)
//        }
//
//
////        -------------------------------------------------------THE CALCULATOR BLOCK-------------------------------------------------------
//
//        var operands = ArrayList<Int>()
//        var operators = ArrayList<Char>()
//
////        The on click listeners for the individual keys
//        view.button_0.setOnClickListener {
//
////          Do not enable the user to type 0 if there is nothing on the screen.
//            if (view.text_view_account_balance_entry.text != "")
//                view.text_view_account_balance_entry.text =
//                    view.text_view_account_balance_entry.text.toString() + '0'
//
//        }
//
//        view.button_1.setOnClickListener {
//            view.text_view_account_balance_entry.text =
//                view.text_view_account_balance_entry.text.toString() + '1'
//        }
//
//        view.button_2.setOnClickListener {
//            view.text_view_account_balance_entry.text =
//                view.text_view_account_balance_entry.text.toString() + '2'
//        }
//
//        view.button_3.setOnClickListener {
//            view.text_view_account_balance_entry.text =
//                view.text_view_account_balance_entry.text.toString() + '3'
//        }
//
//        view.button_4.setOnClickListener {
//            view.text_view_account_balance_entry.text =
//                view.text_view_account_balance_entry.text.toString() + '4'
//        }
//
//        view.button_5.setOnClickListener {
//            view.text_view_account_balance_entry.text =
//                view.text_view_account_balance_entry.text.toString() + '5'
//        }
//
//        view.button_6.setOnClickListener {
//            view.text_view_account_balance_entry.text =
//                view.text_view_account_balance_entry.text.toString() + '6'
//        }
//
//        view.button_7.setOnClickListener {
//            view.text_view_account_balance_entry.text =
//                view.text_view_account_balance_entry.text.toString() + '7'
//        }
//
//        view.button_8.setOnClickListener {
//            view.text_view_account_balance_entry.text =
//                view.text_view_account_balance_entry.text.toString() + '8'
//        }
//
//        view.button_9.setOnClickListener {
//            view.text_view_account_balance_entry.text =
//                view.text_view_account_balance_entry.text.toString() + '9'
//        }
//
//        view.button_point.setOnClickListener {
//            view.text_view_account_balance_entry.text =
//                view.text_view_account_balance_entry.text.toString() + '.'
//        }
//
//        view.button_backspace.setOnClickListener {
//            view.text_view_account_balance_entry.text =
//                view.text_view_account_balance_entry.text.toString().dropLast(1)
//
////            Setting the hint to 0 if there is no text.
//            if (view.text_view_account_balance_entry.text.length == 0) {
//                view.text_view_account_balance_entry.hint = "0"
//            }
//        }
//
//        view.button_backspace.setOnLongClickListener {
//            var temp = view.text_view_account_balance_entry.text.length
//            view.text_view_account_balance_entry.text =
//                view.text_view_account_balance_entry.text.toString().dropLast(temp + 1)
//
////            Setting the hint to 0 if there is no text.
//            view.text_view_account_balance_entry.hint = 0.toString()
//
//            true
//        }
//
//
//        view.button_plus.setOnClickListener {
//
////            Getting the user input.
//            var userInput = view.text_view_account_balance_entry.text.toString()
//
////          Do not enable the user to type operator sign if there is nothing on the screen.
////            Also ensuring whether the user is not typing another operator after an operator
//            if (userInput != "" &&
//                userInput[userInput.length - 1] != '+'
//                && userInput[userInput.length - 1] != '-'
//                && userInput[userInput.length - 1] != '/'
//                && userInput[userInput.length - 1] != '*'
//            ) {
//                view.text_view_account_balance_entry.text =
//                    view.text_view_account_balance_entry.text.toString() + '+'
//            }
//        }
//
//        view.button_minus.setOnClickListener {
////            Getting the user input.
//            var userInput = view.text_view_account_balance_entry.text.toString()
//
////          Do not enable the user to type operator sign if there is nothing on the screen.
////            Also ensuring whether the user is not typing another operator after an operator
//            if (userInput != "" &&
//                userInput[userInput.length - 1] != '+'
//                && userInput[userInput.length - 1] != '-'
//                && userInput[userInput.length - 1] != '/'
//                && userInput[userInput.length - 1] != '*'
//            ) {
//                view.text_view_account_balance_entry.text =
//                    view.text_view_account_balance_entry.text.toString() + '-'
//            }
//        }
//
//        view.button_multiply.setOnClickListener {
////            Getting the user input.
//            var userInput = view.text_view_account_balance_entry.text.toString()
//
////          Do not enable the user to type operator sign if there is nothing on the screen.
////            Also ensuring whether the user is not typing another operator after an operator
//            if (userInput != "" &&
//                userInput[userInput.length - 1] != '+'
//                && userInput[userInput.length - 1] != '-'
//                && userInput[userInput.length - 1] != '/'
//                && userInput[userInput.length - 1] != '*'
//            ) {
//                view.text_view_account_balance_entry.text =
//                    view.text_view_account_balance_entry.text.toString() + '*'
//            }
//        }
//
//        view.button_divide.setOnClickListener {
//
////            Getting the user input.
//            var userInput = view.text_view_account_balance_entry.text.toString()
//
////          Do not enable the user to type operator sign if there is nothing on the screen.
////            Also ensuring whether the user is not typing another operator after an operator
//            if (userInput != "" &&
//                userInput[userInput.length - 1] != '+'
//                && userInput[userInput.length - 1] != '-'
//                && userInput[userInput.length - 1] != '/'
//                && userInput[userInput.length - 1] != '*'
//            ) {
//                view.text_view_account_balance_entry.text =
//                    view.text_view_account_balance_entry.text.toString() + '/'
//            }
//        }
//
////        The equal button
//        view.button_equals.setOnClickListener {
//
////            Data from the text view.
//            var userInput = view.text_view_account_balance_entry.text.toString()
//
//
////          Do not enable the user to type equals sign if there is no operand after the operator.
////            The code will proceed if the following condition is true.
//
//            if (userInput != "" &&
//                userInput[userInput.length - 1] != '+'
//                && userInput[userInput.length - 1] != '-'
//                && userInput[userInput.length - 1] != '/'
//                && userInput[userInput.length - 1] != '*'
//            ) {
//
////            List to store all the operands given by the user
//                var operandList = ArrayList<Double>()
////            To store the operators
//                var operators: String = ""
//
//
////            Following parses the operands and operators in different entities.
//
////            To store a single operand.
//                var operand = ""
//                for (i in 0..userInput.length - 1) {
//                    if (userInput[i] == '+' || userInput[i] == '-' || userInput[i] == '*' || userInput[i] == '/') {
//
//                        operators = operators + userInput[i]
//                        operandList.add(operand.toDouble())
//                        operand = ""
//                        continue
//                    }
//                    operand = operand + userInput[i] //Getting the operand character by character
//                }
//                operandList.add(operand.toDouble()) //Getting the last operand
//
////            Following applies the operations to the operands and generates the final result.
//
//                var answer: Double = operandList[0] //To store the final result
//                for (i in 0..operators.length - 1) {
//
//                    when (operators[i]) {
//                        '+' -> answer += operandList[i + 1]
//                        '-' -> answer -= operandList[i + 1]
//                        '*' -> answer *= operandList[i + 1]
//                        '/' -> answer /= operandList[i + 1]
//                    }
//                }
//
////            Displaying the result.
//                view.text_view_account_balance_entry.text = answer.toString()
//            }
//
//
//        }
//
//
//
//        return view
//    }
//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        //    Fetching the account names to be displayed on the spinner
//        accounts = dbHelper!!.getAccountNamesList()
//
//    }
//}