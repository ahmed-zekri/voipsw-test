package com.call.testsdkvoip.presentation.call.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons
import com.streamwide.smartms.lib.core.api.call.CallError
import com.streamwide.smartms.lib.core.api.call.CompletionCallback
import com.streamwide.smartms.lib.core.api.call.STWCallManager
import com.streamwide.smartms.lib.core.network.voip.STWVCall

@Composable
fun CallReceivedScreen(
    stwCall: STWVCall,

    ) = Box(modifier = Modifier.blur(5.dp)) {
    val context = LocalContext.current
    FaIcon(
        faIcon = FaIcons.Phone,
        size = 24.dp, tint = Color.Green,
        modifier = Modifier
            .clickable {
                STWCallManager
                    .getInstance()
                    .acceptCall(context, stwCall, object : CompletionCallback {
                        override fun onError(p0: CallError) {

                        }

                        override fun onCompletion(p0: STWVCall) {

                        }
                    })

            }
            .align(Alignment.CenterEnd)
            .padding(15.dp))

}