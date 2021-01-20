package com.example.batwa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_view_pager.view.*


class ViewPagerFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view= inflater.inflate(R.layout.fragment_view_pager, container, false)

        val fragmentList= arrayListOf(RecordsEntryFragmentIncome(),
            TransactionAddEditFragment())

        val viewPagerAdapter=viewPagerAdapter(fragmentList,requireActivity().supportFragmentManager,lifecycle)

        view.viewPager2.adapter=viewPagerAdapter

        return view
    }

}