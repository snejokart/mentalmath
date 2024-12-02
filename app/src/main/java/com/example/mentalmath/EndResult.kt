package com.example.mentalmath

import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mentalmath.databinding.ActivityEndResultBinding
import com.example.mentalmath.databinding.ActivityMain2Binding
import kotlin.math.cos

class EndResult : AppCompatActivity() {

//    private lateinit var sharedPreferences: SharedPreferences
//    private lateinit var editor: SharedPreferences.Editor
//    private var cost_save: Int = 0 // Ваше значение cost

    private lateinit var bin : ActivityEndResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bin = ActivityEndResultBinding.inflate(layoutInflater)
        setContentView(bin.root)


        val cost = intent.getStringExtra("cost") ?: "0"
        val allansw = intent.getStringExtra("allansw") ?: "0"
        val mistakes = intent.getStringExtra("allmistakes") ?: "0"
        val correct = intent.getStringExtra("correct") ?: "0"
        val alltime = intent.getStringExtra("alltime") ?: "0"

        fun animateTextView(initialValue: Int, finalValue: Int, textView: TextView) {
            val valueAnimator = ValueAnimator.ofInt(initialValue, finalValue)
            valueAnimator.duration = 5000 // 5 sec
            valueAnimator.addUpdateListener { valueAnimator -> textView.text = valueAnimator.animatedValue.toString() }
            valueAnimator.start()
        }

//        fun saveCost() {
//            editor.putInt("cost", cost_save) // Сохранение переменной cost
//            editor.apply() // Применение изменений
//        }

        animateTextView(0, cost.toInt(), bin.cost)
        animateTextView(0, correct.toInt(), bin.correctansw)
        animateTextView(0, allansw.toInt(), bin.correct)
        animateTextView(0, mistakes.toInt(), bin.mistakes)

//        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
//        editor = sharedPreferences.edit()
//        this.cost_save = sharedPreferences.getInt("cost_save", 0) // 0 - значение по умолчанию, если нет сохраненного значения
//        saveCost()

        bin.time.text = alltime

        bin.restart.setOnClickListener {
            finish()
        }

        bin.goBackMain.setOnClickListener {
            val int = Intent(this, Main::class.java)
            startActivity(int)
            overridePendingTransition(0,0)
        }
    }
}