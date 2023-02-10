package com.policyboss.policybosscaller.popup

import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.view.WindowManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.policybosscaller.Prefrence.SharePrefernce
import com.example.policybosscaller.Utility.Constant
import com.example.policybosscaller.Utility.Utility
import com.policyboss.policybosscaller.Home.HomeViewModel
import com.policyboss.policybosscaller.Home.HomeViewModelFactory
import com.policyboss.policybosscaller.R
import com.policyboss.policybosscaller.RetrofitHelper
import com.policyboss.policybosscaller.Utility.Product
import com.policyboss.policybosscaller.WebView.CommonWebViewActivity
import com.policyboss.policybosscaller.data.db.database.CallerDatabase
import com.policyboss.policybosscaller.data.model.DashboardData.ConstantEntity
import com.policyboss.policybosscaller.data.repository.HomeRepository
import com.policyboss.policybosscaller.databinding.ActivityPopUpAfterCallEndBinding
import kotlinx.coroutines.launch

class PopUpAfterCallEndActivity : AppCompatActivity() , OnClickListener {

    private lateinit var binding : ActivityPopUpAfterCallEndBinding

    private lateinit var sharePreferences: SharePrefernce
    lateinit var viewModel: HomeViewModel
    private var constantEntity: ConstantEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {


        //For Look Screen
       // showWhenLockedAndTurnScreenOn()

        super.onCreate(savedInstanceState)
        binding = ActivityPopUpAfterCallEndBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overridePendingTransition(
            R.anim.slide_up_in, R.anim.slide_up_out)


        sharePreferences = SharePrefernce(this@PopUpAfterCallEndActivity)
        init()
        binding.lyParent.setOnClickListener(this)
        binding.lyPrivateCar.setOnClickListener(this)
        binding.lyTwoWheeler.setOnClickListener(this)
        binding.lyHealth.setOnClickListener(this)

         bindData()

        sharePreferences.clear()

        observe()
    }


    fun getClassName() : String{

        return this@PopUpAfterCallEndActivity.getClassName().toString()
    }

    fun init(){

        var demoDatabase = CallerDatabase.getDatabase(this.applicationContext)
        var repository = HomeRepository(RetrofitHelper.retrofitCallerApi,demoDatabase)
        var viewModelFactory = HomeViewModelFactory(this,repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(HomeViewModel::class.java)





    }



    private fun observe() {

        viewModel.constantData.observe(this@PopUpAfterCallEndActivity) { constantEntity ->

            constantEntity?.let {

                this.constantEntity = it.get(0)
//                Log.d(Constant.TAG, "" + it[0].FBAId + "\n Car " + it[0].FourWheelerUrl
//                            + " \n Health" + it[0].healthurl
//                            + " \n Bike" + it[0].TwoWheelerUrl
//                )

            }


        }



    }

    override fun onClick(view: View?) {

        when(view!!.id){

            binding.lyParent.id -> {


                this@PopUpAfterCallEndActivity.finish()


            }

            binding.lyPrivateCar.id -> {


                OpenWebView(Constant.PrivateCar)
                this@PopUpAfterCallEndActivity.finish()
            }
            binding.lyTwoWheeler.id -> {


                OpenWebView(Constant.TwoWheeler)
                this@PopUpAfterCallEndActivity.finish()
            }
            binding.lyHealth.id -> {


                OpenWebView(Constant.Health)
                this@PopUpAfterCallEndActivity.finish()
            }




        }
    }

    fun bindData(){

        binding.txtVehicleDtls.text = "Maruti, Wegon R, Petrol"
        binding.txtExpiryDate.text = "21-Mar-2023"
        binding.txtInsurer.text = "Bharti Axa"
        var strTemp = StringBuilder()
        strTemp.append(this.resources.getString(R.string.last_call))
        strTemp.append(sharePreferences.getCallType())
        strTemp.append(" " + this.resources.getString(R.string.from_call))
        strTemp.append(" ")
        strTemp.append(sharePreferences.getPhoneNumber().takeLast(10))

       // binding.txtMob.text = "${sharePreferences.getCallType()}: ${ sharePreferences.getPhoneNumber().takeLast(10)}"
        binding.txtMob.text = strTemp.toString()


    }


    private fun showWhenLockedAndTurnScreenOn() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
            setTurnScreenOn(true)
            val keyguardManager = getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
            keyguardManager.requestDismissKeyguard(this, null)
        } else {
            window.addFlags(
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
            )
        }
    }
    fun OpenWebView(type: String){

        var url = ""
        var name = ""
        when(type){

            Constant.PrivateCar ->{


                name = "MOTOR INSURANCE"
                constantEntity?.let {
                    url =  Utility.getURL(url = it.FourWheelerUrl, parent_ssid = "", type = Product.car)

                }

            }
            Constant.TwoWheeler ->{

                //url = Constant.TwoWheelerUrl
                name = "TWO WHEELER INSURANCE"
                constantEntity?.let {
                    url =    Utility.getURL(url = it.TwoWheelerUrl, parent_ssid = "", type = Product.car)

                }
            }
            Constant.Health ->{

                //url = Constant.HealthUrl
                name = "HEALTH INSURANCE"
                constantEntity?.let {
                    url =    Utility.getURL(url = it.healthurl, parent_ssid = "", type = Product.car)

                }
            }
        }

        if(!url.isEmpty()){

            val dialogIntent = Intent(this@PopUpAfterCallEndActivity, CommonWebViewActivity::class.java)
                .putExtra("URL", url)
                .putExtra("NAME", name)
                .putExtra("TITLE", name);
            dialogIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
            dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

            startActivity(dialogIntent)

        }

    }




}