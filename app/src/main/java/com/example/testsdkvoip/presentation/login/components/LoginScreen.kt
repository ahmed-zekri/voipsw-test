package com.example.testsdkvoip.presentation.login.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testsdkvoip.presentation.login.LoginViewModel

@Composable
fun LoginScreen(loginViewModel: LoginViewModel = hiltViewModel()) {

    val context = LocalContext.current


    Box(modifier = Modifier.fillMaxSize())
    {
        Button(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.Center)) {
            Text(text = if (!loginViewModel.stwAccountManager.isUserAuthenticated(context)) "Login" else "Contacts")

        }

    }


}