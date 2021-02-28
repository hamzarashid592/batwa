package com.example.batwa.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.batwa.R
import com.example.batwa.database.WalletTransaction
import com.example.batwa.databinding.FragmentHomeBinding
import com.example.batwa.ui.adapter.AccountAdapter
import com.example.batwa.ui.adapter.WalletTransactionAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {


    //    Declaring animations and variables for the fab.
    val fab_rot_clockwise by lazy {
        AnimationUtils.loadAnimation(
            context,
            R.anim.fab_rotate_clockwise
        )
    }
    val fab_rot_anticlockwise by lazy {
        AnimationUtils.loadAnimation(
            context,
            R.anim.fab_rotate_anticlockwise
        )
    }
    val fab_go_up by lazy {
        AnimationUtils.loadAnimation(context, R.anim.fab_bottom_up)
    }
    val fab_go_down by lazy {
        AnimationUtils.loadAnimation(context, R.anim.fab_bottom_down)
    }
    var fab_state = false

    //    Creating the view model.
    private val batwaViewModel: BatwaViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    //    Doing the following stuff here.
//    Making the main fab to rotate and appearance of the income and expense fabs.
//    On click listeners for all the views necessary.
//    Navigation to other fragments.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Doing view binding for the fragment
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

//        Instantiating the adapters.
        val accountAdapter = AccountAdapter(AccountAdapter.ACCOUNT_CARD,batwaViewModel)
        val walletTransactionAdapter = WalletTransactionAdapter()

//--------------------------------------------UI Animations--------------------------------------------

//        Code for making the income and expense fabs to appear and disappear.
        binding.fabAdd.setOnClickListener {
            if (fab_state == false) {
                fab_state = true
                showRecordEntryFabs(binding)
            } else {
                fab_state = false
                hideRecordEntryFabs(binding)
            }
        }


//--------------------------------------------Navigation Actions--------------------------------------------
        binding.buttonAddAccount.setOnClickListener {
            it.findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToAccountAddEditFragment())
        }

//        Addition of Expense
        binding.fabExpense.setOnClickListener {
            // Returning the fab to the original state
            if (fab_state == true) {
                fab_state = false
                hideRecordEntryFabs(binding)
            }
            //Navigating to the fragment
            it.findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToRecordsEntryFragmentExpense())
        }


//--------------------------------------------Button click listeners-------------------------------------------


//--------------------------------------------Live Data Listeners--------------------------------------------
//        Displaying the accounts in the accounts recycler view (card)
        batwaViewModel.allAccounts.observe(viewLifecycleOwner) {

//            Submitting the data to the adapter
            accountAdapter.submitList(it)

            binding.apply {
//                Configuring the recycler view
                accountsRecyclerView.layoutManager =
                    GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
                accountsRecyclerView.adapter = accountAdapter
            }
        }

//    Displaying the transactions on the main screen.
        batwaViewModel.allTransactions.observe(viewLifecycleOwner) {

//            Submitting the transactions to the transaction adapter.
            walletTransactionAdapter.submitList(it)

            binding.apply {
//                Configuring the recycler view
                mainScreenTransactionsRecyclerView.layoutManager = LinearLayoutManager(
                    context, LinearLayoutManager.VERTICAL,
                    false
                )
                mainScreenTransactionsRecyclerView.adapter = walletTransactionAdapter
            }
        }


        return binding.root
    }


    //    Doing the following stuff here.
//    Fetching data from the database and populating the recycler views.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    //--------------------------------------------UI Animation Functions--------------------------------------------
    fun showRecordEntryFabs(binding: FragmentHomeBinding) {
        binding.apply {
            fabAdd.startAnimation(fab_rot_clockwise)

            fabExpense.visibility = View.VISIBLE
            fabExpense.isClickable = true
            fabExpense.startAnimation(fab_go_up)
            fabIncome.visibility = View.VISIBLE
            fabIncome.isClickable = true
            fabIncome.startAnimation(fab_go_up)
        }
    }

    fun hideRecordEntryFabs(binding: FragmentHomeBinding) {
        binding.apply {
            fabAdd.startAnimation(fab_rot_anticlockwise)

            fabExpense.visibility = View.INVISIBLE
            fabExpense.isClickable = false
            fabExpense.startAnimation(fab_go_down)
            fabIncome.visibility = View.INVISIBLE
            fabIncome.isClickable = false
            fabIncome.startAnimation(fab_go_down)
        }
    }


}
