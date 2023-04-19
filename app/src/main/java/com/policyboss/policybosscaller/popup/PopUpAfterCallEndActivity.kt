package com.policyboss.policybosscaller.popup

import android.app.KeyguardManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.View
import android.view.View.OnClickListener
import android.view.WindowManager
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import coil.transform.CircleCropTransformation
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
         setListener()
         bindData()

       // sharePreferences.clear()
        sharePreferences.clearLastState()    // Cleae the last State of the Call receiver...

        observe()

       if(sharePreferences.isSmsACTIVE()) {
           Utility.smsHandling()
       }


    }

    fun setListener(){
        binding.lyParent.setOnClickListener(this)
        binding.lyPrivateCar.setOnClickListener(this)
        binding.lyTwoWheeler.setOnClickListener(this)
        binding.lyHealth.setOnClickListener(this)
        binding.btnLink.setOnClickListener(this)
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

            Toast.makeText(this,"wdwdwwwd",Toast.LENGTH_LONG).show()
            if(constantEntity != null){

                constantEntity?.let {
                    this.constantEntity = it

                }

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

            binding.btnLink.id -> {

                val url = "https://origin-cdnh.policyboss.com/fmweb/motor/life_sanchay/POS_Product.html?ss_id=11436&hdfc_quote=00952048&fba_id=58403&v=20200717&sub_fba_id=0&ip_address=&mac_address=&app_version=policyboss-0.1.2&device_id=e73cb6c8dd4be83c&product_id=3&login_ssid="
               Utility.loadWebViewUrlInBrowser(context = this@PopUpAfterCallEndActivity,url = url)
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

        binding.imglogo.load("https://origin-cdnh.policyboss.com/fmweb/policyboss-pro/DynamicMenuImages/hdfc_life.png") {
            crossfade(true)
            placeholder(R.drawable.round_rect_shape)
            transformations(CircleCropTransformation())
        }

        binding.webview.webViewClient = WebViewClient()

        // this will load the url of the website
        //"https://www.policyboss.com"
        binding.webview.loadUrl("https://www.youtube.com/watch?v=_LqI-x9oD-g")

        // this will enable the javascript settings, it can also allow xss vulnerabilities
        binding.webview.settings.javaScriptEnabled = true

        // if you want to enable zoom feature
        binding.webview.settings.setSupportZoom(true)



        val mSpannableString = SpannableString("LINK")

        // Setting underline style from
        // position 0 till length of
        // the spannable string
        mSpannableString.setSpan(UnderlineSpan(), 0, mSpannableString.length, 0)

        // Displaying this spannable
        // string in TextView
        binding.btnLink.text = mSpannableString


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

        if(constantEntity != null) {

            var url = ""
            var name = ""
            when (type) {

                Constant.PrivateCar -> {


                    name = "MOTOR INSURANCE"
                    constantEntity?.let {
                        url = Utility.getURL(
                            url = it.FourWheelerUrl,
                            parent_ssid = "",
                            type = Product.car
                        )

                    }

                }
                Constant.TwoWheeler -> {

                    //url = Constant.TwoWheelerUrl
                    name = "TWO WHEELER INSURANCE"
                    constantEntity?.let {
                        url = Utility.getURL(
                            url = it.TwoWheelerUrl,
                            parent_ssid = "",
                            type = Product.car
                        )

                    }
                }
                Constant.Health -> {

                    //url = Constant.HealthUrl
                    name = "HEALTH INSURANCE"
                    constantEntity?.let {
                        url =
                            Utility.getURL(url = it.healthurl, parent_ssid = "", type = Product.car)

                    }
                }
            }

            if (!url.isEmpty()) {

                val dialogIntent =
                    Intent(this@PopUpAfterCallEndActivity, CommonWebViewActivity::class.java)
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




}