package com.policyboss.policybosscaller.Home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.policyboss.policybosscaller.data.repository.LoginRepository

class HomeViewModelFactory(private val context: Context) :ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(HomeViewModel::class.java)){

            return HomeViewModel(context) as T
        }

        throw IllegalArgumentException("ViewModel class Not Found")

    }
}