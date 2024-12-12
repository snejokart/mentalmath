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

    private lateinit var bin : ActivityEndResultBinding

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bin = ActivityEndResultBinding.inflate(layoutInflater)
        setContentView(bin.root)

        // Инициализация SharedPreferences
        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

        val cost = intent.getStringExtra("cost") ?: "0"
        val allansw = intent.getStringExtra("allansw") ?: "0"
        val mistakes = intent.getStringExtra("allmistakes") ?: "0"
        val correct = intent.getStringExtra("correct") ?: "0"
        val alltime = intent.getStringExtra("alltime") ?: "0"
        val putExc = intent.getStringExtra("operation") ?: "0"

        var savedCost = 0

        var lastCost: Int
        var lastCategory = putExc

        when (putExc) {
            "Сложение" -> savedCost = sharedPreferences.getInt("save_cost_add", 0)
            "Вычитание" -> savedCost = sharedPreferences.getInt("save_cost_sub", 0)
            "Умножение" -> savedCost = sharedPreferences.getInt("save_cost_mult", 0)
            "Деление" -> savedCost = sharedPreferences.getInt("save_cost_div", 0)
            "Всё вместе" -> savedCost = sharedPreferences.getInt("save_cost_all_op", 0)
        }

        // Функция для сохранения значения cost
        fun saveCost(save_cost: Int) {
            val editor = sharedPreferences.edit()
            when (putExc) {
                "Сложение" -> editor.putInt("save_cost_add", save_cost)
                "Вычитание" -> editor.putInt("save_cost_sub", save_cost)
                "Умножение" -> editor.putInt("save_cost_mult", save_cost)
                "Деление" -> editor.putInt("save_cost_div", save_cost)
                "Всё вместе" -> editor.putInt("save_cost_all_op", save_cost)
            }
            editor.putString("last_category", save_cost.toString())
            editor.apply() // Применяем изменения
        }

        // Функция для сохранения lastCost
        fun saveLastCost(last_cost: Int) {
            val editor = sharedPreferences.edit()
            editor.putInt("last_cost", last_cost)
            editor.apply() // Применяем изменения
        }

        // Сохранение категории в SharedPreferences
        fun saveCategory(category: String) {
            val editor = sharedPreferences.edit()
            editor.putString("last_category", category)
            editor.apply()
        }

// Сравнение значений cost
        if (cost.toInt() > savedCost) {
            // Если новое значение больше, обновляем сохраненное значение
            saveCost(cost.toInt())
            saveCategory(lastCategory)
            lastCost = cost.toInt() // Обновляем lastCost
            saveLastCost(lastCost) // Сохраняем lastCost в SharedPreferences
            bin.newRecord.text = "НОВЫЙ РЕКОРД!"
        } else {
            saveCategory(lastCategory)

            lastCost = cost.toInt() // Обновляем lastCost
            saveLastCost(lastCost) // Сохраняем lastCost в SharedPreferences
            bin.newRecord.text = "Лучший рекорд: $savedCost"
        }


        fun animateTextView(initialValue: Int, finalValue: Int, textView: TextView) {
            val valueAnimator = ValueAnimator.ofInt(initialValue, finalValue)
            valueAnimator.duration = 5000 // 5 sec
            valueAnimator.addUpdateListener { valueAnimator -> textView.text = valueAnimator.animatedValue.toString() }
            valueAnimator.start()
        }

        animateTextView(0, cost.toInt(), bin.cost)
        animateTextView(0, correct.toInt(), bin.correctansw)
        animateTextView(0, allansw.toInt(), bin.correct)
        animateTextView(0, mistakes.toInt(), bin.mistakes)

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