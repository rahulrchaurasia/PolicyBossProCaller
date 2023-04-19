package com.policyboss.policybosscaller.Home.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.CompoundButton.OnCheckedChangeListener
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope

import com.example.policybosscaller.Prefrence.SharePrefernce
import com.example.policybosscaller.Utility.Constant
import com.policyboss.policybosscaller.BaseFragment

import com.policyboss.policybosscaller.Home.HomeViewModel
import com.policyboss.policybosscaller.Home.HomeViewModelFactory
import com.policyboss.policybosscaller.RetrofitHelper
import com.policyboss.policybosscaller.data.db.database.CallerDatabase
import com.policyboss.policybosscaller.data.model.DashboardData.ConstantEntity
import com.policyboss.policybosscaller.data.repository.HomeRepository
import com.policyboss.policybosscaller.databinding.FragmentSettingBinding



class SettingFragment : BaseFragment() {

    private var _binding : FragmentSettingBinding? = null
    lateinit var layout: View
    lateinit var viewModel: HomeViewModel
    private val binding get() = _binding!!
    private lateinit var  prefernce : SharePrefernce
    private var constantEntity: ConstantEntity? = null

    // saveSmSActivation
    private var switchListener : CompoundButton.OnCheckedChangeListener? = null
    private var switchSMSListener : CompoundButton.OnCheckedChangeListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSettingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layout = binding.root

        init()

        getSwitchListener()
        setListener()
      //  observe()
       // viewLifecycleOwner.lifecycleScope.launch {

//            viewModel.getUserConstant()
//            Log.d(Constant.TAG,"User Constatnt Data"+ viewModel.constantList.toString())

       // }

    }

    fun init(){


        var demoDatabase = CallerDatabase.getDatabase(requireContext().applicationContext)
        var repository = HomeRepository( RetrofitHelper.retrofitCallerApi,demoDatabase)
        var viewModelFactory = HomeViewModelFactory(requireActivity(),repository)
        viewModel = ViewModelProvider(requireActivity(),viewModelFactory).get(HomeViewModel::class.java)

       prefernce = SharePrefernce(requireContext())

        // region Cpmmt For Demo
//        val calculatedtTime = Calendar.getInstance()
//        calculatedtTime.add(Calendar.MINUTE, + 1)
//
//        SharePrefernce(requireContext()).saveOpenBootTime(calculatedtTime.timeInMillis)
//
//        Log.d(Constant.TAG,"Calaculated Time  ${Utility.getDate(calculatedtTime.time) }")
//        Log.d(Constant.TAG,"current Time  ${Utility.getCurrentTime()}")

        //endregion
    }


    fun setListener(){

        binding.swPopup.setOnCheckedChangeListener (null)
        binding.swPopup.setChecked (prefernce.isOverlayPopup())
        binding.swPopup.setOnCheckedChangeListener (switchListener)

        binding.swSms.setOnCheckedChangeListener (null)
        binding.swSms.setChecked (prefernce.isSmsACTIVE())
        binding.swSms.setOnCheckedChangeListener (switchListener)
    }

    private fun getSwitchListener(){

        switchListener = object : OnCheckedChangeListener{

        override fun onCheckedChanged(viewButton: CompoundButton?, isChecked: Boolean) {

                if(isChecked){

                   // viewModel.saveOverlayStatus(true)
                    prefernce.saveOverlayDialogStatus(true)


                }else{

                   // viewButton!!.setBackgroundResource(R.color.red_custom)
                   // viewModel.saveOverlayStatus(false)
                    prefernce.saveOverlayDialogStatus(false)
                }

            }

        }


        switchSMSListener = object  : OnCheckedChangeListener{
            override fun onCheckedChanged(p0: CompoundButton?, isChecked: Boolean) {
                if(isChecked){

                    prefernce.saveSmSActivation(true)

                }else{
                    prefernce.saveSmSActivation(false)
                }
            }


        }

    }



    companion object {

        @JvmStatic
        fun newInstance() =
            SettingFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}