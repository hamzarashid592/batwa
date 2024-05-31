package com.example.batwa

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.batwa.databinding.LoAccountCardBinding
import com.example.batwa.databinding.LoTransactionFragmentBinding
import com.example.batwa.databinding.LoTransactionMainScreenBinding

//We also need the account record here as we would be showing stuff like account name, remaining balance etc.
class TransactionsAdapter(
        private val context: Context,
        private val transactions: ArrayList<Transaction>,
        private val accounts: HashMap<Int, Account>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    class MainScreenTransactionsVH(private val binding: LoTransactionMainScreenBinding) : RecyclerView.ViewHolder(binding.root) {
        var pos = 0

        fun bind(transaction: Transaction, position: Int, accounts: HashMap<Int, Account>) {
            //        Setting the details of the transaction view.
            if (transaction.transactionType == "Expense") {
                binding.tranAmmount.text = "-PKR ${transaction.transactionAmount}"
                binding.tranAmmount.setTextColor(Color.rgb(255, 0, 0))
            } else {
                binding.tranAmmount.text = "+PKR ${transaction.transactionAmount}"
                binding.tranAmmount.setTextColor(Color.rgb(0, 255, 0))
            }

            binding.tranComments.text = transaction.transactionComments.toString()
            binding.tranDate.text = transaction.transactionDate.toString()

//        Getting account id.
            val id = transaction.accountID

//        Referencing the account name. The not null operator is for the case when accountID would be null which will never happen.
            binding.tranAccount.text = accounts.get(id)!!.accountName
        }

        companion object {
            fun from(parent: ViewGroup): TransactionsAdapter.MainScreenTransactionsVH {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LoTransactionMainScreenBinding.inflate(layoutInflater, parent, false)
                return TransactionsAdapter.MainScreenTransactionsVH(binding)
            }
        }

    }

    class FragmentTransactionsVH(private val binding: LoTransactionFragmentBinding) : RecyclerView.ViewHolder(binding.root) {
        var pos = 0

        fun bind(transaction: Transaction, position: Int, accounts: HashMap<Int, Account>) {
            //        Setting the details of the transaction view.
            if (transaction.transactionType == "Expense") {
                binding.tranAmmount.text = "-PKR ${transaction.transactionAmount}"
                binding.tranAmmount.setTextColor(Color.rgb(255, 0, 0))
            } else {
                binding.tranAmmount.text = "+PKR ${transaction.transactionAmount}"
                binding.tranAmmount.setTextColor(Color.rgb(0, 255, 0))
            }
            binding.tranComments.text = transaction.transactionComments.toString()
            binding.tranDate.text = transaction.transactionDate.toString()

//              Getting account id.
            val id = transaction.accountID

//              Referencing the account name. The not null operator is for the case when accountID would be null which will never happen.
            binding.tranAccount.text = accounts.get(id)!!.accountName
        }
        companion object {
            fun from(parent: ViewGroup): TransactionsAdapter.FragmentTransactionsVH {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LoTransactionFragmentBinding.inflate(layoutInflater, parent, false)
                return TransactionsAdapter.FragmentTransactionsVH(binding)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
        when (transactions[position].type) {
            0 -> return Transaction.TRANSACTION_MAIN_SCREN
            1 -> return Transaction.TRANSACTION_FRAGMENT
        }
//        Default return
        return 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view: View
        when (viewType) {
            Transaction.TRANSACTION_MAIN_SCREN -> {
                return MainScreenTransactionsVH.from(parent)
            }
            Transaction.TRANSACTION_FRAGMENT -> {
                return  FragmentTransactionsVH.from(parent)
            }
        }
//        Default return
        return MainScreenTransactionsVH.from(parent)
    }
    override fun getItemCount(): Int {
        return transactions.size
    }
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (transactions[position].type) {
            Transaction.TRANSACTION_MAIN_SCREN -> {
                val transaction = transactions[position]
                (holder as MainScreenTransactionsVH).bind(transaction, position, accounts)
            }
            Transaction.TRANSACTION_FRAGMENT -> {
                val transaction = transactions[position]
                (holder as FragmentTransactionsVH).bind(transaction, position, accounts)
            }
        }
    }
}