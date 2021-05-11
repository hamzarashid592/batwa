package com.example.batwa.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.batwa.databinding.FragmentAccountsSettingsBinding
import com.example.batwa.ui.adapter.AccountAdapter
import com.example.batwa.ui.adapter.WalletTransactionAdapter


class AccountsSettingsFragment : Fragment() {

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
        val binding = FragmentAccountsSettingsBinding.inflate(inflater, container, false)

        // Instantiating the adapters.
        val accountAdapter = AccountAdapter(AccountAdapter.ACCOUNT_SETTINGS, batwaViewModel)

        //Fetching the transactions from the view model and applying them on the adapter.
        batwaViewModel.allAccounts.observe(viewLifecycleOwner) {

            //Submitting the transactions to the transaction adapter.
            accountAdapter.submitList(it)

            binding.apply {
            //Configuring the recycler view
                accountsSettingsRecyclerView.layoutManager = LinearLayoutManager(
                    context, LinearLayoutManager.VERTICAL,
                    false
                )
                accountsSettingsRecyclerView.adapter = accountAdapter
            }
        }

        //Navigating to the add account fragment on tapping the add account fab.
        binding.fabAddAccount.setOnClickListener {
            it.findNavController().navigate(AccountsSettingsFragmentDirections.actionAccountsSettingsFragmentToAccountAddFragment())
        }


        return binding.root
    }


}