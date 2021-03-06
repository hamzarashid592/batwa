package com.example.batwa.ui.forms

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.batwa.database.Account
import com.example.batwa.databinding.FragmentAccountAddEditBinding
import com.example.batwa.ui.BatwaViewModel


class AccountAddEditFragment : Fragment() {

//    Creating the view model.
    private val batwaViewModel: BatwaViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding=FragmentAccountAddEditBinding.inflate(inflater,container,false)


        binding.buttonSubmit.setOnClickListener {
//            Taking the user input
            val inputAccountName=binding.editTextAccountName.text.toString()
            val inputAmount=binding.editTextAccountBalance.text.toString().toDouble()

//            Invoking the insert function from the view model.
            batwaViewModel.insertAccount(Account(null,inputAccountName,inputAmount,0))

            Toast.makeText(context,"Successfully Added Account $inputAccountName",Toast.LENGTH_SHORT).show()

//          Hiding the soft keyboard
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
            findNavController().popBackStack()

        }


        return binding.root
    }


}