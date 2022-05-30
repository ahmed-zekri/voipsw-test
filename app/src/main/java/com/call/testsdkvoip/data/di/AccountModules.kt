package com.call.testsdkvoip.data.di

import android.content.Context
import com.call.testsdkvoip.domain.use_case.account.AuthenticationUseCases
import com.call.testsdkvoip.domain.use_case.account.LoginUser
import com.call.testsdkvoip.domain.use_case.account.RegisterUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AccountModules {


    @Provides
    @Singleton

    fun providesRegisterUseCase(
        @ApplicationContext context: Context
    ): RegisterUser =
        RegisterUser(context)

    @Provides
    @Singleton

    fun providesLoginUseCase(
        @ApplicationContext context: Context
    ): LoginUser =
        LoginUser(context)

    @Provides
    @Singleton

    fun providesAuthenticationUseCase(
        loginUser: LoginUser,
        registerUser: RegisterUser
    ): AuthenticationUseCases =
        AuthenticationUseCases(registerUser, loginUser)


}