package com.example.coronavirus

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import com.example.coronavirus.data.Result
import com.example.coronavirus.di.component.Injectable
import com.example.coronavirus.ui.CoronaStatsViewModel
import timber.log.Timber
import com.example.coronavirus.di.injectViewModel
import com.example.coronavirus.ui.CoronaStatsFragment
import com.example.coronavirus.util.FragmentFactory
import dagger.android.DaggerActivity
import dagger.android.DaggerApplication
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showHomeFragment()


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

