package com.example.testsdkvoip.presentation.login.components

import com.streamwide.smartms.lib.core.api.account.login.RegisterInfo

data class LoginState(

    val isLoading: Boolean = false,
    val registerInfo: RegisterInfo? = null,
    val error: String? = null


)
