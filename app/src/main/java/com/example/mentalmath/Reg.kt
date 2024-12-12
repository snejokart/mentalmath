package com.example.mentalmath

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mentalmath.databinding.ActivityRegBinding
import io.github.jan.supabase.exceptions.RestException
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import java.lang.Exception

class Reg : AppCompatActivity() {

    private lateinit var binding : ActivityRegBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.alreadyHaveAcc.setOnClickListener {
            val int = Intent(this, Auth::class.java)
            startActivity(int)
            overridePendingTransition(0,0)
        }

        binding.btnReg.setOnClickListener {

            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
//            val age = if (bin.ageEnter.text.toString().isEmpty()) 0 else bin.ageEnter.text.toString().toInt()
            val fullname = binding.fullname.text.toString()

            if (email.isEmpty()){
                return@setOnClickListener;
            }

            if (password.isEmpty()){
                return@setOnClickListener
            }
//
//            if (age <= 0){
//                return@setOnClickListener
//            }
//
            if (fullname.isEmpty()){
                return@setOnClickListener
            }

            CoroutineScope(Dispatchers.Main).launch {
                try {
                    Supabase.supabase.auth.signUpWith(Email) {
                        this.email = email
                        this.password = password
                        data = buildJsonObject {
                            put("fullname", fullname)
//                            put("age", age)
//                            put("number", number)
                        }
                    }
                    Toast.makeText(
//                        this@Reg,Supabase.supabase.auth.currentUserOrNull()?.id ?: "", Toast.LENGTH_LONG).show()
                        this@Reg, "Вы успешно зарегистрировались", Toast.LENGTH_SHORT).show()
//                    Supabase.supabase.auth.signOut()
                } catch (e: RestException) {
                    Toast.makeText(this@Reg,"no", Toast.LENGTH_LONG).show()
                } catch (e: Exception) {
                    Toast.makeText(this@Reg,"no", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}