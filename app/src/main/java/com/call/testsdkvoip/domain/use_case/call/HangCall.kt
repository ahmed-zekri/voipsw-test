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
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HangCall @Inject constructor(

    private val context: Context
) {
    operator fun invoke(conversationId: String): Flow<Resources<Unit>> =

        flow {

            emit(Resources.Loading())
            try {
                val voipSessionItem = STWCallManager.getInstance()
                    .getActiveVoipSessionByThreadId(context, conversationId)
                val sessionHolder =
                    STWCallManager.getInstance().getCallWithID(voipSessionItem!!.sessionId!!)

                STWCallManager.getInstance().stopCall(
                    context, sessionHolder
                )
                emit(Resources.Success(Unit))


            } catch (exception: Exception) {
                emit(Resources.Error(exception.message))


            }


        }


}



