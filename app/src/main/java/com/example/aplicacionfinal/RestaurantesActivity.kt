package com.example.aplicacionfinal

import android.os.Bundle
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

        adapter = RestauranteAdapter(listaRestaurantes) { restaurante ->
            eliminarRestaurante(restaurante)
        }
        binding.recyclerView.adapter = adapter
    }

    private fun eliminarRestaurante(restaurante: Restaurante) {
        val position = listaRestaurantes.indexOf(restaurante)
        if (position != -1) {
            listaRestaurantes.removeAt(position)
            adapter.notifyItemRemoved(position)
        }
    }
}