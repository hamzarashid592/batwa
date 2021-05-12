package com.example.batwa.ui.forms

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.batwa.databinding.FragmentTransactionCommentsBinding
import com.example.batwa.ui.BatwaViewModel


class TransactionCommentsFragment : Fragment() {

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
        val binding = FragmentTransactionCommentsBinding.inflate(inflater, container, false)

        binding.buttonSubmit.setOnClickListener {
            //Saving the comments entered by the user.
            batwaViewModel.setCurrentTransactionComments(binding.editTextComments.text.toString())
            //Hiding the soft keyboard
            closeSoftKeyboard()

            findNavController().popBackStack()
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