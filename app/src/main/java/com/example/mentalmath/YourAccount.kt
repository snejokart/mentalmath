package com.example.mentalmath

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mentalmath.databinding.ActivityYourAccountBinding
import io.github.jan.supabase.exceptions.RestException
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json.Default.parseToJsonElement
import kotlinx.serialization.json.JsonObject
import java.lang.Exception

class YourAccount : AppCompatActivity() {

    private lateinit var binding: ActivityYourAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYourAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnBack.setOnClickListener {
            startActivity(Intent(this, Main::class.java))
            overridePendingTransition(0,0)
        }

        CoroutineScope(Dispatchers.Main).launch {
            val user = Supabase.supabase.auth.currentUserOrNull()
            if (user?.userMetadata != null){
                binding.name.setText(user.userMetadata!!["fullname"]?.toString()?.replace("\"", "") ?: "")
                binding.surname.setText(user.userMetadata!!["surname"]?.toString()?.replace("\"", "") ?: "")
                binding.patronymic.setText(user.userMetadata!!["patr"]?.toString()?.replace("\"", "") ?: "")
                binding.numClass.setText(user.userMetadata!!["num_class"]?.toString()?.replace("\"", "") ?: "")
                binding.age.setText(user.userMetadata!!["age"]?.toString() ?: "")
            }
        }

        binding.btnSave.setOnClickListener {
            val surname = binding.surname.text.toString()
            val fullname = binding.name.text.toString()
            val patr = binding.patronymic.text.toString()
            val age = binding.age.text.toString()
            val num_class = binding.numClass.text.toString()

            if (fullname.isEmpty() || age.isEmpty() || surname.isEmpty() || patr.isEmpty() || num_class.isEmpty()){
                Toast.makeText(this@YourAccount, "Заполните все поля!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            CoroutineScope(Dispatchers.Main).launch {
                try {
                    val metadata = Supabase.supabase.auth.currentUserOrNull()?.userMetadata!!.toMap().toMutableMap()
                    metadata["fullname"] = parseToJsonElement(fullname)
                    metadata["surname"] = parseToJsonElement(surname)
                    metadata["patr"] = parseToJsonElement(patr)
                    metadata["age"] = parseToJsonElement(age)
                    metadata["num_class"] = parseToJsonElement(num_class)

                    Supabase.supabase.auth.updateUser {
                        this.email = email
                        this.data = JsonObject(metadata)
                    }
                    Toast.makeText(this@YourAccount, "Данные обновлены", Toast.LENGTH_SHORT).show()
                    }
                catch (e: RestException) {
                    Toast.makeText(this@YourAccount, "Ошибка", Toast.LENGTH_LONG).show()
                } catch (e: Exception) {
                    Toast.makeText(this@YourAccount, "Ошибка", Toast.LENGTH_LONG).show()
                }
            }
        }

        binding.changePassword.setOnClickListener {
            startActivity(Intent(this, ForgotPassword::class.java))
            overridePendingTransition(0,0)
        }

        binding.logout.setOnClickListener {
            val int = Intent(this, Auth::class.java)

            CoroutineScope(Dispatchers.Main).launch {
                try {
                    Supabase.supabase.auth.signOut()
                    Toast.makeText(this@YourAccount, "Вы вышли из своего аккаунта", Toast.LENGTH_SHORT).show()
                    startActivity(int)
                    overridePendingTransition(0,0)
                }
                catch (e: RestException) {
                    Toast.makeText(this@YourAccount, "Ошибка", Toast.LENGTH_LONG).show()
                } catch (e: Exception) {
                    Toast.makeText(this@YourAccount, "Ошибка", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}