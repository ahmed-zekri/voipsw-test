package com.call.testsdkvoip.domain.use_case.call

import com.call.testsdkvoip.common.GlobalListener
import com.call.testsdkvoip.common.MainManager
import com.call.testsdkvoip.common.Resources
import com.call.testsdkvoip.presentation.CallState
import com.streamwide.smartms.lib.core.data.item.VoipSessionItem
import com.streamwide.smartms.lib.core.network.voip.STWVCall
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class ListenForCallsState @Inject constructor() : GlobalListener {
    private lateinit var producerScope: ProducerScope<Resources.Success<CallState>>
    operator fun invoke(): Flow<Resources.Success<CallState>> = callbackFlow {
        MainManager.globalListener = this@ListenForCallsState
        producerScope = this


        awaitClose {}
    }

    override fun onCallReceived(stwCall: STWVCall) {
        producerScope.trySend(Resources.Success(CallState.CallReceived(stwCall)))
    }

    override fun onCallInitiated(stwCall: STWVCall) {
        producerScope.trySend(Resources.Success(CallState.Calling(stwCall)))
    }

    override fun onCallStopped(voipSessionItem: VoipSessionItem) {
        producerScope.trySend(Resources.Success(CallState.CallStopped(voipSessionItem)))
    }


    override fun onCallInProgress(stwCall: STWVCall) {
        producerScope.trySend(Resources.Success(CallState.CallInProgress(stwCall)))
    }


}


