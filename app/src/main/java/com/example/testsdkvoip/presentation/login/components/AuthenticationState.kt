package com.example.testsdkvoip.presentation.login.components

import com.streamwide.smartms.lib.core.api.account.login.RegisterInfo

data class AuthenticationState(

    val isLoading: Boolean = false,
    val registerInfo: RegisterInfo? = null,
    val error: String? = null,
    val success: Boolean = false


)
