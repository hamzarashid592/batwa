package com.example.batwa.ui.forms

import android.content.Context
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.batwa.database.Account
import com.example.batwa.databinding.FragmentAccountEditBinding
import com.example.batwa.ui.BatwaViewModel


class AccountEditFragment : Fragment() {

    //    Creating the view model.
    private val batwaViewModel: BatwaViewModel by activityViewModels()
//    Getting the nav arg.
    private val selectedAccountArg : AccountEditFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentAccountEditBinding.inflate(inflater, container, false)

        //Getting the selected account object.
        val selectedAccount=selectedAccountArg.selectedAccount


        //Setting the text fields of the view wrt the selected account.
        binding.editTextAccountBalance.text=selectedAccount.accountBalance.toString().toEditable()
        binding.editTextAccountName.text=selectedAccount.accountName.toString().toEditable()


        //If the user updates an account
        binding.buttonUpdate.setOnClickListener {

//            If the user hasn't entered an account name
            if (binding.editTextAccountName.text.toString().length == 0)
                Toast.makeText(context, "Please enter a valid account name", Toast.LENGTH_SHORT).show()
//            If the user hasn't entered an account balance
            else if (binding.editTextAccountBalance.text.toString().length == 0)
                Toast.makeText(context, "Please enter a valid account balance", Toast.LENGTH_SHORT).show()

//            If all goes well.
            else {
//            Taking the new user input
                val inputAccountName = binding.editTextAccountName.text.toString()
                val inputAmount = binding.editTextAccountBalance.text.toString().toDouble()

//            Invoking the update function from the view model.
                batwaViewModel.updateAccount(Account(selectedAccount.accountID, inputAccountName, inputAmount,
                    selectedAccount.accountNumRecords))

                Toast.makeText(
                    context,
                    "Successfully Updated Account $inputAccountName",
                    Toast.LENGTH_SHORT
                ).show()

//          Hiding the soft keyboard
                val imm =
                    activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.root.windowToken, 0)

                //Going back
                findNavController().popBackStack()
            }

        }

        //If the user deletes an account.
        binding.buttonDelete.setOnClickListener {
            //Calling the delete method.
            batwaViewModel.deleteAccount(selectedAccount)

            //Toast message
            Toast.makeText(
                context,
                "Successfully Deleted Account ${selectedAccount.accountName}",
                Toast.LENGTH_SHORT
            ).show()

            //Hiding the soft keyboard
            val imm =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)

            //Going back
            findNavController().popBackStack()
        }


        return binding.root
    }


}
