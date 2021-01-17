package com.example.batwa

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_accounts_list.*
import kotlinx.android.synthetic.main.fragment_accounts_settings.*


class AccountsListFragment : Fragment() {

    private lateinit var dbHelper : DBHelper
    private var accountsList=ArrayList<Account>()

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
        return inflater.inflate(R.layout.fragment_accounts_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        Fetching the account details.
        accountsList=dbHelper.getAccountRecordList()

//        Setting the type of the accounts to list.
        accountsList.forEach { it.type=Account.ACCOUNT_LIST }

//        Putting the data in the adapter

        var layoutManager : RecyclerView.LayoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        var adapter: AccountsAdapter? = context?.let { AccountsAdapter(it,accountsList) }
        accounts_list_recycler_view.adapter=adapter
        accounts_list_recycler_view.layoutManager=layoutManager

    }
}