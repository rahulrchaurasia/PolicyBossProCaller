package com.policyboss.policybosscaller.login

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.policyboss.policybosscaller.RetrofitHelper
import com.example.policybosscaller.Utility.Constant
import com.example.policybosscaller.Utility.NetworkUtils
import com.example.policybosscaller.Utility.Utility
import com.example.policybosscaller.Utility.showSnackbar
import com.google.android.material.snackbar.Snackbar
import com.policyboss.policybosscaller.APIState
import com.policyboss.policybosscaller.BaseActivity
import com.policyboss.policybosscaller.Home.HomeActivity
import com.policyboss.policybosscaller.R
import com.policyboss.policybosscaller.data.repository.LoginRepository
import com.policyboss.policybosscaller.databinding.ActivityLoginBinding
import com.policyboss.policybosscaller.databinding.DialogLoadingBinding
import com.utility.finmartcontact.core.requestentity.LoginRequestEntity
import kotlinx.coroutines.launch

class LoginActivity : BaseActivity() {

    private lateinit var binding : ActivityLoginBinding
    lateinit var viewModel: LoginViewModel
    private lateinit var dialog : Dialog
    lateinit var layout: View


    init {


    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        layout = binding.root
        init()

        lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED){

                viewModel.LoginStateFlow.collect{

                    when(it){

                        is APIState.Loading -> {
                         showDialog("Loading")
                        }

                        is APIState.Success -> {
                           cancelDialog()


                            it.data?.let {

                                // Log.d(Constant.TAG_Coroutine, it.MasterData.EmailID)

                                //  Log.d(Constant.TAG_Coroutine, it.MasterData.toString())


                                layout.showSnackbar("Dear ${it.MasterData.FBAId}, You Login Successfully!!",Snackbar.LENGTH_SHORT)
//                                DataStoreManager(this@LoginActivity).saveLoginData(fbaId = it.MasterData.FBAId.toString(),
//                                    ssId = it.MasterData.ssid.toString(),
//                                    parentId = it.MasterData.parentid.toString())

                                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                                this@LoginActivity.finish()
                                // startActivity(Intent(this@LoginActivity, CommonWebViewActivity::class.java))
                            }




                        }
                        is APIState.Failure -> {

                            //Utility.cancelDialog()

                            showSnackBar(binding.root,it.errorMessage?: Constant.ErrorMessage)
                            Log.d(Constant.TAG_Coroutine, it.errorMessage.toString())
                        }
                        is APIState.Empty -> {
                          //  Utility.cancelDialog()

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

         dialog = Dialog(this@LoginActivity,R.style.Dialog)
        var loiginRepository = LoginRepository(this@LoginActivity, RetrofitHelper.retrofitCallerApi)
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

     fun showDialog(msg: String){

        if(!dialog.isShowing) {
            val dialogLoadingBinding = DialogLoadingBinding.inflate(layoutInflater)
            dialog.setContentView(dialogLoadingBinding.root)

            if(msg.isNotEmpty()){
                dialogLoadingBinding.txtMessage.text = msg

            }
            dialog.setCancelable(false)
            dialog.show()
        }
    }

     fun cancelDialog(){

        if(dialog.isShowing){

            dialog.dismiss()
        }


    }


}