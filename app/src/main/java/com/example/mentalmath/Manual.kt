package com.example.mentalmath

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mentalmath.databinding.ActivityManualBinding

class Manual : AppCompatActivity() {

    private lateinit var bin : ActivityManualBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        bin = ActivityManualBinding.inflate(layoutInflater)
        setContentView(bin.root)

        val putExc = intent.getStringExtra("name").toString()

        bin.title.text = putExc

        bin.btnStart.setOnClickListener {
            val int = Intent(this, MathTask::class.java)
            int.putExtra("name", bin.title.text)
            startActivity(int)
            overridePendingTransition(0,0)
        }
    }
}