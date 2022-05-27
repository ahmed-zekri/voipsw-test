package com.call.testsdkvoip.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.call.testsdkvoip.common.Resources
import com.call.testsdkvoip.domain.use_case.account.AuthenticationUseCases
import com.call.testsdkvoip.presentation.login.components.AuthenticationState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(

    private val authenticationUseCases: AuthenticationUseCases

) : ViewModel() {

    private val _loggedInState = mutableStateOf(AuthenticationState())
    val loggedInState: State<AuthenticationState> = _loggedInState
    fun registerUser(
        phoneNumber: String, companyId: String


    ) {
        authenticationUseCases.registerUser.invoke(phoneNumber, companyId).onEach {
            when (it) {
                is Resources.Loading -> _loggedInState.value = AuthenticationState(isLoading = true)
                is Resources.Success -> _loggedInState.value =
                    AuthenticationState(registerInfo = it.data)

                is Resources.Error -> _loggedInState.value = AuthenticationState(error = it.message)


            }

        }.launchIn(viewModelScope)


    }


    fun loginUser(
        activationCode: String


    ) {
        authenticationUseCases.loginUser.invoke(activationCode).onEach {
            when (it) {
                is Resources.Loading -> _loggedInState.value = AuthenticationState(isLoading = true)
                is Resources.Success -> _loggedInState.value =
                    AuthenticationState(success = true)

                is Resources.Error -> _loggedInState.value = AuthenticationState(error = it.message)


            }

        }.launchIn(viewModelScope)


    }


}