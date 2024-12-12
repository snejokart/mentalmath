package com.example.mentalmath

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mentalmath.databinding.ActivityForgotPasswordBinding
import io.github.jan.supabase.exceptions.RestException
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class ForgotPassword : AppCompatActivity() {

    private lateinit var binding : ActivityForgotPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.alreadyHaveAcc.setOnClickListener {
            val int = Intent(this, Auth::class.java)
            startActivity(int)
            overridePendingTransition(0,0)
        }

        binding.btnResetCode.setOnClickListener {
            val email = binding.email.text.toString()

            if (email.isEmpty()){
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                return@setOnClickListener
            }

            CoroutineScope(Dispatchers.Main).launch {
                try {
                    Supabase.supabase.auth.resetPasswordForEmail(email = email)
                    val intent = Intent(this@ForgotPassword, ResetPassword::class.java)
                    intent.putExtra("email", email)
                    finish()
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                } catch (e: RestException) {
                    Toast.makeText(this@ForgotPassword,"Ошибка", Toast.LENGTH_LONG).show()
                } catch (e: Exception) {
                    Toast.makeText(this@ForgotPassword,"Ошибка", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}