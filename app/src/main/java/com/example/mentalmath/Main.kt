package com.example.mentalmath

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mentalmath.databinding.ActivityMain2Binding

class Main : AppCompatActivity() {

//    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var bin : ActivityMain2Binding

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
//        val cost_save = sharedPreferences.getInt("cost_save", 0)
//        bin.bestRecord.text = cost_save.toString()

//        enableEdgeToEdge()
        bin = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(bin.root)

        bin.btnAddition.setOnClickListener {
            val int = Intent(this@Main, Manual::class.java)
            int.putExtra("name", bin.btnAddition.text)
            startActivity(int)
            overridePendingTransition(0,0)
        }

        bin.btnSubtraction.setOnClickListener {
            val int = Intent(this@Main, Manual::class.java)
            int.putExtra("name", bin.btnSubtraction.text)
            startActivity(int)
            overridePendingTransition(0,0)
        }

        bin.btnMultiplication.setOnClickListener {
            val int = Intent(this@Main, Manual::class.java)
            int.putExtra("name", bin.btnMultiplication.text)
            startActivity(int)
            overridePendingTransition(0,0)
        }
        bin.btnDivision.setOnClickListener {
            val int = Intent(this@Main, Manual::class.java)
            int.putExtra("name", bin.btnDivision.text)
            startActivity(int)
            overridePendingTransition(0,0)
        }

        bin.btnMixed.setOnClickListener {
            val int = Intent(this@Main, Manual::class.java)
            int.putExtra("name", bin.btnMixed.text)
            startActivity(int)
            overridePendingTransition(0,0)
        }
    }
}