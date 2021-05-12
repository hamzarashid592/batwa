package com.example.batwa.ui.forms


import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.batwa.database.Account
import com.example.batwa.database.AccountTransactionView
import com.example.batwa.database.WalletTransaction
import com.example.batwa.databinding.FragmentTransactionAddEditBinding
import com.example.batwa.ui.BatwaViewModel

class TransactionAddEditFragment : Fragment() {

    //    Creating the view model.
    private val batwaViewModel: BatwaViewModel by activityViewModels()
    private val transactionAddEditFragmentArgs: TransactionAddEditFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentTransactionAddEditBinding.inflate(inflater, container, false)


//        Fetching the account name and transaction amount of the current transaction that was tapped.
        val accountTranView = transactionAddEditFragmentArgs.accountTranViewArg

//        Setting the text of the edit text fields based on the current transaction.
        binding.textViewAccount.text = accountTranView.accountName
        binding.editTextAmount.text = accountTranView.transactionAmount.toString().toEditable()
        binding.editTextComments.text = accountTranView.transactionComments.toString().toEditable()
        //binding.textViewTranType.text = accountTranView.transactionType
        binding.textViewDate.text = accountTranView.transactionDate
        binding.textViewTime.text = accountTranView.transactionTime

        //Populating the spinner.
        val choices= arrayOf<String>(WalletTransaction.INCOME, WalletTransaction.EXPENSE)
        // Create an ArrayAdapter having choices (income and expense) and a default spinner layout
        val arrayAdp = context?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_item,
                choices
            )
        }
        // Specify the layout to use when the list of choices appears
        arrayAdp?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Apply the adapter to the spinner
        binding.spinnerTranType.adapter = arrayAdp
        //Setting the default selection.
        binding.spinnerTranType.setSelection(choices.indexOf(accountTranView.transactionType))
        var newTranType = ""

        binding.spinnerTranType.onItemSelectedListener=object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                newTranType=choices[position]
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }


//        Updating that current transaction
        binding.buttonUpdate.setOnClickListener {

//        Getting and saving the user inputs if any.
            val newAmount = binding.editTextAmount.text.toString().toDouble()
            val newComments = binding.editTextComments.text.toString()

//        Making a new wallet transaction object from the account transaction view + the user input
            val newWalletTransaction = WalletTransaction(
                accountTranView.transactionID,
                newAmount,
                accountTranView.transactionDate,
                accountTranView.transactionTime,
                newComments,
                accountTranView.accountID,
                newTranType
            )

            val changed = batwaViewModel.updateTransaction(
                newWalletTransaction
            )
            Log.d("hamza", "$changed rows updated")
            //Hiding the soft keyboard
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
            //Navigating back
            findNavController().popBackStack()
        }

//        Deleting the current transaction. Deletion only considers the PK.
        binding.buttonDelete.setOnClickListener {

//        Making a new wallet transaction object from the account transaction view + the user input
            val newWalletTransaction = WalletTransaction(
                accountTranView.transactionID,
                0.0,
                accountTranView.transactionDate,
                accountTranView.transactionTime,
                "",
                accountTranView.accountID,
                accountTranView.transactionType
            )


            val changed = batwaViewModel.deleteTransaction(newWalletTransaction)
            Log.d("hamza", "$changed rows deleted")
            //Hiding the soft keyboard
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
            //Navigating back
            findNavController().popBackStack()
        }

        return binding.root
    }


}

//Extension function to convert string into an editable.
fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)