package com.example.aplicacionfinal

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val user = intent.getStringExtra("USER")
        val pass = intent.getStringExtra("PASS")

        val tvUser = findViewById<TextView>(R.id.tvUser)
        val tvPass = findViewById<TextView>(R.id.tvPass)

        tvUser.text = "Usuario: $user"
        tvPass.text = "Contraseña: $pass"
    }
}