package com.example.batwa

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_transactions.*


class TransactionsFragment : Fragment() {


    private lateinit var dbHelper : DBHelper
    private var transactionsList=ArrayList<Transaction>()
    private var accountMap=HashMap<Int,Account>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        dbHelper= DBHelper(context,null)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transactions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        Fetching the transaction details.
        transactionsList=dbHelper.getTransactionRecord()

//        Setting the type of the transactions to fragment.
        transactionsList.forEach { it.type=Transaction.TRANSACTION_FRAGMENT}

//        Fetching the accounts map to be passed on with the transactions.
        accountMap=dbHelper.getAccountRecordMap()

//        Putting the data in the adapter

        var layoutManager : RecyclerView.LayoutManager= LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        var adapter: TransactionsAdapter? = context?.let { TransactionsAdapter(it,transactionsList,accountMap) }
        fragment_transactions_recycler_view.adapter=adapter
        fragment_transactions_recycler_view.layoutManager=layoutManager

    }
}