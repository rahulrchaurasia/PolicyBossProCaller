package com.policyboss.policybosscaller.data.response

import com.policyboss.policybosscaller.data.model.DashboardData.ConstantEntity


data class ConstantDataResponse(
    val MasterData: ConstantEntity,
    val Message: String,
    val Status: String,
    val StatusNo: Int
)