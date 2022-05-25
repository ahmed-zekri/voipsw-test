package com.example.testsdkvoip.presentation.conversation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testsdkvoip.domain.use_case.FetchConversationByUser
import com.streamwide.smartms.lib.core.api_ktx.contact.model.STWContact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ConversationViewModel @Inject constructor(
    private val fetchConversationByUser: FetchConversationByUser
) :
    ViewModel() {


    fun fetchConversation(stwContact: STWContact) =
        fetchConversationByUser(stwContact).onEach {


        }.launchIn(viewModelScope)
}