package com.policyboss.policybosscaller.Home


import android.app.Dialog
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.policybosscaller.Utility.Constant
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.policyboss.policybosscaller.APIState
import com.policyboss.policybosscaller.BaseActivity
import com.policyboss.policybosscaller.BroadCastReceiver.AppInstallReceiver
import com.policyboss.policybosscaller.R
import com.policyboss.policybosscaller.RetrofitHelper
import com.policyboss.policybosscaller.data.db.database.CallerDatabase
import com.policyboss.policybosscaller.data.repository.HomeRepository
import com.policyboss.policybosscaller.databinding.ActivityHomeBinding
import com.policyboss.policybosscaller.databinding.ProgressdialogLoadingBinding
import kotlinx.coroutines.launch


//http://horizon.policyboss.com:5000/posps/dsas/view/8774
class HomeActivity : BaseActivity() {

     lateinit var binding : ActivityHomeBinding
    private lateinit var bottomNavigationView: BottomNavigationView

    lateinit var viewModel: HomeViewModel
    lateinit var showDialog: Dialog

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar) // bind Toolbar to Activity
        supportActionBar?.hide()

        showDialog = Dialog(this@HomeActivity, R.style.Dialog)

        init()

        bottomNavigationView  = binding.bottomNavigationView
       val  navController = findNavController(R.id.nav_host_bottom_fragment)

        // region For handling Toolbar  text using navgraph
//        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment,R.id.contactFragment,R.id.settingFragment))
//        setupActionBarWithNavController(navController, appBarConfiguration )
      // endregion

        bottomNavigationView.setupWithNavController(navController)


//        viewModel.saveUserConstant()
//        getUserConstantData()


    }

    private fun init(){


        var demoDatabase = CallerDatabase.getDatabase(this@HomeActivity.applicationContext)
        var repository = HomeRepository(RetrofitHelper.retrofitCallerApi,demoDatabase)
        var viewModelFactory = HomeViewModelFactory(this@HomeActivity,repository)
        viewModel = ViewModelProvider(this@HomeActivity,viewModelFactory).get(HomeViewModel::class.java)


    }


    //region Not IN USED

    private fun getUserConstantData(){

        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED){

                viewModel.UserConstantStateFlow.collect(){

                    when(it){
                        is APIState.Empty -> {
                            cancelDialog()
                        }
                        is APIState.Failure -> {
                            cancelDialog()

                            showSnackBar(binding.root,it.errorMessage?: Constant.ErrorMessage)
                            Log.d(Constant.TAG_Coroutine, it.errorMessage.toString())
                        }
                        is APIState.Loading -> {
                            showDialog()
                        }
                        is APIState.Success -> {

                            cancelDialog()
                            // Log.d(Constant.TAG,"SUCCESS"+ it.data.toString())

                            it.data?.let {

                               // setData(  it.MasterData)

                            }

                        }
                    }
                }
            }
        }
    }


    private fun showDialog() {
        try {
            if (!this@HomeActivity.isFinishing()) {
                if (!showDialog.isShowing()) {
                    val dialogLoadingBinding: ProgressdialogLoadingBinding =
                        ProgressdialogLoadingBinding.inflate(
                            layoutInflater
                        )
                    showDialog.setContentView(dialogLoadingBinding.getRoot())
                    showDialog.setCancelable(false)
                    showDialog.show()
                }
            }
        } catch (e: Exception) {
            showDialog.dismiss()
        }
    }

    private fun cancelDialog() {
        try {
            if (showDialog != null) {
                showDialog.dismiss()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            showDialog.dismiss()
        }
    }

    //endregion

}