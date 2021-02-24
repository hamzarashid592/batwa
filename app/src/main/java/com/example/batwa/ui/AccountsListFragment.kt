package com.example.batwa.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.batwa.databinding.FragmentAccountsListBinding
import com.example.batwa.ui.adapter.AccountAdapter

class AccountsListFragment : Fragment() {

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
        // Doing view binding for the fragment
        val binding = FragmentAccountsListBinding.inflate(inflater, container, false)

        //Instantiating the adapter.
        val accountAdapter = AccountAdapter(AccountAdapter.ACCOUNT_LIST)

//        Displaying the accounts in the accounts recycler view (list)
        batwaViewModel.allAccounts.observe(viewLifecycleOwner) {

//            Submitting the data to the adapter
            accountAdapter.submitList(it)

            binding.apply {
//                Configuring the recycler view
                accountsListRecyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false)
                accountsListRecyclerView.adapter = accountAdapter
            }


        }
        return binding.root
    }
}