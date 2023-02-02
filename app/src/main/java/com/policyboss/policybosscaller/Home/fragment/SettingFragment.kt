package com.policyboss.policybosscaller.Home.fragment

import android.graphics.Color
import android.os.Bundle
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.CompoundButton.OnCheckedChangeListener
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.jetpackdemo.RetrofitHelper
import com.policyboss.policybosscaller.BaseFragment
import com.policyboss.policybosscaller.Home.HomeViewModel
import com.policyboss.policybosscaller.Home.HomeViewModelFactory
import com.policyboss.policybosscaller.R
import com.policyboss.policybosscaller.data.repository.HomeRepository
import com.policyboss.policybosscaller.databinding.FragmentHomeBinding
import com.policyboss.policybosscaller.databinding.FragmentSettingBinding


class SettingFragment : BaseFragment() {

    private var _binding : FragmentSettingBinding? = null
    lateinit var layout: View
    lateinit var viewModel: HomeViewModel
    private val binding get() = _binding!!

    private var switchListener : CompoundButton.OnCheckedChangeListener? = null

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

    }

    fun init(){

        var repository = HomeRepository(requireActivity(), RetrofitHelper.retrofitCallerApi)
        var viewModelFactory = HomeViewModelFactory(requireActivity(),repository)
        viewModel = ViewModelProvider(requireActivity(),viewModelFactory).get(HomeViewModel::class.java)



    }

    fun setListener(){

        binding.swPopup.setOnCheckedChangeListener (null);
        binding.swPopup.setChecked (true);
        binding.swPopup.setOnCheckedChangeListener (switchListener);
    }

    private fun getSwitchListener(){

        switchListener = object : OnCheckedChangeListener{

        override fun onCheckedChanged(viewButton: CompoundButton?, isChecked: Boolean) {
                if(isChecked){
                   // viewButton!!.setBackgroundResource(R.color.orange_button)
                    //showAlert("checked")
                    viewModel.saveOverlayStatus(true)
                }else{
                   // showAlert("Not checked")
                   // viewButton!!.setBackgroundResource(R.color.red_custom)
                    viewModel.saveOverlayStatus(false)
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