package com.call.testsdkvoip.common

import com.streamwide.smartms.lib.core.data.item.VoipSessionItem
import com.streamwide.smartms.lib.core.network.voip.STWVCall

interface GlobalListener {
    fun onCallReceived(stwCall: STWVCall)
    fun onCallInitiated(stwCall: STWVCall)
    fun onCallStopped(voipSessionItem: VoipSessionItem)
    fun onCallInProgress(stwCall: STWVCall)
}
