package com.example.aplicacionfinal

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aplicacionfinal.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        const val MYUSER = "admin"
        const val MYPASS = "1234"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnValidate.setOnClickListener {
            val inputUser = binding.etUser.text.toString()
            val inputPass = binding.etPass.text.toString()

            if (inputUser == MYUSER && inputPass == MYPASS) {
                val intent = Intent(this, RestaurantesActivity::class.java)
                intent.putExtra("USER", inputUser)
                intent.putExtra("PASS", inputPass)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
