package com.example.coronavirus

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.coronavirus.ui.country.CoronaStatsFragment
import com.example.coronavirus.util.FragmentFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : DaggerAppCompatActivity(), NavController.OnDestinationChangedListener {

    private lateinit var navHostFragment: NavHostFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // showHomeFragment()
        initNavigation()

    }

    private fun initNavigation() {
        navHostFragment = f_nav_host_main as NavHostFragment
        navHostFragment.navController.addOnDestinationChangedListener(this)
        //setupActionBarWithNavController(navHostFragment.navController)
        bn_main.setupWithNavController(navHostFragment.navController)
        bn_main.setOnNavigationItemReselectedListener { }
    }

    override fun onDestinationChanged(
        controller: NavController,
        destination: NavDestination,
        arguments: Bundle?
    ) {
    }

    fun initToolbar() {
        setSupportActionBar(tb_main)
        supportActionBar?.apply {
            //title = ""
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
            setHomeButtonEnabled(true)
        }
    }

    private fun showHomeFragment() {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
            .replace(
                R.id.container,
                FragmentFactory.getHomeFragment(supportFragmentManager),
                CoronaStatsFragment::class.java.name
            )
        fragmentTransaction.addToBackStack(CoronaStatsFragment::class.java.name)
        fragmentTransaction.commit()
    }
}

