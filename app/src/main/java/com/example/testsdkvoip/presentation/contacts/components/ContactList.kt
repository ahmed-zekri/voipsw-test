package com.example.testsdkvoip.presentation.contacts.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.testsdkvoip.common.CONTACT_PARAM
import com.example.testsdkvoip.presentation.contacts.ContactListViewModel
import com.example.testsdkvoip.presentation.navigation.Screen

@Composable
fun ContactList(
    contactListViewModel: ContactListViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val fetchContactsState = contactListViewModel.fetchContactsState.value
    LaunchedEffect(key1 = true) { contactListViewModel.fetchContactsList() }
    Box(modifier = Modifier.fillMaxSize()) {
        if (fetchContactsState.isLoading)

            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        else
            if (fetchContactsState.error != null)
                Text(text = fetchContactsState.error)
            else
                if (fetchContactsState.contactsList != null) {
                    LazyColumn(modifier = Modifier.align(Alignment.Center)) {

                        items(fetchContactsState.contactsList) { contact ->
                            ContactListItem(contact = contact) {
                                navHostController.currentBackStackEntry?.savedStateHandle?.set(
                                    CONTACT_PARAM,
                                    contact
                                )
                                navHostController.navigate(Screen.Conversation.route)
                            }
                        }
                    }

                }
    }


}