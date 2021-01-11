package com.example.batwa

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.lo_transaction.view.*

class TransactionsAdapter(
        private val context: Context,
        private val transactions: ArrayList<Transaction>,
        private val accountMap : HashMap<Int,String>
) : RecyclerView.Adapter<TransactionsAdapter.myViewHolder>() {



    inner class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pos=0

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        var view= LayoutInflater.from(context).inflate(R.layout.lo_transaction,parent,false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return transactions.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

//        Setting the details of the transaction view.
        if (transactions[position].transactionType=="Expense") {
            holder.itemView.tran_ammount.text = "-PKR ${transactions[position].transactionAmount}"
            holder.itemView.tran_ammount.setTextColor(Color.rgb(255,0,0))
        }
        else {
            holder.itemView.tran_ammount.text = "+PKR ${transactions[position].transactionAmount}"
            holder.itemView.tran_ammount.setTextColor(Color.rgb(0,255,0))
        }

        holder.itemView.tran_comments.text=transactions[position].transactionComments.toString()
        holder.itemView.tran_date.text=transactions[position].transactionDate.toString()
        holder.itemView.tran_account.text=accountMap[transactions[position].accountID]



    }
}