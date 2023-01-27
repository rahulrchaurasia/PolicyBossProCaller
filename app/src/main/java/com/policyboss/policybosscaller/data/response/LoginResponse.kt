package com.policyboss.policybosscaller.data.response

import com.policyboss.policybosscaller.data.ApiResponse
import com.policyboss.policybosscaller.data.model.login.LoginEntity


data class LoginResponse(
    val MasterData: LoginEntity


): ApiResponse()