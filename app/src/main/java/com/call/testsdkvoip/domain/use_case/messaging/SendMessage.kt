package com.call.testsdkvoip.domain.use_case.messaging

import android.content.Context
import com.call.testsdkvoip.common.Resources
import com.streamwide.smartms.lib.core.api.messaging.CompletionCallback
import com.streamwide.smartms.lib.core.api.messaging.MessagingError
import com.streamwide.smartms.lib.core.api_ktx.messages.STWMessagesApi
import com.streamwide.smartms.lib.core.api_ktx.messages.model.STWConversation
import com.streamwide.smartms.lib.core.data.item.BaseMessage
import com.streamwide.smartms.lib.core.data.item.ThreadItem
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import javax.inject.Inject

class SendMessage @Inject constructor(

    private val context: Context
) {
    operator fun invoke(
        stwConversation: STWConversation,
        message: String
    ): Flow<Resources<Int>> =

        callbackFlow {

            trySend(Resources.Loading())
            try {


                STWMessagesApi.sendMessage(
                    context,
                    message,
                    null,
                    stwConversation.threadId.toString()
                ) {
                    this.isRequireAcknowledgement = true
                    this.callback = object : CompletionCallback,
                        com.streamwide.smartms.lib.core.api_ktx.messages.callback.CompletionCallback {
                        override fun onError(p0: MessagingError) {
                            trySend(Resources.Error(p0.mMessage))
                        }

                        override fun onCompletion(p0: ThreadItem, p1: BaseMessage?) {

                        }

                        override fun completion(messageId: Int?) {
                            trySend(Resources.Success(messageId))
                        }

                        override fun error(error: com.streamwide.smartms.lib.core.api_ktx.messages.model.MessagingError) {

                        }
                    }
                }
            } catch (exception: Exception) {
                trySend(Resources.Error(exception.message))


            }
            awaitClose { }


        }


}