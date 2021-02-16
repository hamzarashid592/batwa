package com.example.batwa.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.example.batwa.R
import com.example.batwa.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {


    //    private var accountList = ArrayList<Account>()
//    private var accountMap = HashMap<Int, Account>()
//    private var transactionList = ArrayList<Transaction>()
//
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
    val fab_go_up by lazy { AnimationUtils.loadAnimation(context, R.anim.fab_bottom_up) }
    val fab_go_down by lazy { AnimationUtils.loadAnimation(context, R.anim.fab_bottom_down) }
    var fab_state = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
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
//
//        //        Creating an onclick listener on the accounts cog wheel.
//        view.icon_accounts_settings.setOnClickListener {
//            var navDirections =
//                HomeFragmentDirections.actionHomeFragmentToAccountsSettingsFragment()
//            findNavController().navigate(navDirections)
//        }
//
//        //  Code for the show more button that will start the transactions fragment
//        view.text_view_show_more_transactions.setOnClickListener {
//            var navDirections = HomeFragmentDirections.actionHomeFragmentToTransactionsFragment()
//            findNavController().navigate(navDirections)
//        }
//
//
//        Code for making the income and expense fabs to appear and disappear.
        binding.fabAdd.setOnClickListener {
            if (fab_state == false) {
                fab_state = true
                binding.apply {
                    fabAdd.startAnimation(fab_rot_clockwise)

                    fabExpense.visibility = View.VISIBLE
                    fabExpense.isClickable = true
                    fabExpense.startAnimation(fab_go_up)
                    fabIncome.visibility = View.VISIBLE
                    fabIncome.isClickable = true
                    fabIncome.startAnimation(fab_go_up)
                }

            } else {
                fab_state = false
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
//
//        view.fab_expense.setOnClickListener {
//            fab_state=false //Making the fab to get to its original position before leaving the home fragment.
//
//            var navDirections =
//                HomeFragmentDirections.actionHomeFragmentToRecordsEntryFragmentExpense(0,null)
//            view.findNavController().navigate(navDirections)
//        }
//
//        view.fab_income.setOnClickListener {
//            fab_state=false //Making the fab to get to its original position before leaving the home fragment.
//
//            var navDirections =
//                HomeFragmentDirections.actionHomeFragmentToViewPagerFragmentIncome(0,null)
//            view.findNavController().navigate(navDirections)
//        }


        return binding.root
    }


    //    Doing the following stuff here.
//    Fetching data from the database and populating the recycler views.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


////        Fetching the account details to be displayed on the main screen as list..
//        accountList = dbHelper.getAccountRecordList()
//
////        Fetching the transaction details to be displayed on the main screen.
//        transactionList = dbHelper.getTransactionRecord()
//
////        Fetching the accounts map to be passed on with the transactions.
//        accountMap = dbHelper.getAccountRecordMap()
//
//        Log.d("hamza", "No of transactions ${transactionList.size}")
//
////        Passing the account details to the adapter to be displayed as cards in the main screen.
//        val accountsLayoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
//        accounts_recycler_view.layoutManager = accountsLayoutManager
//        accounts_recycler_view.adapter = context?.let { AccountsAdapter(it, accountList) }
//
////        Passing the transaction details to the adapter to be displayed on the main screen.
//        val transactionsLayoutManager =
//            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//        main_screen_transactions_recycler_view.layoutManager = transactionsLayoutManager
//        main_screen_transactions_recycler_view.adapter =
//            context?.let { TransactionsAdapter(it, transactionList, accountMap) }


    }
}
