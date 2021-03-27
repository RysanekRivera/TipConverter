package com.rysanek.tipconverter.other

import android.os.Build
import android.view.View
import android.view.WindowInsets
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.rysanek.tipconverter.R
import com.rysanek.tipconverter.databinding.FragmentTipCalculatorBinding

@RequiresApi(Build.VERSION_CODES.R)
fun AppCompatActivity.setUpSystemWindow(){
    val navBarColor = ContextCompat.getColor(this.applicationContext, R.color.navigation_bar_color)
    val statusBarColor = ContextCompat.getColor(this.applicationContext, R.color.status_bar_color)
    this.window.setDecorFitsSystemWindows(false)
    this.window.navigationBarColor = navBarColor
    this.window.statusBarColor = statusBarColor
}

fun Fragment.showSnackBar(message: String){
  Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
      .setAction("OK"){}
      .show()
}

@RequiresApi(Build.VERSION_CODES.R)
fun Fragment.hideKeyboard(){
    requireActivity().window.insetsController?.hide(WindowInsets.Type.ime())
}

fun FragmentTipCalculatorBinding.btnSelection(){
    val red = ContextCompat.getColor(btPercent15.context, R.color.colorPrimary)
    val brown = ContextCompat.getColor(btPercent15.context, R.color.colorAccent)
    val transparent = ContextCompat.getColor(btPercent15.context, R.color.transparent)
    
    when (true){
        btPercent15.isChecked -> apply {
            listOf(btPercent18, btPercent20, btCustom).forEach { it.apply { setTextColor(brown) } }
            tilCustomTip.visibility = View.GONE
            btPercent15.setBackgroundColor(transparent)
            btPercent15.setTextColor(red)
            btCustom.icon.setTint(brown)
        }
        
        btPercent18.isChecked -> apply {
            listOf(btPercent15, btPercent20, btCustom).forEach { it.apply { setTextColor(brown) } }
            tilCustomTip.visibility = View.GONE
            btPercent18.setBackgroundColor(transparent)
            btPercent18.setTextColor(red)
            btCustom.icon.setTint(brown)
        }
        
        btPercent20.isChecked -> apply {
            listOf(btPercent15, btPercent18, btCustom).forEach { it.apply { setTextColor(brown) } }
            tilCustomTip.visibility = View.GONE
            btPercent20.setBackgroundColor(transparent)
            btPercent20.setTextColor(red)
            btCustom.icon.setTint(brown)
        }
        
        btCustom.isChecked-> apply {
            listOf(btPercent15, btPercent18, btPercent20).forEach { it.apply { setTextColor(brown) } }
            tilCustomTip.visibility = View.VISIBLE
            btCustom.setBackgroundColor(transparent)
            btCustom.icon.setTint(red)
            tilBillTotal.clearFocus()
        }
    }
}