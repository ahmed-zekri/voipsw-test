package com.call.testsdkvoip.presentation.login.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.hilt.navigation.compose.hiltViewModel
import com.call.testsdkvoip.presentation.login.LoginViewModel
import kotlinx.coroutines.launch


@Composable
fun LoginScreen(loginViewModel: LoginViewModel = hiltViewModel()) {

    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val loginState = loginViewModel.loggedInState.value

    val mobilePhone = remember {
        mutableStateOf("")
    }
    val companyId = remember {
        mutableStateOf("")
    }
    val activationCode = remember {
        mutableStateOf("")
    }
    SnackbarHost(hostState = snackBarHostState)

    Box(modifier = Modifier.fillMaxSize()) {

        if (loginState.isLoading)

            CircularProgressIndicator(modifier = Modifier.align(Center))
        else if (loginState.success) {
            Text(text = "You are successfully connected")


        } else if (loginState.registerInfo != null) {
            Column(modifier = Modifier.align(Center)) {
                TextField(
                    modifier = Modifier.align(CenterHorizontally),
                    value = activationCode.value,
                    onValueChange = { activationCode.value = it },
                    label = { Text(text = "activationCode") },
                    placeholder = { Text(text = "Enter activation code received by your administrator") })

                Button(modifier = Modifier.align(CenterHorizontally), onClick = {
                    if (activationCode.value.isEmpty()) {

                        scope.launch { snackBarHostState.showSnackbar("Activation code too short") }

                        return@Button
                    }
                    loginViewModel.loginUser(
                        activationCode.value
                    )
                }, content = { Text(text = "Login") })
            }

        } else if (loginState.error != null)
            Text(
                text = "An error has been detected ${loginState.error}",
                modifier = Modifier.align(Center)
            )
        else {


            Column(modifier = Modifier.align(Center)) {

                TextField(value = mobilePhone.value,
                    keyboardOptions =
                    KeyboardOptions(keyboardType = KeyboardType.Number),

                    onValueChange = { mobilePhone.value = it },
                    label = { Text(text = "Phone number") },
                    placeholder = { Text(text = "Enter your phone number") })


                TextField(value = companyId.value,
                    onValueChange = { companyId.value = it },
                    label = { Text(text = "companyId") },
                    placeholder = { Text(text = "Enter companyId") })

                Button(modifier = Modifier.align(CenterHorizontally), onClick = {
                    if (companyId.value.length < 3 || mobilePhone.value.length < 3) {

                        scope.launch { snackBarHostState.showSnackbar("Data too short") }

                        return@Button
                    }
                    loginViewModel.registerUser(
                        mobilePhone.value, companyId.value
                    )
                }, content = { Text(text = "Register") })

            }


        }


    }

}