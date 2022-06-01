package com.call.testsdkvoip.common

import com.streamwide.smartms.lib.core.network.voip.STWVCall

interface GlobalListener {
    fun onCallReceived(stwCall: STWVCall)
}
