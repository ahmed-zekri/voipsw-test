package com.example.testsdkvoip.data.di

import android.content.Context
import com.example.testsdkvoip.domain.use_case.AuthenticationUseCases
import com.example.testsdkvoip.domain.use_case.LoginUser
import com.example.testsdkvoip.domain.use_case.RegisterUser
import com.streamwide.smartms.lib.core.api.account.STWAccountManager
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


}