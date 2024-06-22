package com.example.mentalmath

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mentalmath.databinding.ActivityMathTaskBinding
import funTest.Addition

class MathTask : AppCompatActivity() {

    private lateinit var bin : ActivityMathTaskBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        bin = ActivityMathTaskBinding.inflate(layoutInflater)
        setContentView(bin.root)

        fun Random(cost: Int): Any {

            var num1 = 0
            var num2 = 0

            if (cost < 11){
                num1 = (0..10).random()
                num2 = (0..10).random()
                Log.e("if1", "2 : $num1, $num2, $cost")

            } else if (cost < 31) {
                num1 = (8..20).random()
                num2 = (8..20).random()
                Log.e("if2", "2 : $num1, $num2, $cost")
            } else if (cost < 61) {
                num1 = (20..50).random()
                num2 = (20..50).random()
                Log.e("if3", "2 : $num1, $num2, $cost")
            } else if (cost >= 61) {
                num1 = (40..100).random()
                num2 = (40..100).random()
                Log.e("if4", "2 : $num1, $num2, $cost")
            }

            bin.num1.text = num1.toString()
            bin.num2.text = num2.toString()
            return Pair(num1, num2)
        }

        var cost = 7

        val putExc = intent.getStringExtra("name").toString()

        if(putExc == "Сложение") {
            bin.sign.text = "+"
        }

        bin.ok.setOnClickListener {
            Random(cost)
            cost++
        }

    }
}