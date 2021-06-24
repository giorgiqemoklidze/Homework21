package com.example.homework21

import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework21.adapters.DrawerMenuRecyclerAdapter
import com.example.homework21.databinding.ActivityMainBinding
import com.example.homework21.model.MenuItem
import com.example.homework21.viewModels.NewsViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: NewsViewModel by viewModels()
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding : ActivityMainBinding
    private val menuitems  = mutableListOf<MenuItem>()
    private lateinit var adapter : DrawerMenuRecyclerAdapter





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.reCall()

        getMenuItems()
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home), binding.drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }



    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        adapter.checkReturn()
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }



    private fun getMenuItems(){


        val navController = findNavController(R.id.nav_host_fragment)

        menuitems.add(MenuItem(R.drawable.ic_menu_camera,"Camera"))
        menuitems.add(MenuItem(R.drawable.ic_menu_gallery,"Galerry"))
        menuitems.add(MenuItem(R.drawable.ic_menu_slideshow,"SlideShow"))



        adapter = DrawerMenuRecyclerAdapter(menuitems)
        binding.drawerMenuRecycler.layoutManager = LinearLayoutManager(this)
        adapter.selectedItem ={

            binding.drawerLayout.closeDrawer(GravityCompat.START)
            when(it){
                0 -> navController.navigate(R.id.nav_home)

                1 -> navController.navigate(R.id.nav_gallery)

                2 -> navController.navigate(R.id.nav_slideshow)

            }


        }
        binding.drawerMenuRecycler.adapter = adapter



    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.reCall().cancel()
    }
}
