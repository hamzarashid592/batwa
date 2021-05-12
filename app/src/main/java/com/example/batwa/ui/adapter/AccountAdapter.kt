package com.example.batwa.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.batwa.database.Account
import com.example.batwa.databinding.LoAccountCardBinding
import com.example.batwa.databinding.LoAccountListBinding
import com.example.batwa.databinding.LoAccountSettingsBinding
import com.example.batwa.ui.AccountsSettingsFragmentDirections
import com.example.batwa.ui.BatwaViewModel
import com.example.batwa.ui.HomeFragmentDirections
import java.text.DecimalFormat


class AccountAdapter(
    val accountAdapterType: String,
    val viewModel: BatwaViewModel  //The view model should be passed here to save the selected account.
) : ListAdapter<com.example.batwa.database.Account, RecyclerView.ViewHolder>(accountUtil()) {

    //    Static members representing the adapter type
    companion object {
        val ACCOUNT_CARD: String = "account_card"
        val ACCOUNT_LIST: String = "account_list"
        val ACCOUNT_SETTINGS: String = "account_settings"
    }


    //    Account card adapter type
    inner class AccountCardViewHolder(val binding: LoAccountCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindCard(card_account: com.example.batwa.database.Account) {
            //To print comma separated digits.
            val decimalFormat=DecimalFormat("#.##")
            decimalFormat.groupingSize=3
            decimalFormat.isGroupingUsed=true
            binding.textViewAccountBalance.text = "PKR ${decimalFormat.format(card_account.accountBalance)}"
            binding.textViewAccountName.text = card_account.accountName
        }

    }

    //    Account settings adapter type
    inner class AccountSettingsViewHolder(val binding: LoAccountSettingsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //The position variable
        var pos = 0

        //The on click listener
        init {
            binding.root.setOnClickListener {
                binding.root.findNavController()
                    .navigate(AccountsSettingsFragmentDirections.actionAccountsSettingsFragmentToAccountEditFragment(getItem(pos)))
            }
        }
        fun bindSettings(settings_account: com.example.batwa.database.Account) {
            binding.textViewAccountName.text = settings_account.accountName
            val decimalFormat=DecimalFormat("#.##")
            decimalFormat.groupingSize=3
            decimalFormat.isGroupingUsed=true
            binding.textViewAccountBalance.text="PKR ${decimalFormat.format(settings_account.accountBalance)}"
        }
    }

    //    Account list adapter type
    inner class AccountListViewHolder(val binding: LoAccountListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //    Property to hold the current selected item.
        var selectedItem = Account()

        //        On click action for the list item.
        init {
            binding.root.setOnClickListener {
//                Setting the selected account
                viewModel.setSelectedAccount(selectedItem)
//                Navigating back to the record entry fragment.
                Navigation.findNavController(it).popBackStack()
            }
        }


        fun bindList(list_account: Account) {
            binding.textViewAccountName.text = list_account.accountName
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (accountAdapterType == ACCOUNT_CARD)
            return AccountCardViewHolder(
                LoAccountCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        else if (accountAdapterType == ACCOUNT_SETTINGS)
            return AccountSettingsViewHolder(
                LoAccountSettingsBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        else if (accountAdapterType == ACCOUNT_LIST)
            return AccountListViewHolder(
                LoAccountListBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

//        Default return to account card view holder However, the code should never reach here.
        return AccountCardViewHolder(
            LoAccountCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        if (accountAdapterType == ACCOUNT_CARD) {
            (holder as AccountCardViewHolder).bindCard(getItem(position))
        } else if (accountAdapterType == ACCOUNT_SETTINGS) {
            //Storing the position.
            (holder as AccountSettingsViewHolder).pos=position
            //Binding the view
            (holder as AccountSettingsViewHolder).bindSettings(getItem(position))
        } else if (accountAdapterType == ACCOUNT_LIST) {
            val listHolder = (holder as AccountListViewHolder)
//            Saving the selected item as the property of the account list class for later use.
            listHolder.selectedItem = getItem(position)
            listHolder.bindList(listHolder.selectedItem)
        }
    }


}

class accountUtil() : DiffUtil.ItemCallback<com.example.batwa.database.Account>() {
    override fun areItemsTheSame(
        oldItem: com.example.batwa.database.Account,
        newItem: com.example.batwa.database.Account
    ): Boolean {
        return oldItem.accountID == newItem.accountID
    }

    override fun areContentsTheSame(
        oldItem: com.example.batwa.database.Account,
        newItem: com.example.batwa.database.Account
    ): Boolean {
        return oldItem == newItem
    }

}