package com.example.batwa.ui.forms


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.batwa.databinding.FragmentRecordsEntryExpenseBinding
import com.example.batwa.ui.BatwaViewModel

class TransactionAddEditFragment : Fragment() {

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
        val binding = FragmentRecordsEntryExpenseBinding.inflate(inflater, container, false)

        var currentTransaction=batwaViewModel.getCurrentTransaction()
        



        return binding.root
    }


}