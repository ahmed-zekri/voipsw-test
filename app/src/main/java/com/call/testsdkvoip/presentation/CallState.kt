package com.call.testsdkvoip.presentation

import com.streamwide.smartms.lib.core.data.item.VoipSessionItem
import com.streamwide.smartms.lib.core.network.voip.STWVCall

sealed class CallState(
    val item: Any? = null

) {
    class CallReceived(stwCall: STWVCall) : CallState(stwCall)
    class CallInProgress(stwCall: STWVCall) : CallState(stwCall)
    class CallStopped(voipSessionItem: VoipSessionItem) : CallState(voipSessionItem)
    class Calling(stwCall: STWVCall) : CallState(stwCall)
    object Idle : CallState()

}