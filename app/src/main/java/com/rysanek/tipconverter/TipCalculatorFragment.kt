package com.rysanek.tipconverter

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rysanek.tipconverter.databinding.FragmentTipCalculatorBinding
import com.rysanek.tipconverter.other.Constants.PERCENT_15
import com.rysanek.tipconverter.other.Constants.PERCENT_18
import com.rysanek.tipconverter.other.Constants.PERCENT_20
import com.rysanek.tipconverter.other.btnSelection
import com.rysanek.tipconverter.other.hideKeyboard
import com.rysanek.tipconverter.other.showSnackBar
import com.rysanek.tipconverter.viewmodels.TipViewModel
import dagger.hilt.android.AndroidEntryPoint

@RequiresApi(Build.VERSION_CODES.R)
class TipCalculatorFragment: Fragment() {
    
    private val tipViewModel: TipViewModel by viewModels()
    
    private lateinit var binding: FragmentTipCalculatorBinding
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTipCalculatorBinding.inflate(layoutInflater)
        binding.tipViewModel = tipViewModel
        binding.lifecycleOwner = this
        
        setOnClickListeners()
        
        return binding.root
    }
    
    private fun setOnClickListeners() {
        binding.btPercent15.setOnClickListener {
            tipViewModel.setPercent(PERCENT_15)
            binding.btnSelection()
            hideKeyboard()
            if (binding.tvBillTotal.text.isNullOrEmpty()) {
                showSnackBar("Please enter the Bill Total")
            } else {
                tipViewModel.setBillTotal(binding.tvBillTotal.text.toString().toDouble())
                binding.btCalculate.visibility = View.VISIBLE
            }
        }
        
        binding.btPercent18.setOnClickListener {
            tipViewModel.setPercent(PERCENT_18)
            binding.btnSelection()
            hideKeyboard()
            
            if (binding.tvBillTotal.text.isNullOrEmpty()) {
                showSnackBar("Please enter the Bill Total")
            } else {
                tipViewModel.setBillTotal(binding.tvBillTotal.text.toString().toDouble())
                binding.btCalculate.visibility = View.VISIBLE
            }
        }
        
        binding.btPercent20.setOnClickListener {
            tipViewModel.setPercent(PERCENT_20)
            binding.btnSelection()
            hideKeyboard()
            
            if (binding.tvBillTotal.text.isNullOrEmpty()) {
                showSnackBar("Please enter the Bill Total")
            } else {
                tipViewModel.setBillTotal(binding.tvBillTotal.text.toString().toDouble())
                binding.btCalculate.visibility = View.VISIBLE
            }
        }
        
        binding.btCustom.setOnClickListener {
            binding.btnSelection()
            hideKeyboard()
            
            if (binding.tvBillTotal.text?.toString().isNullOrEmpty() || binding.etSplit.text?.toString()
                    ?.toInt()!! <= 0
            ) {
                showSnackBar("Please enter the Bill Total")
//                it.clearFocus()
            }
        }
        
        binding.btCalculate.setOnClickListener {
            hideKeyboard()
            tipViewModel.calculateTotal()
            binding.tvTipAmount.visibility = View.VISIBLE
            binding.tvTotal.visibility = View.VISIBLE
            binding.tvTipAmountResult.visibility = View.VISIBLE
            binding.tvTotalAmountResult.visibility = View.VISIBLE
            
        }
        
        binding.tvBillTotal.setOnEditorActionListener { v, actionId, event ->
            if (actionId == KeyEvent.ACTION_DOWN || actionId == EditorInfo.IME_ACTION_DONE || actionId == KeyEvent.KEYCODE_ENTER) {
                hideKeyboard()
                v.clearFocus()
                if (binding.tvBillTotal.text.isNullOrEmpty()) {
                    showSnackBar("Please enter the Bill Total")
                } else {
                    if (binding.tvBillTotal.text.toString().toDouble() > 0) {
                        tipViewModel.setBillTotal(binding.tvBillTotal.text.toString().toDouble())
                    } else {
                        showSnackBar("Please enter a valid Bill Total")
                    }
                }
            }
            true
        }
        
        /**Allows this TextView [etSplit] to save the value written and dismiss the focus and Keyboard after.**/
        binding.etSplit.setOnEditorActionListener { textView, keyCode, _ ->
            if (keyCode == KeyEvent.ACTION_DOWN || keyCode == EditorInfo.IME_ACTION_DONE || keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_NAVIGATE_NEXT) {
                if (binding.etSplit.text.isNullOrEmpty()) {
                    showSnackBar("Please fill out the Split field correctly")
                } else {
                    if (binding.etSplit.text.toString().toInt() > 0) {
                        textView.clearFocus()
                        tipViewModel.setSplit(binding.etSplit.text.toString().toInt())
                    } else {
                        showSnackBar("Invalid input")
                    }
                }
            }
            hideKeyboard()
            true
        }
        
        binding.tilCustomTip.setEndIconActivated(true)
        
        binding.tilCustomTip.setEndIconOnClickListener {
            hideKeyboard()
            binding.tvCustomTip.clearFocus()
            
            if (binding.tvCustomTip.text.isNullOrEmpty()) {
                showSnackBar("Please enter the Tip %")
            } else {
                if (binding.tvCustomTip.text.toString().toInt() > 0) {
                    tipViewModel.setPercent(binding.tvCustomTip.text.toString().toInt())
                } else {
                    showSnackBar("Please enter a valid percent")
                }
                
                if (binding.tvBillTotal.text.isNullOrEmpty()) {
                    showSnackBar("Please enter the Bill Total")
                } else {
                    tipViewModel.setBillTotal(binding.tvBillTotal.text?.toString()?.toDouble()!!)
                    binding.btCalculate.visibility = View.VISIBLE
                }
            }
        }
        
        binding.tvCustomTip.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == KeyEvent.ACTION_DOWN || actionId == EditorInfo.IME_ACTION_DONE || actionId == KeyEvent.KEYCODE_ENTER) {
                hideKeyboard()
                binding.tvCustomTip.clearFocus()
    
                if (binding.tvCustomTip.text.isNullOrEmpty()) {
                    showSnackBar("Please enter the Tip %")
                } else {
                    if (binding.tvCustomTip.text.toString().toInt() > 0) {
                        tipViewModel.setPercent(binding.tvCustomTip.text.toString().toInt())
                    } else {
                        showSnackBar("Please enter a valid percent")
                    }
        
                    if (binding.tvBillTotal.text.isNullOrEmpty()) {
                        showSnackBar("Please enter the Bill Total")
                    } else {
                        tipViewModel.setBillTotal(binding.tvBillTotal.text?.toString()?.toDouble()!!)
                        binding.btCalculate.visibility = View.VISIBLE
                    }
                }
            }
            true
        }
    }
    
}

