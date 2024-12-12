package com.example.mentalmath

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mentalmath.databinding.ActivityResetPasswordBinding
import io.github.jan.supabase.exceptions.RestException
import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class ResetPassword : AppCompatActivity() {

    private lateinit var binding: ActivityResetPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringExtra("email") ?: ""

        val timer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = (millisUntilFinished / 1000).toInt()
                val minutes = seconds / 60
                val secondsRemaining = seconds % 60
                binding.resetAgain.text = "Отправить код повторно можно через ${String.format("%d:%02d", minutes, secondsRemaining)}"
            }

            override fun onFinish() {
                binding.resetAgain.text = "Отправить код повторно можно"

                binding.resetAgain.setOnClickListener {

                    CoroutineScope(Dispatchers.Main).launch {
                        try {
                            Supabase.supabase.auth.resetPasswordForEmail(email = email)
                            val intent = Intent(this@ResetPassword, ResetPassword::class.java)
                            intent.putExtra("email", email)
                        } catch (e: RestException) {
                            Toast.makeText(this@ResetPassword,"Ошибка", Toast.LENGTH_LONG).show()
                        } catch (e: Exception) {
                            Toast.makeText(this@ResetPassword,"Ошибка", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }.start()

        binding.btnCreateNewPass.setOnClickListener {
            val code = binding.code.text.toString()

            if (code.isEmpty()) {
                return@setOnClickListener
            }

            if (code.length != 6) {
                return@setOnClickListener
            }

            CoroutineScope(Dispatchers.Main).launch {
                try {
                    Supabase.supabase.auth.verifyEmailOtp(
                        type = OtpType.Email.EMAIL,
                        email = email,
                        token = code
                    )
                    startActivity(Intent(this@ResetPassword, NewPassword::class.java))
                } catch (e: RestException) {
                    Toast.makeText(this@ResetPassword, "Ошибка", Toast.LENGTH_LONG).show()

                } catch (e: Exception) {
                    Toast.makeText(this@ResetPassword, "Ошибка", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}