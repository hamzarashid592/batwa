package com.example.batwa.ui.forms


import android.content.Context
import android.os.Bundle
import android.text.Editable
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
    private val transactionAddEditFragmentArgs : TransactionAddEditFragmentArgs by navArgs()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentTransactionAddEditBinding.inflate(inflater, container, false)


//        Fetching the transactionID of the current transaction that was tapped.
        val tranID=transactionAddEditFragmentArgs.tranID

//        Fetching the Account Transaction View object from the tranID.
        val currentTransactionViewObject=batwaViewModel.fetchAccountTransactionViewObjectFromTranID(tranID)

//        Fetching the Wallet Transaction object from the tranID.
        val currentWalletTransaction=batwaViewModel.fetchWalletTransactionFromTranID(tranID)


//        Setting the text of the edit text fields based on the current transaction.
        binding.textViewAccount.text=currentTransactionViewObject.accountName
        binding.editTextAmount.text= currentWalletTransaction.transactionAmount.toString().toEditable()
        binding.textViewTranType.text=currentWalletTransaction.transactionType
        binding.textViewDate.text=currentWalletTransaction.transactionDate
        binding.textViewTime.text=currentWalletTransaction.transactionTime

//      Getting and saving the user inputs if any.
        currentWalletTransaction.transactionAmount=binding.editTextAmount.text.toString().toDouble()
        currentWalletTransaction.transactionComments= binding.editTextComments.text.toString()

//        Updating that current transaction
        binding.buttonUpdate.setOnClickListener {
            batwaViewModel.updateTransaction(currentWalletTransaction)
            //Hiding the soft keyboard
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
            //Navigating back
            findNavController().popBackStack()
        }

//        Deleting the current transaction
        binding.buttonDelete.setOnClickListener {
            batwaViewModel.deleteTransaction(currentWalletTransaction)
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
fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)