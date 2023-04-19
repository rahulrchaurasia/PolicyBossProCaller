package com.policyboss.policybosscaller.data.db.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.policyboss.policybosscaller.data.db.convertors.Convertors


import com.policyboss.policybosscaller.data.db.dao.ConstantDao
import com.policyboss.policybosscaller.data.model.DashboardData.ConstantEntity


/***************** Defination****************************
//volatile : --> property as volatile, meaning that writes to this field are immediately made visible to other threads.


 we have used Singleton pattern to Create RoomDatabase INSTANCE
 **********************************************/


@Database(entities = [ ConstantEntity::class], version = 1, exportSchema = false)
@TypeConverters(Convertors::class)
abstract class CallerDatabase : RoomDatabase() {



    abstract fun  constantDao() : ConstantDao

    companion object{

        @Volatile
        private var INSTANCE: CallerDatabase? = null       // Volatile : here any changes in "Instance" are immediately made visible to all threads

        fun  getDatabase(context : Context) : CallerDatabase {

            if(INSTANCE == null){

                synchronized(this){                                         // Note synchronized : due to multithread it may
                                                                                    // chance  create multiple instance of Room Database.
                    INSTANCE = Room.databaseBuilder(context.applicationContext,    // Synchronize lock the particular instance.
                        CallerDatabase::class.java, "PolicyBossCallerDB")
                       // .allowMainThreadQueries() //  Dont Use it    Android Room Database Query without using Flow or LiveData (no observing)
                       .fallbackToDestructiveMigration()
                        .build()
                }

            }

            return INSTANCE!!

        }
    }


}