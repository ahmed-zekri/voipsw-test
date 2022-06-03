package com.call.testsdkvoip.domain.use_case.call

import android.content.Context
import com.call.testsdkvoip.common.Resources
import com.streamwide.smartms.lib.core.api.call.CallError
import com.streamwide.smartms.lib.core.api.call.CompletionCallback
import com.streamwide.smartms.lib.core.api.call.STWCallManager
import com.streamwide.smartms.lib.core.api.call.STWCallPriority
import com.streamwide.smartms.lib.core.network.voip.STWVCall
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class CallUser @Inject constructor(

    private val context: Context
) {
    operator fun invoke(phoneNumber: String): Flow<Resources<STWVCall>> =

        callbackFlow {

            trySend(Resources.Loading())
            try {


                STWCallManager.getInstance().startFreeCall(
                    context, phoneNumber, STWCallPriority.NORMAL, object : CompletionCallback {
                        override fun onError(p0: CallError) {
                            trySend(Resources.Error(p0.message))
                        }

                        override fun onCompletion(p0: STWVCall) {
                            trySend(Resources.Success(p0))
                        }
                    }
                )


            } catch (exception: Exception) {
                trySend(Resources.Error("Exception message ${exception.message}"))


            }

            awaitClose {}
        }


}



