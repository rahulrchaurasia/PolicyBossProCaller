package com.policyboss.policybosscaller.Home.fragment

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.policybosscaller.Prefrence.DataStoreManager
import com.example.policybosscaller.Prefrence.SharePrefernce
import com.example.policybosscaller.Utility.Constant
import com.example.policybosscaller.Utility.NetworkUtils
import com.example.policybosscaller.Utility.Utility
import com.example.policybosscaller.Utility.showSnackbar
import com.google.android.material.snackbar.Snackbar
import com.policyboss.policybosscaller.APIState
import com.policyboss.policybosscaller.BaseFragment
import com.policyboss.policybosscaller.Home.HomeViewModel
import com.policyboss.policybosscaller.Home.HomeViewModelFactory
import com.policyboss.policybosscaller.R
import com.policyboss.policybosscaller.RetrofitHelper
import com.policyboss.policybosscaller.data.db.database.CallerDatabase
import com.policyboss.policybosscaller.data.model.DashboardData.ConstantEntity
import com.policyboss.policybosscaller.data.repository.HomeRepository
import com.policyboss.policybosscaller.databinding.FragmentHomeBinding
import com.policyboss.policybosscaller.login.LoginActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeFragment : BaseFragment() {

    private var _binding : FragmentHomeBinding? = null
    lateinit var layout: View
    lateinit var viewModel: HomeViewModel

    private var constantEntity: ConstantEntity? = null

    private val binding
    get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

       _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layout = binding.root

        init()

        setListener()


        if (!NetworkUtils.isNetworkAvailable(requireContext())){

            Utility.noInternetDialog(requireContext())


        }else{
            viewModel.saveUserConstant()
            getUserConstantData()
        }




    }



    fun init(){

        var demoDatabase = CallerDatabase.getDatabase(requireContext().applicationContext)
        var repository = HomeRepository(RetrofitHelper.retrofitCallerApi,demoDatabase)
        var viewModelFactory = HomeViewModelFactory(requireActivity(),repository)
        viewModel = ViewModelProvider(requireActivity(),viewModelFactory).get(HomeViewModel::class.java)


        SharePrefernce(requireContext()).saveOpenBootTime(0)

    }

    fun setListener(){

        binding.imglogout.setOnClickListener {


            // region Lemda fun directly called
//            Utility.showlogout(context =requireActivity(), msg = getString(R.string.logout )){
//
//                strType, dialog ->
//
//                when(strType){
//
//                    "Y" -> {
//                        dialog.dismiss()
//                        lifecycleScope.launchWhenStarted {
//                            DataStoreManager(requireContext()).clearAll()
//                            withContext(Dispatchers.Main){
//
//                                startActivity(Intent(requireActivity(),LoginActivity::class.java))
//                            }
//                        }
//
//
//
//
//                    }
//                    "N" -> {
//                        dialog.dismiss()
//                    }
//                }
//
//            }

            //endregion

           Utility.showlogout(context =requireActivity(), msg = getString(R.string.logout ), action = ::alertlogotClickAction)
        }



    }

    fun alertlogotClickAction( strType : String, dialog : DialogInterface){

        when(strType){

            "Y" -> {
                dialog.dismiss()
                lifecycleScope.launchWhenStarted {
                    DataStoreManager(requireContext()).clearAll()
                    withContext(Dispatchers.Main){

                        startActivity(Intent(requireActivity(),LoginActivity::class.java))
                    }
                }




            }
            "N" -> {
                dialog.dismiss()

            }
        }
    }



    private fun setData( entity: ConstantEntity?){

        binding.txtName.text = entity?.FullName ?: ""

        binding.txtfbaID.text =  "FBAID-${entity?.FBAId ?:""}"
        binding.txtpospNo.text =  "POSP NO-${entity?.POSPNo ?:""}"


        binding.imgProfile.load(entity!!.loanselfphoto){
            placeholder(R.drawable.circularbg)
            crossfade(true)
            crossfade(400)
            transformations(RoundedCornersTransformation(100f ))
        }
    }


    private fun getUserConstantData(){


        viewLifecycleOwner.lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED){

                viewModel.UserConstantStateFlow.collect(){

                    when(it){
                        is APIState.Empty -> {
                            cancelDialog()
                        }
                        is APIState.Failure -> {
                            cancelDialog()

                            layout.showSnackbar(layout,it.errorMessage?: Constant.ErrorMessage)
                            Log.d(Constant.TAG_Coroutine, it.errorMessage.toString())
                        }
                        is APIState.Loading -> {
                            showDialog()
                        }
                        is APIState.Success -> {

                            cancelDialog()
                            // Log.d(Constant.TAG,"SUCCESS"+ it.data.toString())

                            it.data?.let {

                                setData(  it.MasterData)

                            }

                        }
                    }
                }
            }
        }
    }

    private fun getOverlayStatus(){

        viewLifecycleOwner.lifecycleScope.launch {

            repeatOnLifecycle(Lifecycle.State.STARTED){

//                viewModel.getOverlayStatus().collect(){
//                    if(it){
//                        showAlert("Data is Checked")
//                    }else{
//                        showAlert("Data is Not Checked")
//                    }
//                }


                viewModel.getOverlayStatus().catch { e ->
                    e.printStackTrace()
                }.collect{

                    withContext(Dispatchers.Main){
                        if(it){
                            showAlert("Data is Checked")
                        }else{
                            showAlert("Data is Not Checked")
                        }
                    }
                }

            }
        }
    }

    //endregion


    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}