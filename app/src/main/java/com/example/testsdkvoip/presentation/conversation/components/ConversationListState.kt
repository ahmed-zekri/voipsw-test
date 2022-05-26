package com.example.testsdkvoip.presentation.conversation.components

import androidx.paging.PagingData
import com.streamwide.smartms.lib.core.api_ktx.messages.model.STWBaseMessage
import com.streamwide.smartms.lib.core.api_ktx.messages.model.STWConversation
import kotlinx.coroutines.flow.Flow

data class ConversationListState(
    val messages: Flow<PagingData<STWBaseMessage>>? = null,
    val stwConversation: STWConversation? = null

)