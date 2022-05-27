package com.call.testsdkvoip.domain.use_case.account

import android.content.Context
import com.call.testsdkvoip.common.Resources
import com.streamwide.smartms.lib.core.api.account.STWAccountError
import com.streamwide.smartms.lib.core.api.account.STWAccountManager
import com.streamwide.smartms.lib.core.api.account.login.CompletionCallback
import com.streamwide.smartms.lib.core.api.account.login.RegistrationCallback
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class LoginUser @Inject constructor(
    private val stwAccountManager: STWAccountManager,
    private val context: Context
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(activationCode: String): Flow<Resources<Unit>> =
        callbackFlow {

            trySend(Resources.Loading())
            stwAccountManager.login(context,
                RegistrationCallback.LoginType.ACTIVATION_CODE_PROVIDED_BY_ADMINISTRATOR,
                activationCode,
                object : CompletionCallback {
                    override fun onError(p0: STWAccountError) {
                        trySend(Resources.Error(p0.mMessage))
                    }

                    override fun onSuccess() {
                        trySend(Resources.Success(Unit))
                        stwAccountManager.acceptNewDevice(context)
                    }

                    override fun onSynchronizationStarted() {

                    }

                    override fun onSynchronizationFinished() {

                    }
                });

            awaitClose {}

        }
}