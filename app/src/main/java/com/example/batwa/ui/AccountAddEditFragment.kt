package com.example.batwa.ui

//import android.content.Context
//import android.os.Build
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.annotation.RequiresApi
//import kotlin.collections.ArrayList
//
//
//class AccountAddEditFragment : Fragment() {
//
//
//    private var accountNames = ArrayList<String>()
//
//    private lateinit var dbHelper: DBHelper
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }
//
//
//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//        //Context becomes available here..
//        dbHelper = DBHelper(context, null)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_account_add_edit, container, false)
//    }
//
//
//    @RequiresApi(Build.VERSION_CODES.N)
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//
//    }
//
//}