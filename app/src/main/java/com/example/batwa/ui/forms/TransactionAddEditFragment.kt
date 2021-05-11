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
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
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
        binding.textViewTranType.text = accountTranView.transactionType
        binding.textViewDate.text = accountTranView.transactionDate
        binding.textViewTime.text = accountTranView.transactionTime


//        Updating that current transaction
        binding.buttonUpdate.setOnClickListener {

//        Getting and saving the user inputs if any.
            val newAmount = binding.editTextAmount.text.toString().toDouble()
            val newComments = binding.editTextComments.text.toString()

//        Making a new wallet transaction object from the account transaction view + the user input
            val newWalletTransaction=WalletTransaction(
                accountTranView.transactionID,
                newAmount,
                accountTranView.transactionDate,
                accountTranView.transactionTime,
                newComments,
                accountTranView.accountID,
                accountTranView.transactionType
            )

            val changed=batwaViewModel.updateTransaction(
                newWalletTransaction
            )
            Log.d("hamza","$changed rows updated")
            //Hiding the soft keyboard
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
            //Navigating back
            findNavController().popBackStack()
        }

//        Deleting the current transaction. Deletion only considers the PK.
        binding.buttonDelete.setOnClickListener {

//        Making a new wallet transaction object from the account transaction view + the user input
            val newWalletTransaction=WalletTransaction(
                accountTranView.transactionID,
                0.0,
                accountTranView.transactionDate,
                accountTranView.transactionTime,
                "",
                accountTranView.accountID,
                accountTranView.transactionType
            )


            val changed=batwaViewModel.deleteTransaction(newWalletTransaction)
            Log.d("hamza","$changed rows deleted")
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