package com.call.testsdkvoip.data.di

import android.content.Context
import com.call.testsdkvoip.domain.use_case.messaging.ConversationUseCases
import com.call.testsdkvoip.domain.use_case.messaging.GetSingleConversation
import com.call.testsdkvoip.domain.use_case.messaging.LoadConversation
import com.call.testsdkvoip.domain.use_case.messaging.SendMessage
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

    fun providesGetSingleConversation(
        @ApplicationContext context: Context
    ): GetSingleConversation =
        GetSingleConversation(context)

    @Provides
    @Singleton
    fun providesLoadConversation(
        @ApplicationContext context: Context
    ): LoadConversation =
        LoadConversation(context)


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

        @ApplicationContext context: Context
    ): SendMessage =
        SendMessage(

            context = context
        )

}