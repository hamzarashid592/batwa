package com.example.batwa.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.graphics.Color
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.batwa.database.AccountTransactionRelation
import com.example.batwa.database.AccountTransactionView
import com.example.batwa.database.WalletTransaction
import com.example.batwa.databinding.LoTransactionFragmentBinding
import com.example.batwa.databinding.LoTransactionMainScreenBinding
import com.example.batwa.ui.HomeFragmentDirections

class WalletTransactionAdapter(
    var adapterType: String
) :
    ListAdapter<AccountTransactionView, RecyclerView.ViewHolder>(transactionUtil()) {

    //Static members to hold the sub type of the adapter.
    companion object {
        val TRANSACTION_MAINSCREEN: String = "TransactionMainScreen"
        val TRANSACTION_LIST: String = "TransactionList"
    }

    inner class WalletTransactionMainScreenViewHolder(val binding: LoTransactionMainScreenBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var pos : Int = 0

        init {
            binding.root.setOnClickListener {
                it.findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToTransactionAddEditFragment(getItem(pos).transactionID!!)
                )
            }
        }

        fun bind(accountTransactionView: AccountTransactionView) {
            binding.tranAccount.text = accountTransactionView.accountName
            binding.tranAmount.text = "PKR ${accountTransactionView.transactionAmount}"
            binding.tranComments.text = accountTransactionView.transactionComments
            binding.tranDate.text = accountTransactionView.transactionDate
            if (accountTransactionView.transactionType == WalletTransaction.INCOME)
                binding.tranAmount.setTextColor(android.graphics.Color.GREEN)
            else
                binding.tranAmount.setTextColor(android.graphics.Color.RED)
        }
    }

    inner class WalletTransactionListViewHolder(val binding: LoTransactionFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(accountTransactionView: AccountTransactionView) {
            binding.tranAccount.text = accountTransactionView.accountName
            binding.tranAmount.text = "PKR ${accountTransactionView.transactionAmount}"
            binding.tranComments.text = accountTransactionView.transactionComments
            binding.tranDate.text = accountTransactionView.transactionDate
            binding.tranTime.text = accountTransactionView.transactionTime
            if (accountTransactionView.transactionType == WalletTransaction.INCOME)
                binding.tranAmount.setTextColor(android.graphics.Color.GREEN)
            else
                binding.tranAmount.setTextColor(android.graphics.Color.RED)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        if (adapterType == TRANSACTION_MAINSCREEN)
            return WalletTransactionMainScreenViewHolder(
                LoTransactionMainScreenBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        else if (adapterType == TRANSACTION_LIST)
            return WalletTransactionListViewHolder(
                LoTransactionFragmentBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )

//        The default case. The program will never get here.
        return WalletTransactionMainScreenViewHolder(
            LoTransactionMainScreenBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        Saving the position of the item.
        (holder as WalletTransactionMainScreenViewHolder).pos=position

        if (adapterType == TRANSACTION_MAINSCREEN)
            (holder as WalletTransactionMainScreenViewHolder).bind(getItem(position))
        else if (adapterType == TRANSACTION_LIST)
            (holder as WalletTransactionListViewHolder).bind(getItem(position))
    }
}

class transactionUtil() : DiffUtil.ItemCallback<AccountTransactionView>() {
    override fun areItemsTheSame(
        oldItem: AccountTransactionView,
        newItem: AccountTransactionView
    ): Boolean {
        return oldItem.transactionID == newItem.transactionID
    }

    override fun areContentsTheSame(
        oldItem: AccountTransactionView,
        newItem: AccountTransactionView
    ): Boolean {
        return oldItem == newItem
    }

}