package com.call.testsdkvoip.data.di

import android.content.Context
import com.call.testsdkvoip.domain.use_case.call.CallUser
import com.call.testsdkvoip.domain.use_case.call.HangCall
import com.call.testsdkvoip.domain.use_case.call.ListenForCallsState
import com.call.testsdkvoip.domain.use_case.call.LoadVoipChannels
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

class CallModules {

    @Provides
    @Singleton
    fun providesLoadVoipChannels(
        @ApplicationContext context: Context
    ): LoadVoipChannels =
        LoadVoipChannels(context)

    @Provides
    @Singleton
    fun providesCallUser(@ApplicationContext context: Context): CallUser = CallUser(context)


    @Provides
    @Singleton
    fun providesHangCall(@ApplicationContext context: Context): HangCall = HangCall(context)

    @Provides
    @Singleton
    fun providesListenForCalls(): ListenForCallsState = ListenForCallsState()


}