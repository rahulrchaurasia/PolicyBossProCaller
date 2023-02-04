package com.policyboss.policybosscaller.Home


import android.os.Bundle
import androidx.lifecycle.*

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.policyboss.policybosscaller.BaseActivity

import com.policyboss.policybosscaller.R
import com.policyboss.policybosscaller.databinding.ActivityHomeBinding
import kotlinx.coroutines.launch


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

//                viewModel.getFBAID().collect{
//
//                    val fbaid = it
//                    Log.d(Constant.TAG ,"" + fbaid)
//
//                }

            }
        }

    }

    private fun init(){


      //  var loiginRepository = LoginRepository(this@HomeActivity, RetrofitHelper.retrofitLoginApi)
//        var viewModelFactory = HomeViewModelFactory(this@HomeActivity)
//        viewModel = ViewModelProvider(this,viewModelFactory).get(HomeViewModel::class.java)


    }



}