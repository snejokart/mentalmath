
package com.example.mentalmath

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mentalmath.databinding.ActivityNewPasswordBinding
import io.github.jan.supabase.exceptions.RestException
import io.github.jan.supabase.gotrue.OtpType
import io.github.jan.supabase.gotrue.auth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class NewPassword : AppCompatActivity() {

    private lateinit var binding : ActivityNewPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.alreadyHaveAcc.setOnClickListener {
            val int = Intent(this, Auth::class.java)
            startActivity(int)
            overridePendingTransition(0,0)
        }

        binding.confirmNewPassword.setOnClickListener {
            val newPassword = binding.newPassword.text.toString()

            if (newPassword.isEmpty()){
                return@setOnClickListener
            }

            CoroutineScope(Dispatchers.Main).launch {
                try {
                    Supabase.supabase.auth.updateUser {
                        password = newPassword
                    }
                    Supabase.supabase.auth.signOut()
                    Toast.makeText(this@NewPassword,"Пароль успешно сменён", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this@NewPassword, Auth::class.java))
                    finishAffinity()
                } catch (e: RestException) {
                    Toast.makeText(this@NewPassword,"Ошибка", Toast.LENGTH_LONG).show()

                } catch (e: Exception) {
                    Toast.makeText(this@NewPassword,"Ошибка", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}