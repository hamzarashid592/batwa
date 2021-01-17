package com.example.batwa

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.fragment_transaction_add_edit.*
import java.util.*
import kotlin.collections.ArrayList


class TransactionAddEditFragment : Fragment() {


    private var accountNames = ArrayList<String>()

    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        //Context becomes available here..
        dbHelper = DBHelper(context, null)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transaction_add_edit, container, false)
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        accountNames = dbHelper.getAccountNamesList()


        text_view_date.setOnClickListener{

//            Getting the current date.
            val calendar=Calendar.getInstance()

            val datePickerDialog = context?.let { it1 ->
                DatePickerDialog(
                    it1,
                    DatePickerDialog.OnDateSetListener { datePicker: DatePicker, year: Int, month: Int, day: Int ->
                        text_view_date.text = "$year/$month/$day"
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }

        text_view_time.setOnClickListener{

//            Getting the current date.
            val calendar=Calendar.getInstance()

            val datePickerDialog = context?.let { it1 ->
                TimePickerDialog(
                    it1,
                    TimePickerDialog.OnTimeSetListener() { timePicker: TimePicker, hour: Int, minute: Int ->
                        if(hour>12)
                            text_view_time.text = "${hour%12}:$minute PM"
                        else
                            text_view_time.text = "${hour%12}:$minute AM"
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    false
                ).show()
            }
        }


        //      Making adapter and populating the spinner for the account names
        var adapter = context?.let {
            ArrayAdapter(
                it,
                android.R.layout.simple_spinner_dropdown_item,
                accountNames
            )
        }
        spinner_account.adapter = adapter

        //        Populating the spinner for the tran type.
        adapter = context?.let {
            ArrayAdapter(
                it, android.R.layout.simple_spinner_dropdown_item,
                arrayOf(Transaction.INCOME, Transaction.EXPENSE)
            )
        }
        spinner_tran_type.adapter = adapter

    }

}