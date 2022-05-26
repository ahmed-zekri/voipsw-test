package com.example.testsdkvoip.presentation.conversation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.testsdkvoip.presentation.conversation.ConversationViewModel
import com.streamwide.smartms.lib.core.api_ktx.contact.model.STWContact

@Composable
fun Conversation(
    conversationViewModel: ConversationViewModel = hiltViewModel(),
    stwContact: STWContact
) {
    val context = LocalContext.current
    val conversationListState = conversationViewModel.conversationListState.value
    LaunchedEffect(key1 = true) {
        conversationViewModel.fetchConversation(stwContact = stwContact)


    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (conversationListState.stwConversation != null)
            LaunchedEffect(key1 = true) {
                conversationViewModel.sendMessage("cdscdsvd")


            }

        if (conversationListState.messages != null) {
            MessagesList(list = conversationListState.messages.collectAsLazyPagingItems())

            LaunchedEffect(key1 = true) {


            }
        }


    }
}