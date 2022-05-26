package com.example.testsdkvoip.domain.use_case.messaging

data class ConversationUseCases(
    val loadConversation: LoadConversation,
    val getSingleConversation: GetSingleConversation,
    val sendMessage: SendMessage
)