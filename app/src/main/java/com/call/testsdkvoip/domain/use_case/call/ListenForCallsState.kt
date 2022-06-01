package com.call.testsdkvoip.domain.use_case.call

import com.call.testsdkvoip.common.GlobalListener
import com.call.testsdkvoip.common.MainManager
import com.call.testsdkvoip.common.Resources
import com.streamwide.smartms.lib.core.network.voip.STWVCall
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class ListenForCallsState @Inject constructor() : GlobalListener {
    private lateinit var producerScope: ProducerScope<Resources<Any>>
    operator fun invoke(): Flow<Resources<Any>> = callbackFlow {
        MainManager.globalListener = this@ListenForCallsState
        producerScope = this
        trySend(Resources.Loading())
        try {


        } catch (exception: Exception) {
            trySend(Resources.Error(exception.message))


        }

        awaitClose {}
    }

    override fun onCallReceived(stwCall: STWVCall) {
        producerScope.trySend(Resources.Success(stwCall))
    }


}


