package com.policyboss.policybosscaller.Home


import android.os.Bundle

import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.policybosscaller.OverlayDemo.CallType
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.policyboss.policybosscaller.BaseActivity

import com.policyboss.policybosscaller.R
import com.policyboss.policybosscaller.databinding.ActivityHomeBinding


//http://horizon.policyboss.com:5000/posps/dsas/view/8774
class HomeActivity : BaseActivity() {

    private lateinit var binding : ActivityHomeBinding
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar) // bind Toolbar to Activity

        bottomNavigationView  = binding.bottomNavigationView
       val  navController = findNavController(R.id.nav_host_bottom_fragment)

        // region For Toolbar using navgraph
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment,R.id.contactFragment,R.id.settingFragment))
        setupActionBarWithNavController(navController, appBarConfiguration )
      // endregion

        bottomNavigationView.setupWithNavController(navController)

    }
}