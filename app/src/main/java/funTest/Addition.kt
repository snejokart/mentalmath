@file:Suppress("DEPRECATED_IDENTITY_EQUALS")

package funTest

import android.util.Log
import com.example.mentalmath.databinding.ActivityMathTaskBinding

private lateinit var bin : ActivityMathTaskBinding


fun Addition(cost: Int): Any {

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