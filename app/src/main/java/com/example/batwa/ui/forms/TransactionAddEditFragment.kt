package com.example.batwa.ui.forms


import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.batwa.database.AccountTransactionView
import com.example.batwa.database.WalletTransaction
import com.example.batwa.databinding.FragmentTransactionAddEditBinding
import com.example.batwa.ui.BatwaViewModel

class TransactionAddEditFragment : Fragment() {

    //    Creating the view model.
    private val batwaViewModel: BatwaViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        val binding = FragmentTransactionAddEditBinding.inflate(inflater, container, false)
//
//        var currentTransaction : WalletTransaction=batwaViewModel.getCurrentTransaction()
//
////        Setting the text of the edit text fields.
//        binding.textViewAccount.text=currentTransaction.accountName
//        binding.editTextAmount.text= currentTransaction.transactionAmount.toString().toEditable()
//        binding.textViewTranType.text=WalletTransaction.EXPENSE
//        binding.textViewDate.text=currentTransaction.transactionDate
//
////      Getting and saving the user inputs if any (this includes comments field)
//        currentTransaction.transactionAmount=binding.editTextAmount.text.toString().toDouble()
//        currentTransaction.transactionComments= binding.editTextComments.text.toString()
//
////        Saving that current transaction
//        batwaViewModel.setCurrentTransaction(currentTransaction)
//
//        return binding.root
//    }


}

//Extension function to convert string into an editable.
fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)