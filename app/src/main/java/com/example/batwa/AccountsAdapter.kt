package com.example.batwa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.batwa.databinding.LoAccountCardBinding
import com.example.batwa.databinding.LoAccountListBinding
import com.example.batwa.databinding.LoAccountSettingsBinding

class AccountsAdapter(
        private val context: Context,
        private val accounts: ArrayList<Account>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    class AccountCardViewHolder(private val binding: LoAccountCardBinding) : RecyclerView.ViewHolder(binding.root) {
        var pos = 0

        fun bind(account: Account, position: Int) {
            binding.textViewAccountName.text = account.accountName
            binding.textViewAccountBalance.text = "PKR ${account.accountBalance.toString()}"
            pos = position
        }

        companion object {
            fun from(parent: ViewGroup): AccountCardViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LoAccountCardBinding.inflate(layoutInflater, parent, false)
                return AccountCardViewHolder(binding)
            }
        }

        init {
            itemView.setOnClickListener {
            }
        }
    }

    class AccountSettingsViewHolder(private val binding: LoAccountSettingsBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {
        var pos = 0

        fun bind(account: Account, position: Int) {
            binding.textViewAccountName.text = account.accountName
            binding.textViewAccountBalance.text = "PKR ${account.accountBalance.toString()}"
            pos = position

            binding.root.setOnClickListener {
                Toast.makeText(context, "NumRecords ${account.accountNumRecords} at pos $pos", Toast.LENGTH_SHORT).show()
            }
        }
        companion object {
            fun from(parent: ViewGroup, context: Context): AccountSettingsViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LoAccountSettingsBinding.inflate(layoutInflater, parent, false)
                return AccountSettingsViewHolder(binding, context)
            }
        }
    }

    class AccountListViewHolder(private val binding: LoAccountListBinding, private val context: Context) : RecyclerView.ViewHolder(binding.root) {
        var pos = 0

        fun bind(account: Account, position: Int) {
            binding.textViewAccountName.text = account.accountName
            pos = position

            binding.root.setOnClickListener {
//                If the previous fragment is the income records entry fragment, go back to that fragment
                if(binding.root.findNavController().previousBackStackEntry!!.destination.id==R.id.viewPagerFragmentIncome) {
                    var navDirections =
                        AccountsListFragmentDirections.actionAccountsListFragmentToViewPagerFragmentIncome(
                            account.accountID!!, account.accountName
                        )
                    binding.root.findNavController().navigate(navDirections)
                }
//                vice versa
                else if(binding.root.findNavController().previousBackStackEntry!!.destination.id==R.id.recordsEntryFragmentExpense){
                    var navDirections =
                        AccountsListFragmentDirections.actionAccountsListFragmentToRecordsEntryFragmentExpense(
                            account.accountID!!, account.accountName
                        )
                    binding.root.findNavController().navigate(navDirections)
                }
            }
        }

        companion object {
            fun from(parent: ViewGroup, context: Context): AccountListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LoAccountListBinding.inflate(layoutInflater, parent, false)
                return AccountListViewHolder(binding, context)
            }
        }
    }



    override fun getItemViewType(position: Int): Int {
        when (accounts[position].type) {
            0 -> return Account.ACCOUNT_CARD
            1 -> return Account.ACCOUNT_LIST
            2 -> return Account.ACCOUNT_SETTINGS
        }
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        var view : View

        when (viewType) {
            Account.ACCOUNT_CARD -> {
                return AccountCardViewHolder.from(parent)
            }

            Account.ACCOUNT_LIST -> {
                return AccountListViewHolder.from(parent, context)
            }

            Account.ACCOUNT_SETTINGS -> {
                return AccountSettingsViewHolder.from(parent, context)
            }
        }
        return AccountCardViewHolder.from(parent)
    }

    override fun getItemCount(): Int {
        return accounts.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        Setting the account name and balance of the text views in the card layout
        when (accounts[position].type) {
            Account.ACCOUNT_CARD -> {
                val account = accounts[position]
                (holder as AccountCardViewHolder).bind(account, position)
            }

            Account.ACCOUNT_LIST->{
                val account = accounts[position]
                (holder as AccountListViewHolder).bind(account,position)
            }

            Account.ACCOUNT_SETTINGS->{
                val account = accounts[position]
                (holder as AccountSettingsViewHolder).bind(account,position)
            }
        }

    }
}