package com.rysanek.tipconverter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.rysanek.tipconverter.R
import com.rysanek.tipconverter.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater)
        setOnClickListeners()
        return binding.root
    }

    private fun setOnClickListeners(){
        binding.apply {

            cvTipCalculator.setOnClickListener {
                it.findNavController().navigate(R.id.action_homeFragment_to_fragmentTipCalculator)
            }

            cvCurrencyConverter.setOnClickListener {
            
            }
            
            materialCardView3.setOnClickListener {
            
            }
        }
    }

}