package com.example.batwa

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.lo_transaction_main_screen.view.tran_account as tran_account_main_screen
import kotlinx.android.synthetic.main.lo_transaction_main_screen.view.tran_ammount as tran_amount_main_screen
import kotlinx.android.synthetic.main.lo_transaction_main_screen.view.tran_comments as tran_comments_main_screen
import kotlinx.android.synthetic.main.lo_transaction_main_screen.view.tran_date as tran_date_main_screen

import kotlinx.android.synthetic.main.lo_transaction_fragment.view.tran_account as tran_account_fragment
import kotlinx.android.synthetic.main.lo_transaction_fragment.view.tran_ammount as tran_amount_fragment
import kotlinx.android.synthetic.main.lo_transaction_fragment.view.tran_comments as tran_comments_fragment
import kotlinx.android.synthetic.main.lo_transaction_fragment.view.tran_date as tran_date_fragment
//import kotlinx.android.synthetic.main.lo_transaction_fragment.view.remaining_balance as remaining_balance_fragment

//We also need the account record here as we would be showing stuff like account name, remaining balance etc.
class TransactionsAdapter(
        private val context: Context,
        private val transactions: ArrayList<Transaction>,
        private val accounts: HashMap<Int, Account>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    inner class MainScreenTransactionsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pos = 0
    }

    inner class FragmentTransactionsVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pos = 0
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
                view = LayoutInflater.from(context).inflate(R.layout.lo_transaction_main_screen, parent, false)
                return MainScreenTransactionsVH(view)
            }
            Transaction.TRANSACTION_FRAGMENT -> {
                view = LayoutInflater.from(context).inflate(R.layout.lo_transaction_fragment, parent, false)
                return FragmentTransactionsVH(view)
            }
        }

//        Default return
        view = LayoutInflater.from(context).inflate(R.layout.lo_transaction_main_screen, parent, false)
        return MainScreenTransactionsVH(view)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (transactions[position].type) {
            Transaction.TRANSACTION_MAIN_SCREN -> {
                //        Setting the details of the transaction view.
                if (transactions[position].transactionType == "Expense") {
                    holder.itemView.tran_amount_main_screen.text = "-PKR ${transactions[position].transactionAmount}"
                    holder.itemView.tran_amount_main_screen.setTextColor(Color.rgb(255, 0, 0))
                } else {
                    holder.itemView.tran_amount_main_screen.text = "+PKR ${transactions[position].transactionAmount}"
                    holder.itemView.tran_amount_main_screen.setTextColor(Color.rgb(0, 255, 0))
                }

                holder.itemView.tran_comments_main_screen.text = transactions[position].transactionComments.toString()
                holder.itemView.tran_date_main_screen.text = transactions[position].transactionDate.toString()

//        Getting account id.
                val id = transactions[position].accountID

//        Referencing the account name. The not null operator is for the case when accountID would be null which will never happen.
                holder.itemView.tran_account_main_screen.text = accounts.get(id)!!.accountName
            }

            Transaction.TRANSACTION_FRAGMENT -> {
                //        Setting the details of the transaction view.
                if (transactions[position].transactionType == "Expense") {
                    holder.itemView.tran_amount_fragment.text = "-PKR ${transactions[position].transactionAmount}"
                    holder.itemView.tran_amount_fragment.setTextColor(Color.rgb(255, 0, 0))
                } else {
                    holder.itemView.tran_amount_fragment.text = "+PKR ${transactions[position].transactionAmount}"
                    holder.itemView.tran_amount_fragment.setTextColor(Color.rgb(0, 255, 0))
                }

                holder.itemView.tran_comments_fragment.text = transactions[position].transactionComments.toString()
                holder.itemView.tran_date_fragment.text = transactions[position].transactionDate.toString()

//              Getting account id.
                val id = transactions[position].accountID

//              Referencing the account name. The not null operator is for the case when accountID would be null which will never happen.
                holder.itemView.tran_account_fragment.text = accounts.get(id)!!.accountName

            }

        }


    }
}