package com.example.testsdkvoip.presentation.conversation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.testsdkvoip.presentation.conversation.ConversationViewModel
import com.streamwide.smartms.lib.core.api_ktx.contact.model.STWContact

@Composable
fun Conversation(
    conversationViewModel: ConversationViewModel = hiltViewModel(),
    stwContact: STWContact
) {
    val messageBody = remember {
        mutableStateOf("")
    }
    val conversationListState = conversationViewModel.conversationListState.value
    LaunchedEffect(key1 = true) {
        conversationViewModel.fetchConversation(stwContact = stwContact)


    }
    if (conversationListState.isLoading)
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))


        }
    else
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceBetween) {


            if (conversationListState.messages != null)
                Row(modifier = Modifier.weight(10f)) {
                    MessagesList(list = conversationListState.messages.collectAsLazyPagingItems())
                }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(value = messageBody.value, onValueChange = { messageBody.value = it })
                Button(onClick = { conversationViewModel.sendMessage(messageBody.value) }) {
                    Text(text = "Send")

                }
            }

        }
}