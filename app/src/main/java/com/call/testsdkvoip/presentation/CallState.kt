package com.call.testsdkvoip.presentation

import com.streamwide.smartms.lib.core.network.voip.STWVCall

sealed class CallState(item: Any) {
    class CallReceived(stwCall: STWVCall) : CallState(stwCall)
    class Idle : CallState(Unit)
}