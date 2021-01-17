package com.example.batwa

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {


    private var accountList=ArrayList<Account>()
    private var accountMap=HashMap<Int,Account>()
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

//        Creating an onclick listener on the accounts cog wheel.
        icon_accounts_settings.setOnClickListener {
            var navDirections=HomeFragmentDirections.actionHomeFragmentToAccountsSettingsFragment()
            findNavController().navigate(navDirections)
        }

//        Code for the show more button that will start the transactions fragment
        text_view_show_more_transactions.setOnClickListener {
            var navDirections=HomeFragmentDirections.actionHomeFragmentToTransactionsFragment()
            findNavController().navigate(navDirections)
        }


//        Fetching the account details to be displayed on the main screen as list..
        accountList= dbHelper.getAccountRecordList()

//        Fetching the transaction details to be displayed on the main screen.
        transactionList=dbHelper.getTransactionRecord()

//        Fetching the accounts map to be passed on with the transactions.
        accountMap=dbHelper.getAccountRecordMap()

        Log.d("hamza","No of transactions ${transactionList.size}")

//        Passing the account details to the adapter to be displayed as cards in the main screen.
        val accountsLayoutManager = GridLayoutManager(context,3,GridLayoutManager.VERTICAL,false)
        accounts_recycler_view.layoutManager=accountsLayoutManager
        accounts_recycler_view.adapter= context?.let { AccountsAdapter(it,accountList) }

//        Passing the transaction details to the adapter to be displayed on the main screen.
        val transactionsLayoutManager= LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        main_screen_transactions_recycler_view.layoutManager=transactionsLayoutManager
        main_screen_transactions_recycler_view.adapter= context?.let { TransactionsAdapter(it,transactionList,accountMap) }


    }
}