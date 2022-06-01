package com.call.testsdkvoip.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.call.testsdkvoip.domain.use_case.call.CallUseCases
import com.streamwide.smartms.lib.core.network.voip.STWVCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CallStateViewModel @Inject constructor(
    private val callUseCases: CallUseCases


) : ViewModel() {
    private val _callState = mutableStateOf<CallState>(CallState.Idle)
    val callState: State<CallState> = _callState

    init {
        callUseCases.listenForCallsState().onEach {

            _callState.value = it.data!!

        }.launchIn(viewModelScope)


    }

    fun callUserNumber(phoneNumber: String) =
        callUseCases.callUser(phoneNumber).launchIn(viewModelScope)

    fun hangCall(call: STWVCall) =
        callUseCases.hangCall(call).launchIn(viewModelScope)

}


