package com.example.batwa

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.batwa.databinding.FragmentHomeBinding
import com.example.batwa.databinding.FragmentTransactionAddEditBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private var accountList = ArrayList<Account>()
    private var accountMap = HashMap<Int, Account>()
    private var transactionList = ArrayList<Transaction>()
    private lateinit var dbHelper: DBHelper

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
        //Context becomes available here..
        dbHelper = DBHelper(context, null)
    }

    //    Doing the following stuff here.
//    Making the main fab to rotate and appearance of the income and expense fabs.
//    On click listeners for all the views necessary.
//    Navigation to other fragments.
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        //        Creating an onclick listener on the accounts cog wheel.
        binding.iconAccountsSettings.setOnClickListener {
            var navDirections =
                HomeFragmentDirections.actionHomeFragmentToAccountsSettingsFragment()
            findNavController().navigate(navDirections)
        }

        //  Code for the show more button that will start the transactions fragment
        binding.textViewShowMoreTransactions.setOnClickListener {
            var navDirections = HomeFragmentDirections.actionHomeFragmentToTransactionsFragment()
            findNavController().navigate(navDirections)
        }


//        Code for making the income and expense fabs to appear.
        binding.fabAdd.setOnClickListener {
            if (fab_state == false) {
                fab_state = true
                binding.fabAdd.startAnimation(fab_rot_clockwise)

                binding.fabExpense.visibility = View.VISIBLE
                binding.fabExpense.isClickable = true
                binding.fabExpense.startAnimation(fab_go_up)
                binding.fabIncome.visibility = View.VISIBLE
                binding.fabIncome.isClickable = true
                binding.fabIncome.startAnimation(fab_go_up)
            } else {
                fab_state = false
                binding.fabAdd.startAnimation(fab_rot_anticlockwise)

                binding.fabExpense.visibility = View.INVISIBLE
                binding.fabExpense.isClickable = false
                binding.fabExpense.startAnimation(fab_go_down)
                binding.fabIncome.visibility = View.INVISIBLE
                binding.fabIncome.isClickable = false
                binding.fabIncome.startAnimation(fab_go_down)
            }

        }

        binding.fabExpense.setOnClickListener {
            fab_state=false //Making the fab to get to its original position before leaving the home fragment.

            var navDirections =
                HomeFragmentDirections.actionHomeFragmentToRecordsEntryFragmentExpense(0,null)
            view.findNavController().navigate(navDirections)
        }

        binding.fabIncome.setOnClickListener {
            fab_state=false //Making the fab to get to its original position before leaving the home fragment.

            var navDirections =
                HomeFragmentDirections.actionHomeFragmentToViewPagerFragmentIncome(0,null)
            view.findNavController().navigate(navDirections)
        }


        return binding.root
    }


    //    Doing the following stuff here.
//    Fetching data from the database and populating the recycler views.
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        Fetching the account details to be displayed on the main screen as list..
        accountList = dbHelper.getAccountRecordList()

//        Fetching the transaction details to be displayed on the main screen.
        transactionList = dbHelper.getTransactionRecord()

//        Fetching the accounts map to be passed on with the transactions.
        accountMap = dbHelper.getAccountRecordMap()

        Log.d("hamza", "No of transactions ${transactionList.size}")

//        Passing the account details to the adapter to be displayed as cards in the main screen.
        val accountsLayoutManager = GridLayoutManager(context, 3, GridLayoutManager.VERTICAL, false)
        binding.accountsRecyclerView.layoutManager = accountsLayoutManager
        binding.accountsRecyclerView.adapter = context?.let { AccountsAdapter(it, accountList) }

//        Passing the transaction details to the adapter to be displayed on the main screen.
        val transactionsLayoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.mainScreenTransactionsRecyclerView.layoutManager = transactionsLayoutManager
        binding.mainScreenTransactionsRecyclerView.adapter =
            context?.let { TransactionsAdapter(it, transactionList, accountMap) }


    }
}
