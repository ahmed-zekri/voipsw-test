package com.call.testsdkvoip.presentation.call.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.call.testsdkvoip.presentation.CallState
import com.call.testsdkvoip.presentation.CallStateViewModel
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons
import com.streamwide.smartms.lib.core.api.call.CallError
import com.streamwide.smartms.lib.core.api.call.CompletionCallback
import com.streamwide.smartms.lib.core.api.call.STWCallManager
import com.streamwide.smartms.lib.core.api.call.STWCallRefuseReason
import com.streamwide.smartms.lib.core.network.voip.STWVCall
import kotlinx.coroutines.delay

@Composable
fun CallReceivedScreen(
    stwCall: STWVCall, callStateViewModel: CallStateViewModel = hiltViewModel()

) = Box(modifier = Modifier.fillMaxSize()) {
    val context = LocalContext.current

    val counterSeconds = remember {
        mutableStateOf(0)
    }
    val counterMinutes = remember {
        mutableStateOf(0)
    }

    if (callStateViewModel.callState.value is CallState.CallReceived)
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            FaIcon(
                faIcon = FaIcons.Phone,
                size = 24.dp, tint = Color.Green,
                modifier = Modifier

                    .padding(start = 25.dp)
                    .clickable {
                        STWCallManager
                            .getInstance()
                            .acceptCall(context, stwCall, object : CompletionCallback{
                                override fun onError(p0: CallError) {

                                }

                                override fun onCompletion(p0: STWVCall) {

                                }
                            })

                    }

                    .padding(15.dp))

            FaIcon(
                faIcon = FaIcons.PhoneSlash,
                size = 24.dp, tint = Color.Red,
                modifier = Modifier

                    .padding(end = 25.dp)
                    .clickable {
                        STWCallManager
                            .getInstance()
                            .refuseVoipCall(context, stwCall, STWCallRefuseReason.DECLINE)

                    }

                    .padding(15.dp))


        } else {
        LaunchedEffect(key1 = true) {
            while (true) {
                delay(1000)
                counterSeconds.value += 1

                if (counterSeconds.value >= 60) {
                    counterSeconds.value = 0
                    counterMinutes.value += 1
                }
            }


        }
        Column {
            FaIcon(
                faIcon = FaIcons.PhoneSlash,
                size = 24.dp, tint = Color.Red,
                modifier = Modifier

                    .padding(end = 25.dp)
                    .align(CenterHorizontally)
                    .clickable {
                        STWCallManager
                            .getInstance()
                            .stopCall(context, stwCall)

                    }

                    .padding(15.dp))

            Text(
                text = "${counterMinutes.value} : ${counterSeconds.value}",
                modifier = Modifier.align(CenterHorizontally)
            )
        }

    }

}