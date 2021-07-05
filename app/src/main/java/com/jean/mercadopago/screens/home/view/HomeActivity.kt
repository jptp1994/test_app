package com.jean.mercadopago.screens.home.view

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.jean.mercadopago.R
import com.jean.mercadopago.databinding.ActivityHomeBinding
import com.jean.mercadopago.screens.common.BaseActivity
import com.jean.mercadopago.screens.home.viewmodel.HomeViewModel


class HomeActivity: BaseActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewModel=
            ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        setBaseViewModel(viewModel)

        binding.viewModel = viewModel
        setSupportActionBar(
            binding.toolbar
        )

        drawerLayout=  binding.drawerLayout
        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            binding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        configureNavigation()
    }


    private fun configureNavigation(){

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.searchFragment,
                R.id.searchDetailFragment
            ), drawerLayout
        )
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.navigationView.setupWithNavController(navController) //the second most important part
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}