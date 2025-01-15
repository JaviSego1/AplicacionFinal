package com.example.aplicacionfinal

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.aplicacionfinal.databinding.ActivityRestauranteBinding

class RestaurantesActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityRestauranteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRestauranteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar la toolbar
        val toolbar = binding.appBarLayoutDrawer.toolbar
        setSupportActionBar(toolbar)

        // Configuración de Navigation
        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.navController

        val navView = binding.myNavView
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.restaurantesFragment, R.id.fragmentConfiguracion, R.id.fragmentFiltro), // Destinos principales
            binding.drawerLayout // DrawerLayout
        )

        // Vincular la toolbar con el navController
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Configurar el NavigationView con el navController
        navView.setupWithNavController(navController)
    }

    // Este método permite que funcione correctamente el botón de navegación hacia arriba (hamburguesa/retroceso)
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar_op, menu)
        return true
    }

    // Navegación desde el menú de opciones
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.restaurantesFragment -> {
                navController.navigate(R.id.restaurantesFragment)
                true
            }
            R.id.fragmentConfiguracion -> {
                navController.navigate(R.id.fragmentConfiguracion)
                true
            }
            R.id.fragmentFiltro -> {
                navController.navigate(R.id.fragmentFiltro)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}

