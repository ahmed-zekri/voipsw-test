package com.call.testsdkvoip.presentation.call.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.call.testsdkvoip.presentation.CallStateViewModel
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons
import com.streamwide.smartms.lib.core.network.voip.STWVCall
import kotlinx.coroutines.delay

@Composable
fun CallReceivedDialog(callStateViewModel: CallStateViewModel = hiltViewModel()) {
    val callState = callStateViewModel.callState.value
    val callingText = remember {
        mutableStateOf("Calling")
    }
    Box(
        modifier = Modifier

            .padding(10.dp)
            .fillMaxWidth()

            .background(
                brush = Brush.horizontalGradient(
                    listOf(
                        Color.Gray,
                        Color.LightGray
                    )
                ),
                shape = RoundedCornerShape(15.dp)
            )
    ) {
        LaunchedEffect(key1 = true) {

            var dots = ""

            while (true) {
                delay(1000)
                if (dots.length <= 4)
                    dots += "."
                else dots = ""

                callingText.value = "Calling $dots"
            }
        }
        Text(
            text = callingText.value,
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(5.dp)
        )
        FaIcon(
            faIcon = FaIcons.PhoneSlash,
            size = 24.dp, tint = Color.Red,
            modifier = Modifier
                .clickable {
                    callStateViewModel.hangCall(callState.item as STWVCall)


                }
                .align(Alignment.CenterEnd)
                .padding(15.dp))
    }


}