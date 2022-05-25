package com.example.testsdkvoip.presentation.intro.components

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
import com.example.testsdkvoip.presentation.intro.IntroViewModel
import com.example.testsdkvoip.presentation.navigation.Screen

@Composable
fun IntroScreen(
    introViewModel: IntroViewModel = hiltViewModel(),
    navHostController: NavHostController
) {

    val context = LocalContext.current


    Box(modifier = Modifier.fillMaxSize())
    {
        Button(
            onClick = {
                navHostController.navigate(
                    if (!introViewModel.stwAccountManager.isUserAuthenticated(context)) Screen.Login.route
                    else Screen.ContactList.route
                ) {
                    popUpTo( Screen.Intro.route) {
                        inclusive = true
                    }

                }
            },
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(text = if (!introViewModel.stwAccountManager.isUserAuthenticated(context)) "Login" else "Contacts")


        }

    }


}