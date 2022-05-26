package com.example.testsdkvoip.domain.use_case.account

import android.content.Context
import com.example.testsdkvoip.common.Resources
import com.streamwide.smartms.lib.core.api.account.STWAccountError
import com.streamwide.smartms.lib.core.api.account.STWAccountManager
import com.streamwide.smartms.lib.core.api.account.login.RegisterInfo
import com.streamwide.smartms.lib.core.api.account.login.RegisterOrganisationCallback
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class RegisterUser @Inject constructor(
    private val stwAccountManager: STWAccountManager,
    private val context: Context
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(phoneNumber: String, companyId: String): Flow<Resources<RegisterInfo>> =
        callbackFlow {

            trySend(Resources.Loading())
            stwAccountManager.register(context, phoneNumber, companyId, object :
                RegisterOrganisationCallback {
                override fun onError(p0: STWAccountError) {
                    trySend(Resources.Error(p0.mMessage))

                }

                override fun onSuccess(p0: Int, p1: RegisterInfo) {
                    trySend(Resources.Success(p1))

                }

                override fun onServiceConfigurationSuccess() {

                }

                override fun onServiceOrganizationSuccess() {

                }
            })
            awaitClose {}

        }
}