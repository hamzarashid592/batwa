package com.example.batwa.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.batwa.databinding.LoAccountCardBinding



class AccountAdapter : ListAdapter<com.example.batwa.database.Account,AccountAdapter.AccountCardViewHolder>(util()) {

    inner class AccountCardViewHolder(val binding: LoAccountCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindCard(card_account: com.example.batwa.database.Account){
            binding.textViewAccountBalance.text="PKR ${card_account.accountBalance}"
            binding.textViewAccountName.text=card_account.accountName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountCardViewHolder {
        return AccountCardViewHolder(LoAccountCardBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: AccountCardViewHolder, position: Int) {

        holder.bindCard(getItem(position))

    }
}

class util() : DiffUtil.ItemCallback<com.example.batwa.database.Account>(){
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