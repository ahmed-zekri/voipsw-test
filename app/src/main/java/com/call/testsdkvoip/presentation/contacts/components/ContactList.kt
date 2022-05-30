package com.call.testsdkvoip.presentation.contacts.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.call.testsdkvoip.common.CONTACT_PARAM
import com.call.testsdkvoip.presentation.contacts.ContactListViewModel
import com.call.testsdkvoip.presentation.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun ContactList(
    contactListViewModel: ContactListViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val fetchContactsState = contactListViewModel.fetchContactsState.value


    LaunchedEffect(key1 = true) { contactListViewModel.fetchContactsList() }
    Box(modifier = Modifier.fillMaxSize()) {
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
                    if (fetchContactsState.callInProgress)
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

                            Text(
                                text = "Calling",
                                modifier = Modifier
                                    .align(Alignment.CenterStart)
                                    .padding(5.dp)
                            )
                            Button(
                                onClick = {


                                },
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(5.dp)
                            ) { Text(text = "Hang") }
                        }
                }

        SnackbarHost(
            hostState = snackBarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }


}