package com.policyboss.policybosscaller.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.jetpackdemo.RetrofitHelper
import com.example.policybosscaller.Utility.Constant
import com.example.policybosscaller.Utility.NetworkUtils
import com.google.android.material.snackbar.Snackbar
import com.policyboss.policybosscaller.APIState
import com.policyboss.policybosscaller.BaseActivity
import com.policyboss.policybosscaller.Home.HomeActivity
import com.policyboss.policybosscaller.data.repository.LoginRepository
import com.policyboss.policybosscaller.databinding.ActivityLoginBinding
import com.utility.finmartcontact.core.requestentity.LoginRequestEntity
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity() {

    private lateinit var binding : ActivityLoginBinding
    lateinit var viewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED){

                viewModel.LoginStateFlow.collect{

                    when(it){

                        is APIState.Loading -> {
                            showDialog()
                        }

                        is APIState.Success -> {
                            cancelDialog()

                            it.data?.let {

                                // Log.d(Constant.TAG_Coroutine, it.MasterData.EmailID)

                                //  Log.d(Constant.TAG_Coroutine, it.MasterData.toString())

                                showSnackBar(binding.root, "Dear ${it.MasterData.FBAId}, You Login Successfully!!")
//                                DataStoreManager(this@LoginActivity).saveLoginData(fbaId = it.MasterData.FBAId.toString(),
//                                    ssId = it.MasterData.ssid.toString(),
//                                    parentId = it.MasterData.parentid.toString())

                                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                                this@LoginActivity.finish()
                                // startActivity(Intent(this@LoginActivity, CommonWebViewActivity::class.java))
                            }




                        }
                        is APIState.Failure -> {

                            cancelDialog()

                            showSnackBar(binding.root,it.errorMessage?: Constant.ErrorMessage)
                            Log.d(Constant.TAG_Coroutine, it.errorMessage.toString())
                        }
                        is APIState.Empty -> {
                            cancelDialog()

                        }

                    }
                }
            }


        }



        binding.includeLogin.btnSignIn.setOnClickListener {




            if (!NetworkUtils.isNetworkAvailable(this@LoginActivity)){

                showSnackBar(binding.root,Constant.NetworkError)
                return@setOnClickListener
            }



            if(validate()){



                var loginRequestEntity = LoginRequestEntity(
                    UserName = binding.includeLogin.etEmail.text.toString(),
                    Password =  binding.includeLogin.etPassword.text.toString(),
                    VersionNo = 10
                )

                /*************************************************************************************************/
                //  Flow Demo  //
                /*************************************************************************************************/

                viewModel.getLoginUsingFlow(loginRequestEntity)




            }





        }

    }

    private fun init(){


        var loiginRepository = LoginRepository(this@LoginActivity,RetrofitHelper.retrofitCallerApi)
        var viewModelFactory = LoginViewModelFactory(loiginRepository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(LoginViewModel::class.java)


    }

    private  fun validate() : Boolean {


        var blnCheck : Boolean = true

        if(binding.includeLogin.etEmail.text.isNullOrBlank()){

            Snackbar.make(binding.root, "Required Email ID!!", Snackbar.LENGTH_SHORT).show()
            blnCheck = false
        }else if(binding.includeLogin.etPassword.text.isNullOrEmpty()){

            Snackbar.make(binding.root, "Required Password!!", Snackbar.LENGTH_SHORT).show()
            blnCheck = false
        }


        return blnCheck
    }

}