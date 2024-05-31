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
import com.example.batwa.databinding.FragmentAccountsSettingsBinding
import com.example.batwa.databinding.FragmentTransactionAddEditBinding


class AccountsSettingsFragment : Fragment() {

    private var _binding: FragmentAccountsSettingsBinding? = null
    private val binding get() = _binding!!
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
        _binding = FragmentAccountsSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        Fetching the account details.
        accountsList=dbHelper.getAccountRecordList()

//        Setting the type of the accounts to list.
        accountsList.forEach { it.type=Account.ACCOUNT_SETTINGS }

//        Putting the data in the adapter

        var layoutManager : RecyclerView.LayoutManager=LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        var adapter: AccountsAdapter? = context?.let { AccountsAdapter(it,accountsList) }
        binding.accountsSettingsRecyclerView.adapter=adapter
        binding.accountsSettingsRecyclerView.layoutManager=layoutManager

    }
}