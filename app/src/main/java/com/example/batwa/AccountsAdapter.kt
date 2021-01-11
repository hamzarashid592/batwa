package com.example.batwa

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.lo_account.view.*

class AccountsAdapter(
        private val context: Context,
        private val accounts: ArrayList<Account>
) : RecyclerView.Adapter<AccountsAdapter.myViewHolder>() {



    inner class myViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var pos=0
        init {
            itemView.setOnClickListener {
                Toast.makeText(context,"NumRecords ${accounts[pos].accountNumRecords} at pos $pos", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {
        var view= LayoutInflater.from(context).inflate(R.layout.lo_account,parent,false)
        return myViewHolder(view)
    }

    override fun getItemCount(): Int {
        return accounts.size
    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {

//        Setting the account name and balance of the text views
        holder.itemView.text_view_account_name.text=accounts[position].accountName
        holder.itemView.text_view_account_balance.text="PKR ${accounts[position].accountBalance.toString()}"

        holder.pos=position


    }
}