package com.example.batwa

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {


    private var accountList=ArrayList<Account>()
    private var transactionList=ArrayList<Transaction>()
    private lateinit var dbHelper : DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //Context becomes available here..
        dbHelper= DBHelper(context,null)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        Fetching the account details.
        accountList= dbHelper.getAccountRecord()

//        Fetching the transaction details
        transactionList=dbHelper.getTransactionRecord()

//        Passing the account details to the adapter
        val accountsLayoutManager = GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
        accounts_recycler_view.layoutManager=accountsLayoutManager
        accounts_recycler_view.adapter= context?.let { AccountsAdapter(it,accountList) }

//        Passing the transaction details to the adpater.
        val transactionsLayoutManager= LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        transactions_recycler_view.layoutManager=transactionsLayoutManager
        transactions_recycler_view.adapter= context?.let { TransactionsAdapter(it,transactionList,dbHelper.getAccountNames()) }
    }
}