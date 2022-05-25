package com.example.testsdkvoip.presentation.conversation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.testsdkvoip.presentation.conversation.ConversationViewModel
import com.streamwide.smartms.lib.core.api_ktx.contact.model.STWContact

@Composable
fun Conversation(
    conversationViewModel: ConversationViewModel = hiltViewModel(),
    stwContact: STWContact
) {
    LaunchedEffect(key1 = true) {
        conversationViewModel.fetchConversation(stwContact = stwContact)


    }

}