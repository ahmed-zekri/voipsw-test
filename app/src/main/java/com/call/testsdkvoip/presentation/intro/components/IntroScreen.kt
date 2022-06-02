package com.call.testsdkvoip.presentation.intro.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.call.testsdkvoip.presentation.CallState
import com.call.testsdkvoip.presentation.CallStateViewModel
import com.call.testsdkvoip.presentation.call.components.CallReceivedScreen
import com.call.testsdkvoip.presentation.navigation.Screen
import com.streamwide.smartms.lib.core.api.account.STWAccountManager
import com.streamwide.smartms.lib.core.network.voip.STWVCall

@Composable
fun IntroScreen(

    navHostController: NavHostController, callStateViewModel: CallStateViewModel = hiltViewModel()
) {
    val callState = callStateViewModel.callState.value
    val context = LocalContext.current
    if (callState is CallState.CallReceived || callState is CallState.CallInProgress)

        CallReceivedScreen(callState.item as STWVCall)

    Box(modifier = Modifier.fillMaxSize())
    {
        Button(
            onClick = {
                navHostController.navigate(
                    if (!STWAccountManager.getInstance()
                            .isUserAuthenticated(context)
                    ) Screen.Login.route
                    else Screen.ContactList.route
                ) {
                    popUpTo(Screen.Intro.route) {
                        inclusive = true
                    }

                }
            },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = if (!STWAccountManager.getInstance()
                        .isUserAuthenticated(context)
                ) "Login" else "Contacts"
            )


        }

    }


}