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
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.batwa.databinding.FragmentTransactionAddEditBinding
import java.text.DecimalFormat
import java.util.*
import kotlin.collections.ArrayList


class TransactionAddEditFragment : Fragment() {


    private var accountNames = ArrayList<String>()
    private var _binding: FragmentTransactionAddEditBinding? = null
    private val binding get() = _binding!!

    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
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
        _binding = FragmentTransactionAddEditBinding.inflate(inflater, container, false)
        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        accountNames = dbHelper.getAccountNamesList()


//        ---------------------If this fragment has been activated via the records entry path---------------------

//        I am getting the navController below. Used this method as findNavController wasn't working.
        val navHostFragment = requireActivity().supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
        val navController = navHostFragment.navController


        if (navController.previousBackStackEntry!!.destination.id==R.id.homeFragment){
//            Preset the date and time to the current date and time.
            val calendar= Calendar.getInstance()
            val cur_year=calendar.get(Calendar.YEAR)
            val cur_month=calendar.get(Calendar.MONTH)
            val cur_day=calendar.get(Calendar.DAY_OF_MONTH)
            val cur_hour=calendar.get(Calendar.HOUR_OF_DAY)
            val cur_minute=calendar.get(Calendar.MINUTE)

//            Setting the date
            val decimalFormat=DecimalFormat("00")
            binding.textViewDate.text="${decimalFormat.format(cur_day)}/${decimalFormat.format(cur_month+1)}/$cur_year"

//        Setting the time.
            if(cur_hour>12)
                binding.textViewTime.text = "${decimalFormat.format(cur_hour%12)}:${decimalFormat.format(cur_minute)} PM"
            else
                binding.textViewTime.text = "${decimalFormat.format(cur_hour%12)}:${decimalFormat.format(cur_minute)} AM"


            binding.spinnerTranType.setSelection(1)

        }


//        ---------------------Defaults for this fragment---------------------
        binding.textViewDate.setOnClickListener{

//            Getting the current date.
            val calendar=Calendar.getInstance()

            val datePickerDialog = context?.let { it1 ->
                DatePickerDialog(
                    it1,
                    DatePickerDialog.OnDateSetListener { datePicker: DatePicker, year: Int, month: Int, day: Int ->

                        var decimalFormat=DecimalFormat("00")

                        binding.textViewDate.text = "${decimalFormat.format(day)}/${decimalFormat.format(month+1)}/$year"
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        }

        binding.textViewTime.setOnClickListener{

//            Getting the current date.
            val calendar=Calendar.getInstance()

            val datePickerDialog = context?.let { it1 ->
                TimePickerDialog(
                    it1,
                    TimePickerDialog.OnTimeSetListener() { timePicker: TimePicker, hour: Int, minute: Int ->

                        var decimalFormat=DecimalFormat("00")

                        if(hour>12)
                            binding.textViewTime.text = "${decimalFormat.format(hour%12)}:${decimalFormat.format(minute)} PM"
                        else
                            binding.textViewTime.text = "${decimalFormat.format(hour%12)}:${decimalFormat.format(minute)} AM"
                    },
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
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
        binding.spinnerAccount.adapter = adapter

        //        Populating the spinner for the tran type.
        adapter = context?.let {
            ArrayAdapter(
                it, android.R.layout.simple_spinner_dropdown_item,
                arrayOf(Transaction.INCOME, Transaction.EXPENSE)
            )
        }
        binding.spinnerTranType.adapter = adapter

    }

}