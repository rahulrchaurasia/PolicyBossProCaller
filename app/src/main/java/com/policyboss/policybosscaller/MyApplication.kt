package com.policyboss.policybosscaller

import android.app.Application
import android.content.Context

class MyApplication : Application() {


    //private var myApp: MyApplication ?= null


    override fun onCreate() {
        super.onCreate()

    }
//    fun getInstance() : MyApplication {
//
//        if(myApp == null){
//            myApp = MyApplication()
//        }else{
//            myApp
//        }
//        return myApp as MyApplication
//    }
    companion object {
        private lateinit var instance: MyApplication

        val getInstance: MyApplication
            get() {
                if (instance == null) {
                    instance = MyApplication()
                }

                return instance
            }
    }

}