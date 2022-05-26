package com.example.testsdkvoip.data.di

import android.content.Context
import com.example.testsdkvoip.domain.use_case.account.AuthenticationUseCases
import com.example.testsdkvoip.domain.use_case.account.LoginUser
import com.example.testsdkvoip.domain.use_case.account.RegisterUser
import com.example.testsdkvoip.domain.use_case.contacts.FetchContactsList
import com.example.testsdkvoip.domain.use_case.messaging.ConversationUseCases
import com.example.testsdkvoip.domain.use_case.messaging.GetSingleConversation
import com.example.testsdkvoip.domain.use_case.messaging.LoadConversation
import com.example.testsdkvoip.domain.use_case.messaging.SendMessage
import com.streamwide.smartms.lib.core.api.account.STWAccountManager
import com.streamwide.smartms.lib.core.api_ktx.contact.STWContactApi
import com.streamwide.smartms.lib.core.api_ktx.messages.STWMessagesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providesSTWAccountManager(): STWAccountManager = STWAccountManager.getInstance()

    @Provides
    @Singleton
    fun providesSTWMessagesApi(): STWMessagesApi = STWMessagesApi


    @Provides
    @Singleton
    fun providesSTWContactApi(): STWContactApi = STWContactApi

    @Provides
    @Singleton

    fun providesRegisterUseCase(
        stwAccountManager: STWAccountManager,
        @ApplicationContext context: Context
    ): RegisterUser =
        RegisterUser(stwAccountManager, context)

    @Provides
    @Singleton

    fun providesLoginUseCase(
        stwAccountManager: STWAccountManager,
        @ApplicationContext context: Context
    ): LoginUser =
        LoginUser(stwAccountManager, context)

    @Provides
    @Singleton

    fun providesAuthenticationUseCase(
        loginUser: LoginUser,
        registerUser: RegisterUser
    ): AuthenticationUseCases =
        AuthenticationUseCases(registerUser, loginUser)


    @Provides
    @Singleton

    fun providesFetchContactsListUseCase(
        stwContactApi: STWContactApi, @ApplicationContext context: Context
    ): FetchContactsList =
        FetchContactsList(stwContactApi, context)


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