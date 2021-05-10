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

        Log.d("hamza","Fetching the transactions from the viw model and applying them on the adapter.")

        //    Fetching the transactions from the viw model and applying them on the adapter.
        batwaViewModel.allTransactions.observe(viewLifecycleOwner) {

            Log.d("hamza","Submitting the transactions to the transaction adapter.")

//            Submitting the transactions to the transaction adapter.
            walletTransactionAdapter.submitList(it)

            binding.apply {
                Log.d("hamza","Configuring the recycler view")
//                Configuring the recycler view
                fragmentTransactionsRecyclerView.layoutManager = LinearLayoutManager(
                    context, LinearLayoutManager.VERTICAL,
                    false
                )
                Log.d("hamza","Setting ther adapter")
                fragmentTransactionsRecyclerView.adapter = walletTransactionAdapter
                Log.d("hamza","Has set ther adapter")
            }
        }


        return binding.root
    }

}