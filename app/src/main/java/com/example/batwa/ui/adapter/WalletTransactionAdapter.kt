package com.example.batwa.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.graphics.Color
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.batwa.database.AccountTransactionRelation
import com.example.batwa.database.AccountTransactionView
import com.example.batwa.database.WalletTransaction
import com.example.batwa.databinding.LoTransactionMainScreenBinding

class WalletTransactionAdapter() :
    ListAdapter<AccountTransactionView, WalletTransactionAdapter.WalletTransactionMainScreenViewHolder>(transactionUtil()) {

    inner class WalletTransactionMainScreenViewHolder(val binding: LoTransactionMainScreenBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bindMainScreen(accountTransactionView: AccountTransactionView){
                binding.tranAccount.text=accountTransactionView.accountName
                binding.tranAmmount.text=accountTransactionView.transactionAmount.toString()
                binding.tranComments.text=accountTransactionView.transactionComments
                binding.tranDate.text=accountTransactionView.transactionDate
                binding.test.text=accountTransactionView.transactionID.toString()
                if (accountTransactionView.transactionType==WalletTransaction.INCOME)
                    binding.tranAmmount.setTextColor(android.graphics.Color.GREEN)
                else
                    binding.tranAmmount.setTextColor(android.graphics.Color.RED)
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WalletTransactionMainScreenViewHolder {
        return WalletTransactionMainScreenViewHolder(LoTransactionMainScreenBinding.inflate(
            LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: WalletTransactionMainScreenViewHolder, position: Int) {
        holder.bindMainScreen(getItem(position))
    }
}

class transactionUtil() : DiffUtil.ItemCallback<AccountTransactionView>(){
    override fun areItemsTheSame(
        oldItem: AccountTransactionView,
        newItem: AccountTransactionView
    ): Boolean {
        return oldItem.transactionID==newItem.transactionID
    }

    override fun areContentsTheSame(
        oldItem: AccountTransactionView,
        newItem: AccountTransactionView
    ): Boolean {
        return oldItem==newItem
    }

}