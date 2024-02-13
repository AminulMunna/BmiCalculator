package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible

class MainActivity : AppCompatActivity() {
    private lateinit var weightTxt:EditText
    private lateinit var heightTxt:EditText
    private lateinit var calBtn:Button
    private lateinit var countTxt:TextView
    private lateinit var resultTxt:TextView
    private lateinit var rangeTxt:TextView
    private lateinit var cardView:CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        weightTxt = findViewById(R.id.weightEt)
        heightTxt = findViewById(R.id.heightEt)
        calBtn = findViewById(R.id.calBtn)
        countTxt = findViewById(R.id.countTxt)
        resultTxt = findViewById(R.id.resultTxt)
        rangeTxt = findViewById(R.id.rangeTxt)
        cardView = findViewById(R.id.cardView3)

        calBtn.setOnClickListener {
            val weight = weightTxt.text.toString()
            val height = heightTxt.text.toString()
            cardView.isVisible = true

            if(weight.isEmpty() && height.isEmpty()){
                Toast.makeText(this@MainActivity, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }else{
                val bmi = weight.toDouble()/((height.toDouble()/100)*(height.toDouble()/100))
                val bmiDigit  = String.format("%.2f", bmi).toDouble()

                display(bmiDigit)
            }


        }


    }

    private fun display(bmiDigit: Double) {
        countTxt.text = bmiDigit.toString()
        resultTxt.text = "You are healthy"
        rangeTxt.text = "(Normal range is 18.5-24.5)"

        var result = ""
        var color = 0
        var range = ""

        when{
            bmiDigit<18.50 ->{
                result = "Underweight"
                color = R.color.normal
                range = "(Underweight range is less than 18.50)"
            }
            bmiDigit in 18.50..24.99 ->{
                result = "Healthy"
                color = R.color.normal
                range = "(Healthy range is 18.50 to 24.99)"
            }
            bmiDigit in 25.00..29.99 ->{
                result = "Overweight"
                color = R.color.overweight
                range = "(Overweight range is 25.50 to 29.99)"
            }
            bmiDigit > 29.99 ->{
                result = "Obese"
                color = R.color.obese
                range = "(Obese range is greater than 29.99)"
            }
        }

        resultTxt.setTextColor(ContextCompat.getColor(this, color))
        resultTxt.text = result
        rangeTxt.setTextColor(ContextCompat.getColor(this, color))
        rangeTxt.text = range

    }

}