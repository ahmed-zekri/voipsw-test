package com.call.testsdkvoip.data.di

import android.content.Context
import com.call.testsdkvoip.domain.use_case.messaging.ConversationUseCases
import com.call.testsdkvoip.domain.use_case.messaging.GetSingleConversation
import com.call.testsdkvoip.domain.use_case.messaging.LoadConversation
import com.call.testsdkvoip.domain.use_case.messaging.SendMessage
import com.streamwide.smartms.lib.core.api_ktx.messages.STWMessagesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class MessagingModules {

    @Provides
    @Singleton
    fun providesSTWMessagesApi(): STWMessagesApi = STWMessagesApi

    @Provides
    @Singleton

    fun providesGetSingleConversation(
        stwMessagesApi: STWMessagesApi, @ApplicationContext context: Context
    ): GetSingleConversation =
        GetSingleConversation(stwMessagesApi, context)

    @Provides
    @Singleton
    fun providesLoadConversation(
        stwMessagesApi: STWMessagesApi,
        @ApplicationContext context: Context
    ): LoadConversation =
        LoadConversation(stwMessagesApi, context)


    @Provides
    @Singleton
    fun providesConversationUseCases(
        loadConversation: LoadConversation,
        getSingleConversation: GetSingleConversation, sendMessage: SendMessage
    ): ConversationUseCases =
        ConversationUseCases(
            loadConversation = loadConversation,
            getSingleConversation = getSingleConversation,
            sendMessage = sendMessage
        )

    @Provides
    @Singleton
    fun providesSendMessage(
        stwMessagesApi: STWMessagesApi,
        @ApplicationContext context: Context
    ): SendMessage =
        SendMessage(
            stwMessagesApi = stwMessagesApi,
            context = context
        )

}