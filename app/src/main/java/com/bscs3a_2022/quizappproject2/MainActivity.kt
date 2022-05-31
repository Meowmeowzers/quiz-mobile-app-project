package com.bscs3a_2022.quizappproject2

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.bscs3a_2022.quizappproject2.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView

//import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    //private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.activityAppBar.toolbar)

        val drawerLayout = binding.layoutDrawer
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment
        val navController = navHostFragment.navController

        //findViewById<NavigationView>(R.id.nav_View).setupWithNavController(navController)

        //appBarConfiguration = AppBarConfiguration(navController.graph)
        appBarConfiguration = AppBarConfiguration(
            setOf(
            R.id.firstFragment,R.id.secondFragment,R.id.quizFragment,R.id.loginFragment
            ), drawerLayout
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        setupWithNavController(binding.navView, navController)
        //NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        //setNavigationItems(drawerLayout, navController)

        binding.fab.setOnClickListener { view: View ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> {
                val nav = findNavController(R.id.nav_host_fragment_content_main)
                nav.navigate(R.id.quizFragment)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    /*private fun setNavigationItems(drawer: DrawerLayout, nav: NavController) {
        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_1 -> {
                    Toast.makeText(this, "Hey man whats up 1", Toast.LENGTH_SHORT).show()
                    if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START)
                    }
                    true
                }
                R.id.nav_2 -> {
                    Toast.makeText(this, "Hey man whats up 2", Toast.LENGTH_SHORT).show()
                    nav.navigate(R.id.action_global_secondFragment)
                    if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START)
                    }
                    true
                }
                R.id.nav_3 -> {
                    Toast.makeText(this, "Hey man whats up 3", Toast.LENGTH_SHORT).show()
                    if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START)
                    }
                    true
                }
                R.id.nav_4 -> {
                    Toast.makeText(this, "Hey man whats up 4", Toast.LENGTH_SHORT).show()
                    if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START)
                    }
                    true
                }
                R.id.nav_5 -> {
                    Toast.makeText(this, "Hey man whats up 5", Toast.LENGTH_SHORT).show()
                    if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START)
                    }
                    true
                }
                R.id.nav_6 -> {
                    Toast.makeText(this, "Hey man whats up 6", Toast.LENGTH_SHORT).show()
                    if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START)
                    }
                    true
                }
                R.id.nav_7 -> {
                    Toast.makeText(this, "Hey man whats up 7", Toast.LENGTH_SHORT).show()
                    if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START)
                    }
                    true
                }

                else -> {
                    if (drawer.isDrawerOpen(GravityCompat.START)) {
                        drawer.closeDrawer(GravityCompat.START)
                    }
                    false
                }
            }
        }
    }*/
}

