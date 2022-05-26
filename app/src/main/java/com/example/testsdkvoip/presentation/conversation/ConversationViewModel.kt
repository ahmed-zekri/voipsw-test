package com.example.testsdkvoip.presentation.conversation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testsdkvoip.common.Resources
import com.example.testsdkvoip.domain.use_case.messaging.ConversationUseCases
import com.example.testsdkvoip.presentation.conversation.components.ConversationListState
import com.streamwide.smartms.lib.core.api_ktx.contact.model.STWContact
import com.streamwide.smartms.lib.core.api_ktx.messages.model.STWConversation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ConversationViewModel @Inject constructor(
    private val conversationUseCases: ConversationUseCases
) :
    ViewModel() {
    private val _conversationListState = mutableStateOf(ConversationListState())
    val conversationListState: State<ConversationListState> = _conversationListState

    fun fetchConversation(stwContact: STWContact) =
        conversationUseCases.getSingleConversation(stwContact).onEach {
            if (it is Resources.Success) {
                _conversationListState.value = ConversationListState(stwConversation = it.data)
                conversationUseCases.loadConversation(it.data!!).onEach { messages ->
                    if (messages is Resources.Success) {
                        _conversationListState.value = ConversationListState(
                            stwConversation = it.data,
                            messages = messages.data
                        )


                    }
                }.launchIn(viewModelScope)
            }
        }.launchIn(viewModelScope)


    fun sendMessage(message: String) {
        conversationUseCases.sendMessage(conversationListState.value.stwConversation!!, message)
            .onEach {
                when (it) {
                    is Resources.Success -> {


                    }
                    is Resources.Error -> {


                    }

                    is Resources.Loading -> {


                    }

                }
            }
            .launchIn(viewModelScope)

    }


}