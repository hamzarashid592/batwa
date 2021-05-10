package com.example.batwa.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.batwa.databinding.FragmentTransactionsBinding
import com.example.batwa.ui.adapter.WalletTransactionAdapter


class TransactionsFragment : Fragment() {

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
        val binding=FragmentTransactionsBinding.inflate(inflater,container,false)

        // Instantiating the adapters.
        val walletTransactionAdapter = WalletTransactionAdapter(WalletTransactionAdapter.TRANSACTION_LIST)

        //    Fetching the transactions from the viw model and applying them on the adapter.
        batwaViewModel.allTransactions.observe(viewLifecycleOwner) {

//            Submitting the transactions to the transaction adapter.
            walletTransactionAdapter.submitList(it)

            binding.apply {
//                Configuring the recycler view
                fragmentTransactionsRecyclerView.layoutManager = LinearLayoutManager(
                    context, LinearLayoutManager.VERTICAL,
                    false
                )
                fragmentTransactionsRecyclerView.adapter = walletTransactionAdapter
            }
        }


        return binding.root
    }

}