package com.example.aplicacionfinal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    companion object {
        const val MYUSER = "admin"
        const val MYPASS = "1234"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUser = findViewById<EditText>(R.id.etUser)
        val etPass = findViewById<EditText>(R.id.etPass)
        val btnValidate = findViewById<Button>(R.id.btnValidate)

        btnValidate.setOnClickListener {
            val inputUser = etUser.text.toString()
            val inputPass = etPass.text.toString()

            if (inputUser == MYUSER && inputPass == MYPASS) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("USER", inputUser)
                intent.putExtra("PASS", inputPass)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}