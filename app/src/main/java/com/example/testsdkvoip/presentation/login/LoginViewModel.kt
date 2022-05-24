package com.example.testsdkvoip.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testsdkvoip.common.Resources
import com.example.testsdkvoip.domain.use_case.RegisterUser
import com.example.testsdkvoip.presentation.login.components.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

    val registerUser: RegisterUser

) : ViewModel() {

    private val _logged_in_State = mutableStateOf(LoginState())
    val logged_in_State: State<LoginState> = _logged_in_State
    fun registerUser(
        phoneNumber: String, companyId: String


    ) {
        registerUser.invoke(phoneNumber, companyId).onEach {
            when (it) {
                is Resources.Loading -> _logged_in_State.value = LoginState(isLoading = true)
                is Resources.Success -> _logged_in_State.value = LoginState(registerInfo = it.data)
                is Resources.Error -> _logged_in_State.value = LoginState(error = it.message)


            }

        }.launchIn(viewModelScope)


    }


}