package com.example.batwa.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.batwa.database.Account
import com.example.batwa.databinding.LoAccountCardBinding
import com.example.batwa.databinding.LoAccountListBinding


class AccountAdapter(
    val accountAdapterType : String
) : ListAdapter<com.example.batwa.database.Account,RecyclerView.ViewHolder>(accountUtil()) {

//    Static members representing the adapter type
    companion object{
        val ACCOUNT_CARD : String = "account_card"
        val ACCOUNT_LIST : String = "account_list"
    }

//    Account card adapter type
    inner class AccountCardViewHolder(val binding: LoAccountCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindCard(card_account: com.example.batwa.database.Account){
            binding.textViewAccountBalance.text="PKR ${card_account.accountBalance}"
            binding.textViewAccountName.text=card_account.accountName
        }
    }

//    Account list adapter type
    inner class AccountListViewHolder(val binding : LoAccountListBinding):
    RecyclerView.ViewHolder(binding.root){

        fun bindList(list_account : Account){
            binding.textViewAccountName.text=list_account.accountName
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : RecyclerView.ViewHolder {
        if (accountAdapterType== ACCOUNT_CARD)
            return AccountCardViewHolder(LoAccountCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
        else if (accountAdapterType== ACCOUNT_LIST)
            return AccountListViewHolder(LoAccountListBinding.inflate(LayoutInflater.from(parent.context),parent,false))

//        Default return to account card view holder However, the code should never reach here.
        return AccountCardViewHolder(LoAccountCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (accountAdapterType== ACCOUNT_CARD)
            (holder as AccountCardViewHolder).bindCard(getItem(position))
        else if (accountAdapterType== ACCOUNT_LIST)
            (holder as AccountListViewHolder).bindList(getItem(position))
    }


}

class accountUtil() : DiffUtil.ItemCallback<com.example.batwa.database.Account>(){
    override fun areItemsTheSame(
        oldItem: com.example.batwa.database.Account,
        newItem: com.example.batwa.database.Account
    ): Boolean {
        return oldItem.accountID==newItem.accountID
    }

    override fun areContentsTheSame(
        oldItem: com.example.batwa.database.Account,
        newItem: com.example.batwa.database.Account
    ): Boolean {
        return oldItem==newItem
    }

}