package com.policyboss.policybosscaller.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.policybosscaller.Utility.Constant
import com.policyboss.policybosscaller.data.model.DashboardData.ConstantEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ConstantDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveConstantData(entitiy : ConstantEntity)

    @Query( "select * from ConstantEntity") // Note : No need to add suspend when we use Flow and Live data otherwise error come
     fun getConstantData(): List<ConstantEntity>
}