package com.example.aplicacionfinal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacionfinal.adapter.RestauranteAdapter
import com.example.aplicacionfinal.databinding.ActivityRestauranteBinding
import com.example.aplicacionfinal.modelos.Restaurante

class RestaurantesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRestauranteBinding
    private lateinit var adapter: RestauranteAdapter
    private val listaRestaurantes = mutableListOf<Restaurante>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityRestauranteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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

        // Lógica para el botón "Volver"
        binding.btnVolver.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun editarRestaurante(restaurante: Restaurante) {
        val dialogView = layoutInflater.inflate(R.layout.dialog_editar_restaurante, null)
        val etTitulo = dialogView.findViewById<EditText>(R.id.etTitulo)
        val etDescripcion = dialogView.findViewById<EditText>(R.id.etDescripcion)
        val btnGuardar = dialogView.findViewById<Button>(R.id.btnGuardar)

        // Pre-cargar los valores actuales del restaurante en los campos del diálogo
        etTitulo.setText(restaurante.titulo)
        etDescripcion.setText(restaurante.descripcion)

        val dialog = androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Editar Restaurante")
            .setView(dialogView)
            .setCancelable(true)
            .create()

        // Acción de guardar los cambios
        btnGuardar.setOnClickListener {
            val nuevoTitulo = etTitulo.text.toString()
            val nuevaDescripcion = etDescripcion.text.toString()

            if (nuevoTitulo.isNotEmpty() && nuevaDescripcion.isNotEmpty()) {
                // Actualizar el restaurante en la lista
                restaurante.titulo = nuevoTitulo
                restaurante.descripcion = nuevaDescripcion

                // Notificar al adaptador que se ha hecho un cambio
                adapter.notifyItemChanged(listaRestaurantes.indexOf(restaurante))
                dialog.dismiss() // Cerrar el diálogo
            } else {
                Toast.makeText(this, "Todos los campos deben ser llenados", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }


    private fun eliminarRestaurante(restaurante: Restaurante) {
        val position = listaRestaurantes.indexOf(restaurante)
        if (position != -1) {
            listaRestaurantes.removeAt(position)
            adapter.notifyItemRemoved(position)
        }
    }


}
