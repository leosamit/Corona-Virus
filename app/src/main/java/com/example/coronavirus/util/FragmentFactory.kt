package com.example.coronavirus.util

import com.example.coronavirus.ui.CoronaStatsFragment

object FragmentFactory {

    fun getHomeFragment(supportFragmentManager: androidx.fragment.app.FragmentManager): CoronaStatsFragment {
        var fragment =
            supportFragmentManager.findFragmentByTag(CoronaStatsFragment::class.java.name)
        if (fragment == null) {
            fragment = CoronaStatsFragment()
        }
        return fragment as CoronaStatsFragment
    }

}