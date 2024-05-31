package com.example.batwa

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.batwa.databinding.FragmentViewPagerIncomeBinding

class ViewPagerFragmentIncome : Fragment() {

    private var _binding : FragmentViewPagerIncomeBinding? = null
    private val binding get() = _binding!!

//    The selected account from other fragments.
    val args : ViewPagerFragmentIncomeArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentViewPagerIncomeBinding.inflate(inflater, container, false)

        val fragmentList= arrayListOf(RecordsEntryFragmentIncome(args), TransactionAddEditFragment())

        val viewPagerAdapter=viewPagerAdapter(fragmentList,requireActivity().supportFragmentManager,lifecycle)

        binding.viewPager2.adapter=viewPagerAdapter

        return binding.root
    }

}