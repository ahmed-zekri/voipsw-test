package com.example.testsdkvoip.data.di

import com.streamwide.smartms.lib.core.api.account.STWAccountManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providesSTWAccountManager(): STWAccountManager = STWAccountManager.getInstance()


}