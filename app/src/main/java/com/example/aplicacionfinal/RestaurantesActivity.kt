package com.example.aplicacionfinal

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacionfinal.adapter.RestauranteAdapter
import com.example.aplicacionfinal.databinding.ActivityRestauranteBinding
import com.example.aplicacionfinal.modelos.Restaurante
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RestaurantesActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityRestauranteBinding
    private lateinit var adapter: RestauranteAdapter
    private val listaRestaurantes = mutableListOf<Restaurante>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser
        if (currentUser == null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        binding = ActivityRestauranteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        sharedPreferences = getSharedPreferences("PreferenciasApp", Context.MODE_PRIVATE)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()

        listaRestaurantes.add(Restaurante(
            "Disfrutar",
            "Desde Barcelona, galardonado con estrellas Michelin, ofrece una experiencia gastronómica " +
                    "de vanguardia con platos creativos.",
            "disfrutar")
        )
        listaRestaurantes.add(Restaurante(
            "Etxebarri",
            "Desde Vizcaya, es conocido por su cocina de autor basada en la parrilla.",
            "vizcaya")
        )
        listaRestaurantes.add(Restaurante(
            "Table by Bruno Verjus",
            "Desde París, restaurante que fusiona ingredientes locales con técnicas modernas, ofreciendo menús degustación innovadores",
            "brunoverjus")
        )
        listaRestaurantes.add(Restaurante(
            "DiverXO",
            "Desde Madrid, el chef David Muñoz crea platos únicos que combinan la cocina asiática y mediterránea. ",
            "driverxo")
        )
        listaRestaurantes.add(Restaurante(
            "Maido",
            "Desde Lima, Perú, restaurante que fusiona lo mejor de Japón y Perú, dirigido por el chef Mitsuharu Tsumura.",
            "maido")
        )
        listaRestaurantes.add(Restaurante(
            "Atomix",
            "Desde Nueva York, ofrece una experiencia gastronómica coreana moderna, con menús degustación innovadores.",
            "atomix")
        )
        listaRestaurantes.add(Restaurante(
            "Quintonil",
            "Desde Ciudad de México, dirigido por el chef Jorge Vallejo, destaca por su reinterpretación de la cocina mexicana.",
            "quintonil")
        )
        listaRestaurantes.add(Restaurante(
            "Alchemist",
            "Desde Copenhague, combina arte, ciencia y gastronomía en una experiencia multisensorial única.",
            "alchemist")
        )

        adapter = RestauranteAdapter(
            listaRestaurantes,
            onEdit = { restaurante -> editarRestaurante(restaurante) },
            onEliminarClick = { restaurante -> eliminarRestaurante(restaurante) }
        )
        binding.recyclerView.adapter = adapter

        binding.btnVolver.setOnClickListener {
            cerrarSesion()
        }

        binding.btnAgregar.setOnClickListener {
            agregarRestaurante()
        }
    }

    private fun agregarRestaurante() {
        val dialogFragment = AgregarRestauranteDialogFragment()
        dialogFragment.setOnGuardarClickListener { titulo, descripcion ->
            val nuevoRestaurante = Restaurante(titulo, descripcion, "nuevo_restaurante")
            listaRestaurantes.add(nuevoRestaurante)
            adapter.notifyItemInserted(listaRestaurantes.size - 1)
        }
        dialogFragment.show(supportFragmentManager, "AgregarRestauranteDialog")
    }

    private fun editarRestaurante(restaurante: Restaurante) {
        val dialogFragment = EditarRestauranteDialogFragment(restaurante)
        dialogFragment.setOnGuardarClickListener { nuevoTitulo, nuevaDescripcion ->
            restaurante.titulo = nuevoTitulo
            restaurante.descripcion = nuevaDescripcion
            adapter.notifyItemChanged(listaRestaurantes.indexOf(restaurante))
        }
        dialogFragment.show(supportFragmentManager, "EditarRestauranteDialog")
    }

    private fun eliminarRestaurante(restaurante: Restaurante) {
        val position = listaRestaurantes.indexOf(restaurante)
        if (position != -1) {
            listaRestaurantes.removeAt(position)
            adapter.notifyItemRemoved(position)
        }
    }

    private fun cerrarSesion() {
        auth.signOut()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
