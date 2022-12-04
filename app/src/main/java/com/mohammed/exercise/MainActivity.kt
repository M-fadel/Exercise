package com.mohammed.exercise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import com.mohammed.exercise.databinding.ActivityMainBinding
import kotlin.jvm.internal.Intrinsics.Kotlin
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val calculate = binding.calcualt
        calculate.setOnClickListener {
            if (binding.Price.text.toString()!=  "") {
                val total = binding.total
                val vat = Vat()
                val formattedTotal = NumberFormat.getCurrencyInstance().format(total(vat))
                total.text = getString(R.string.totalAmount, formattedTotal)
            }else{}
        }
    }

    private fun Vat ():Double{
        val vat = when(binding.radio.checkedRadioButtonId){
            R.id.radio1-> .10
            R.id.radio2-> .15
            else-> .20
        }
        return vat;
    }
    private fun total(vat:Double):Double{
        val text =binding.Price.text.toString().toDouble()
        var total = ((text*vat)+text)
        if(binding.Switch.isChecked){
           return kotlin.math.ceil(total)
        }
        return total
    }
}