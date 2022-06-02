package com.call.testsdkvoip.presentation.contacts.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
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



    LaunchedEffect(key1 = true) { contactListViewModel.fetchContactsList() }
    Box(modifier = Modifier.fillMaxSize()) {
        if (callState is CallState.CallReceived || callState is CallState.CallInProgress)

            CallReceivedScreen(callState.item as STWVCall)
        else
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
                        if (callState is CallState.Calling)
                    CallReceivedDialog()
                    }

        SnackbarHost(
            hostState = snackBarHostState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }


}