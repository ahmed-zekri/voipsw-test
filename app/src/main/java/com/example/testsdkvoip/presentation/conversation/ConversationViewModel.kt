package com.example.testsdkvoip.presentation.conversation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testsdkvoip.common.Resources
import com.example.testsdkvoip.domain.use_case.messaging.ConversationUseCases
import com.example.testsdkvoip.presentation.conversation.components.ConversationListState
import com.streamwide.smartms.lib.core.api_ktx.contact.model.STWContact
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
            when (it) {
                is Resources.Error -> ConversationListState(error = it.message)
                is Resources.Loading -> ConversationListState(isLoading = true)
                is Resources.Success -> {
                    _conversationListState.value = ConversationListState(stwConversation = it.data)
                    conversationUseCases.loadConversation(it.data!!).onEach { messagesResponse ->
                        when (messagesResponse) {
                            is Resources.Error -> _conversationListState.value =
                                ConversationListState(error = messagesResponse.message)

                            is Resources.Success -> _conversationListState.value =
                                ConversationListState(
                                    stwConversation = it.data,
                                    messages = messagesResponse.data
                                )


                            is Resources.Loading -> _conversationListState.value =
                                ConversationListState(isLoading = true)

                        }

                    }.launchIn(viewModelScope)

                }


            }

        }.launchIn(viewModelScope)


    fun sendMessage(message: String, completionCallback: () -> Unit) {
        conversationUseCases.sendMessage(
            conversationListState.value.stwConversation!!,
            message
        )
            .onEach {
                when (it) {
                    is Resources.Success -> {
                        completionCallback()

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