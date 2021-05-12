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
import com.example.batwa.databinding.FragmentAccountAddBinding
import com.example.batwa.ui.BatwaViewModel


class AccountAddFragment : Fragment() {

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
        val binding = FragmentAccountAddBinding.inflate(inflater, container, false)


        binding.buttonSubmit.setOnClickListener {

//            If the user hasn't entered an account name
            if (binding.editTextAccountName.text.toString().length == 0)
                Toast.makeText(context, "Please enter a valid account name", Toast.LENGTH_SHORT).show()
//            If the user hasn't entered an account balance
            else if (binding.editTextAccountBalance.text.toString().length == 0)
                Toast.makeText(context, "Please enter a valid account balance", Toast.LENGTH_SHORT).show()

//            If all goes well.
            else {
//            Taking the user input
                val inputAccountName = binding.editTextAccountName.text.toString()
                val inputAmount = binding.editTextAccountBalance.text.toString().toDouble()

//            Invoking the insert function from the view model.
                batwaViewModel.insertAccount(Account(null, inputAccountName, inputAmount, 0))

                Toast.makeText(
                    context,
                    "Successfully Added Account $inputAccountName",
                    Toast.LENGTH_SHORT
                ).show()

                //Hiding the soft keyboard
                closeSoftKeyboard()
                //Going to previous fragment
                findNavController().popBackStack()
            }

        }



        return binding.root
    }

    //Hiding the soft keyboard in the onStop method.
    override fun onStop() {
        closeSoftKeyboard()
        super.onStop()
    }

    //The close keyboard method.
    private fun closeSoftKeyboard() : Unit{
        val view= view?.findFocus()
        if (view!=null) {
            val imm =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view!!.windowToken, 0)
        }
    }

}

