package com.example.mentalmath

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.Switch
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mentalmath.databinding.ActivityMain2Binding

class Main : AppCompatActivity() {

    private lateinit var bin : ActivityMain2Binding

    private lateinit var sharedPreferences: SharedPreferences

//    private fun soundOn() {
//        val audioManager = getSystemService(AUDIO_SERVICE) as android.media.AudioManager
//        audioManager.setStreamVolume(android.media.AudioManager.STREAM_MUSIC, audioManager.getStreamMaxVolume(android.media.AudioManager.STREAM_MUSIC), 0)
//    }
//
//    private fun soundOff() {
//        val audioManager = getSystemService(AUDIO_SERVICE) as android.media.AudioManager
//        audioManager.setStreamVolume(android.media.AudioManager.STREAM_MUSIC, 0, 0)
//    }

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bin = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(bin.root)

        //        bin.switchSound.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                // Включить звук
//                soundOn()
//                bin.switchSound.text = "Отключить звук "
//            } else {
//                // Отключить звук
//                soundOff()
//                bin.switchSound.text = "Включить звук "
//            }
//        }

        bin.btnProfile.setOnClickListener {
            startActivity(Intent(this, YourAccount::class.java))
            overridePendingTransition(0,0)
        }

        // Инициализация SharedPreferences
        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)

        val lastCost = sharedPreferences.getInt("last_cost", 0)
        bin.lastRecord.text = lastCost.toString()

        val last_category = sharedPreferences.getString("last_category", "")
        bin.lastCategory.text = last_category

        // Создаем карту для хранения названий операций и их значений
        val operations = mapOf(
            "Сложение" to sharedPreferences.getInt("save_cost_add", 0),
            "Вычитание" to sharedPreferences.getInt("save_cost_sub", 0),
            "Умножение" to sharedPreferences.getInt("save_cost_mult", 0),
            "Деление" to sharedPreferences.getInt("save_cost_div", 0),
            "Всё вместе" to sharedPreferences.getInt("save_cost_all_op", 0)
        )

// Находим максимальное значение и соответствующее название операции
        val maxOperation = operations.maxByOrNull { it.value }

// Проверяем, что maxOperation не равно null
        if (maxOperation != null) {
            // Устанавливаем текст для TextView с максимальным значением
            bin.bestRecord.text = maxOperation.value.toString()
            // Устанавливаем текст для TextView с названием операции
            bin.bestCategory.text = maxOperation.key
        } else {
            // Если maxOperation равно null, выводим сообщение
            bin.bestRecord.text = "0"
            bin.bestCategory.text = ""
        }

//// Получение сохраненных значений
//        val addCost = sharedPreferences.getInt("save_cost_add", 0)
//        val subCost = sharedPreferences.getInt("save_cost_sub", 0)
//        val multCost = sharedPreferences.getInt("save_cost_mult", 0)
//        val divCost = sharedPreferences.getInt("save_cost_div", 0)
//        val allOpCost = sharedPreferences.getInt("save_cost_all_op", 0)
//
//// Находим максимальное значение среди всех переменных
//        val maxCost = maxOf(addCost, subCost, multCost, divCost, allOpCost)
//
//        bin.bestRecord.text = maxCost.toString()

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