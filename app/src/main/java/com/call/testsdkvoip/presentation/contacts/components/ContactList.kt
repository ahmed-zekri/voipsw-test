package com.call.testsdkvoip.presentation.contacts.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.call.testsdkvoip.common.CONTACT_PARAM
import com.call.testsdkvoip.presentation.CallState
import com.call.testsdkvoip.presentation.CallStateListenerViewModel
import com.call.testsdkvoip.presentation.contacts.ContactListViewModel
import com.call.testsdkvoip.presentation.navigation.Screen
import com.guru.fontawesomecomposelib.FaIcon
import com.guru.fontawesomecomposelib.FaIcons
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ContactList(
    contactListViewModel: ContactListViewModel = hiltViewModel(),
    navHostController: NavHostController,
    callStateListenerViewModel: CallStateListenerViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val fetchContactsState = contactListViewModel.fetchContactsState.value
    val callState = callStateListenerViewModel.callState.value
    val callingText = remember {
        mutableStateOf("Calling")
    }


    LaunchedEffect(key1 = true) { contactListViewModel.fetchContactsList() }
    Box(modifier = Modifier.fillMaxSize()) {
        if (callState is CallState.CallReceived) {

            Text(text = "Call received")

        }
        if (fetchContactsState.isLoading)

            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        else
            if (fetchContactsState.error != null) {
                LaunchedEffect(key1 = true) {
                    scope.launch {
                        snackBarHostState.showSnackbar(
                            fetchContactsState.error, duration = SnackbarDuration.Indefinite
                        )
                    }

                }
            } else
                if (fetchContactsState.contactsList != null) {
                    LazyColumn(modifier = Modifier.align(Alignment.Center)) {

                        items(fetchContactsState.contactsList) { contact ->
                            ContactListItem(contact = contact, onClick = {
                                navHostController.currentBackStackEntry?.savedStateHandle?.set(
                                    CONTACT_PARAM,
                                    contact
                                )
                                navHostController.navigate(Screen.Conversation.route)
                            })
                        }
                    }
                    if (fetchContactsState.callInProgress != null)
                        Box(
                            modifier = Modifier

                                .padding(10.dp)
                                .fillMaxWidth()
                                .blur(30.dp)
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
                                        contactListViewModel.hangCall(fetchContactsState.callInProgress)


                                    }
                                    .align(Alignment.CenterEnd)
                                    .padding(15.dp))
                        }
                }

        SnackbarHost(
            hostState = snackBarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }


}