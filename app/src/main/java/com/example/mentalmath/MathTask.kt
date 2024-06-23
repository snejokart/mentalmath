package com.example.mentalmath

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mentalmath.databinding.ActivityMathTaskBinding

@Suppress("NAME_SHADOWING")
class MathTask : AppCompatActivity() {

    private lateinit var bin : ActivityMathTaskBinding

    private var cost = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bin = ActivityMathTaskBinding.inflate(layoutInflater)
        setContentView(bin.root)

        val putExc = intent.getStringExtra("name").toString()

        if(putExc == "Сложение") { bin.sign.text = "+" }
        else if (putExc == "Вычитание") { bin.sign.text = "-" }
        else if (putExc == "Умножение") { bin.sign.text = "*" }
        else if (putExc == "Деление") { bin.sign.text = "/" }
        else if (putExc == "Всё вместе") { bin.sign.text = "+" }

        //функция для обновления чисел num1 и num2 с помощью рандомайзера
        fun Random(cost: Int): Any {

            var num1 = 0
            var num2 = 0

            if (cost < 15){
                num1 = (2..15).random()
                num2 = (2..15).random()
            } else if (cost < 31) {
                num1 = (3..20).random()
                num2 = (3..20).random()
            } else if (cost < 61) {
                num1 = (20..50).random()
                num2 = (20..50).random()
            } else if (cost >= 61) {
                num1 = (40..100).random()
                num2 = (40..100).random()
            }

            bin.num1.text = num1.toString()
            bin.num2.text = num2.toString()
            return Pair(num1, num2)
//            Log.e("if1", "2 : $num1, $num2, $cost")
        }

        // если выбран режим "деление", то генерируются числа, которые будут делиться друг на друга без остатка
        fun GenerateDivisibleNumbers() {
            Random(cost)
//            Log.e("division", "${bin.num1.text}, ${bin.num2.text}")
            while (bin.num1.text.toString().toDouble() % bin.num2.text.toString().toDouble() != 0.toDouble()) {
                Random(cost)
                Log.e("division", "${bin.num1.text}, ${bin.num2.text}")
            }
        }

        //когда экран только открылся, число сразу генерируется
        Random(cost)
        if (putExc == "Деление") {
            GenerateDivisibleNumbers()
        } else {
            Random(cost)
        }

        bin.enter.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { }
            override fun afterTextChanged(s: Editable?) {

                var resultMath = 0

                if (bin.sign.text == "+"){ resultMath = ((bin.num1.text.toString().toDouble() + bin.num2.text.toString().toDouble()).toInt()) }
                else if (bin.sign.text == "-") { resultMath = ((bin.num1.text.toString().toDouble() - bin.num2.text.toString().toDouble()).toInt()) }
                else if (bin.sign.text == "*") { resultMath = ((bin.num1.text.toString().toDouble() * bin.num2.text.toString().toDouble()).toInt()) }
                else if (bin.sign.text == "/") { resultMath = ((bin.num1.text.toString().toDouble() / bin.num2.text.toString().toDouble()).toInt()) }

                if (resultMath.toString().length == bin.enter.text.toString().length) {
                    if (resultMath == bin.enter.text.toString().toInt()) {
                        Toast.makeText(this@MathTask, "OK, ${bin.num1.text}, ${bin.num2.text}, $resultMath", Toast.LENGTH_SHORT).show()
                        Random(cost)
                        if (putExc == "Деление") {
                            GenerateDivisibleNumbers()
                        } else {
                            Random(cost)
                        }
                        cost++
                        bin.enter.text = ""
                        bin.record.text = cost.toString()
                    } else {
                        Toast.makeText(this@MathTask, "неправильно, ${bin.num1.text}, ${bin.num2.text}, $resultMath", Toast.LENGTH_SHORT).show()
                        Random(cost)
                        if (putExc == "Деление") {
                            GenerateDivisibleNumbers()
                        } else {
                            Random(cost)
                        }
                        cost--
                        bin.enter.text = ""
                        bin.record.text = cost.toString()
                    }
                }


                // Вызывается после изменения текста
                // Здесь вы можете выполнить нужные вам действия при изменении текста в EditText
            }
        })

        fun More(mean: String) {
            var res = bin.enter.text
            if (mean == "1") { res = res.toString() + "1" }
            else if (mean == "2") { res = res.toString() + "2" }
            else if (mean == "3") { res = res.toString() + "3" }
            else if (mean == "4") { res = res.toString() + "4" }
            else if (mean == "5") { res = res.toString() + "5" }
            else if (mean == "6") { res = res.toString() + "6" }
            else if (mean == "7") { res = res.toString() + "7" }
            else if (mean == "8") { res = res.toString() + "8" }
            else if (mean == "9") { res = res.toString() + "9" }
            else if (mean == "0") { res = res.toString() + "0" }
            else if (mean == "min") {
//                if (res[0] !== '-') {
                if (res.toString().isEmpty()) {
                    res = "-"
                }
            }

            bin.enter.text = res
        }

        bin.one.setOnClickListener { More("1") }
        bin.two.setOnClickListener { More("2") }
        bin.three.setOnClickListener { More("3") }
        bin.four.setOnClickListener { More("4") }
        bin.five.setOnClickListener { More("5") }
        bin.six.setOnClickListener { More("6") }
        bin.seven.setOnClickListener { More("7") }
        bin.eight.setOnClickListener { More("8") }
        bin.nine.setOnClickListener { More("9") }
        bin.zero.setOnClickListener {
            //если поле пустое, ввести ноль нельзя. надо либо доработать рандомайзер, либо разрешить вводить ноль, тк иногда num1 и num2 совпадают
//            if (bin.enter.text.isNotEmpty()) {
                More("0")
//            }
        }
        bin.minus.setOnClickListener { More("min") }
        bin.delete.setOnClickListener {
            val existingText = bin.enter.text.toString()
            if (existingText.isNotEmpty()) {
                val newText = existingText.substring(0, existingText.length - 1)
                bin.enter.text = newText
            }
        }
    }
}