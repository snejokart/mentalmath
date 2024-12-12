package com.example.mentalmath

import AuthManager
import Supabase
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mentalmath.databinding.ActivityAuthBinding
import io.github.jan.supabase.exceptions.RestException
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class Auth : AppCompatActivity() {

    private lateinit var authManager: AuthManager
    private lateinit var binding : ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authManager = AuthManager(this)

        binding.needReg.setOnClickListener {
            val int = Intent(this, Reg::class.java)
            startActivity(int)
            overridePendingTransition(0,0)
        }

        binding.forgotPassword.setOnClickListener {
            val int = Intent(this, ForgotPassword::class.java)
            startActivity(int)
            overridePendingTransition(0,0)
        }

        binding.btnAuth.setOnClickListener {
            val email = binding.emailAuth.text.toString()
            val password = binding.passAuth.text.toString()

            if (email.isEmpty()){
                return@setOnClickListener;
            }

            if (password.isEmpty()){
                return@setOnClickListener
            }
            val int = Intent(this, Main::class.java)

            CoroutineScope(Dispatchers.Main).launch {
                try {
                    Supabase.supabase.auth.signInWith(Email) {
                        this.email = email
                        this.password = password
                    }
                    val user = Supabase.supabase.auth.currentUserOrNull()
                    if (user != null) {
                        authManager.saveToken((Supabase.supabase.auth.currentUserOrNull() ?: ""))
                        startActivity(int)
                        overridePendingTransition(0,0)
                        Toast.makeText(this@Auth, "Вы вошли в аккаунт", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@Auth, "Ошибка авторизации", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: RestException) {
                    Toast.makeText(this@Auth, "Ошибка", Toast.LENGTH_LONG).show()
                } catch (e: Exception) {
                    Toast.makeText(this@Auth, "Ошибка", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}