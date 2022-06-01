package com.call.testsdkvoip.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.call.testsdkvoip.common.Resources
import com.call.testsdkvoip.domain.use_case.call.ListenForCallsState
import com.streamwide.smartms.lib.core.network.voip.STWVCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CallStateListenerViewModel @Inject constructor(
    callsState: ListenForCallsState


) : ViewModel() {
    private val _callState = mutableStateOf<CallState>(CallState.Idle())
    val callState: State<CallState> = _callState

    init {
        callsState().onEach {
            when (it) {

                is Resources.Success -> when (it.data) {
                    is STWVCall ->
                        _callState.value = CallState.CallReceived(it.data)
                }
                is Resources.Error -> {

                }
                is Resources.Loading -> {


                }
            }

        }.launchIn(viewModelScope)
    }

}