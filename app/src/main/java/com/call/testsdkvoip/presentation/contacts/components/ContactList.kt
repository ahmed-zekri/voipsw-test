package com.call.testsdkvoip.presentation.contacts.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.call.testsdkvoip.common.CONTACT_PARAM
import com.call.testsdkvoip.presentation.CallState
import com.call.testsdkvoip.presentation.CallStateViewModel
import com.call.testsdkvoip.presentation.call.components.CallReceivedDialog
import com.call.testsdkvoip.presentation.call.components.CallReceivedScreen
import com.call.testsdkvoip.presentation.contacts.ContactListViewModel
import com.call.testsdkvoip.presentation.navigation.Screen
import com.streamwide.smartms.lib.core.network.voip.STWVCall
import kotlinx.coroutines.launch

@Composable
fun ContactList(
    contactListViewModel: ContactListViewModel = hiltViewModel(),
    navHostController: NavHostController,
    callStateViewModel: CallStateViewModel = hiltViewModel()
) {
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    val fetchContactsState = contactListViewModel.fetchContactsState.value
    val callState = callStateViewModel.callState.value
    val searchTerm = remember {
        mutableStateOf("")
    }


    LaunchedEffect(key1 = true) { contactListViewModel.fetchContactsList() }
    Column {
        if (callState is CallState.CallReceived || callState is CallState.CallInProgress)

            CallReceivedScreen(callState.item as STWVCall)
        if (callState is CallState.Calling)
            CallReceivedDialog()
        Column {
            TextField(
                value = searchTerm.value, modifier = Modifier
                    .fillMaxWidth()
                    .padding(),
                onValueChange = {
                    searchTerm.value = it
                    contactListViewModel.filterContactList(searchTerm.value)
                })

            Box(modifier = Modifier.fillMaxSize()) {


                if (fetchContactsState.isLoading)

                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                else
                    if (fetchContactsState.error != null)
                        LaunchedEffect(key1 = true) {
                            scope.launch {
                                snackBarHostState.showSnackbar(
                                    fetchContactsState.error, duration = SnackbarDuration.Indefinite
                                )
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

                        }

                SnackbarHost(
                    hostState = snackBarHostState,
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }

    }


}