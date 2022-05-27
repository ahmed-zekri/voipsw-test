package com.call.testsdkvoip.data.di

import android.content.Context
import com.call.testsdkvoip.domain.use_case.contacts.FetchContactsList
import com.streamwide.smartms.lib.core.api_ktx.contact.STWContactApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ContactModules {

    @Provides
    @Singleton
    fun providesSTWContactApi(): STWContactApi = STWContactApi

    @Provides
    @Singleton

    fun providesFetchContactsListUseCase(
        stwContactApi: STWContactApi, @ApplicationContext context: Context
    ): FetchContactsList =
        FetchContactsList(stwContactApi, context)

}