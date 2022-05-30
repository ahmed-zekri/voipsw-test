package com.call.testsdkvoip.data.di

import android.content.Context
import com.call.testsdkvoip.domain.use_case.contacts.FetchContactsList
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

    fun providesFetchContactsListUseCase(
        @ApplicationContext context: Context
    ): FetchContactsList =
        FetchContactsList( context)

}