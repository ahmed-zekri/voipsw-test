package com.example.testsdkvoip.presentation.conversation.components

import com.streamwide.smartms.lib.core.api_ktx.messages.model.STWConversation

data class FetchConversationState(
    val isLoading: Boolean = false,
    val conversation: STWConversation? = null,
    val error: String? = null,
    val success: Boolean = false
)
