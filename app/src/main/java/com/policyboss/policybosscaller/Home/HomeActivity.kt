package com.policyboss.policybosscaller.Home


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.*

import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.jetpackdemo.RetrofitHelper
import com.example.policybosscaller.OverlayDemo.CallType
import com.example.policybosscaller.Prefrence.DataStoreManager
import com.example.policybosscaller.Utility.Constant
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.policyboss.policybosscaller.APIState
import com.policyboss.policybosscaller.BaseActivity

import com.policyboss.policybosscaller.R
import com.policyboss.policybosscaller.data.repository.LoginRepository
import com.policyboss.policybosscaller.databinding.ActivityHomeBinding
import com.policyboss.policybosscaller.login.LoginViewModel
import com.policyboss.policybosscaller.login.LoginViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.nio.channels.spi.AbstractSelectionKey


//http://horizon.policyboss.com:5000/posps/dsas/view/8774
class HomeActivity : BaseActivity() {

    private lateinit var binding : ActivityHomeBinding
    private lateinit var bottomNavigationView: BottomNavigationView

    lateinit var viewModel: HomeViewModel





    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar) // bind Toolbar to Activity
        supportActionBar?.hide()

        init()

        bottomNavigationView  = binding.bottomNavigationView
       val  navController = findNavController(R.id.nav_host_bottom_fragment)

        // region For handling Toolbar  text using navgraph
//        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment,R.id.contactFragment,R.id.settingFragment))
//        setupActionBarWithNavController(navController, appBarConfiguration )
      // endregion

        bottomNavigationView.setupWithNavController(navController)



        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.getFBAID().collect{

                    val fbaid = it
                    Log.d(Constant.TAG ,"" + fbaid)

                }

            }
        }

    }

    private fun init(){


      //  var loiginRepository = LoginRepository(this@HomeActivity, RetrofitHelper.retrofitLoginApi)
        var viewModelFactory = HomeViewModelFactory(this@HomeActivity)
        viewModel = ViewModelProvider(this,viewModelFactory).get(HomeViewModel::class.java)


    }



}