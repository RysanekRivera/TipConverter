package com.rysanek.tipconverter.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject
import kotlin.properties.Delegates

class TipViewModel: ViewModel() {
//
    private var _billTotal = MutableLiveData<Double>()
//    val billTotal: LiveData<Double> = _billTotal

    private var _split = MutableLiveData<Int>()
    val split: LiveData<Int> = _split

    private var _percent = MutableLiveData<Int>()
//    val percent: LiveData<Int> = _percent

    private var _total = MutableLiveData<String>()
    val total: LiveData<String> = _total

    private var _tip = MutableLiveData<String>()
    val tip: LiveData<String> = _tip

    init {
        _split.value = 1
    }

    fun calculateTotal() {
        val percentage = _percent.value?.toDouble()?.div(100)
        val finalTip = (_billTotal.value!! * percentage!!) / _split.value!!
        val total = _billTotal.value!! / _split.value!! + finalTip
        val play = total.toString().format(Locale.getDefault(), "%.2f")
        Log.d("calculate", "play total: $play, percentage: $percentage" )
        
        val tipStr = when (split.value) {
            1 -> "$ ${String.format(Locale.getDefault(), "%.2f", finalTip)}"
            else -> "$ ${String.format(Locale.getDefault(), "%.2f", finalTip)} each"
        }
        
        val totalStr = when (split.value) {
            1 -> "$ ${String.format(Locale.getDefault(), "%.2f", total)}"
            else -> "$ ${String.format(Locale.getDefault(), "%.2f", total)} each"
        }
        _tip.postValue(tipStr)
        _total.postValue(totalStr)
    }

    fun plus() {
        _split.value!!.plus(1).also { _split.value = it }
        Log.d("TipViewModel", "plus called, split: ${_split.value}")
    }

    fun minus() {
        _split.value!!.let {
            if (it > 1) {
                _split.value = it.minus(1)
            }
        }
    }
    
    fun setBillTotal(total:Double) {
        _billTotal.value = total
        Log.d("billTotal", "billTotal: ${_billTotal.value}")
    }
    
    fun setPercent(percent: Int) {
        _percent.value = percent
        Log.d("Percent", "Percent: ${_percent.value}")
    }
    fun setSplit(split: Int) {
        _split.value = split
    }
}