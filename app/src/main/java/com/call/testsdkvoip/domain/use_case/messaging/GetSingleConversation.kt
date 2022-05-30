package com.call.testsdkvoip.domain.use_case.messaging

import android.content.Context
import com.call.testsdkvoip.common.MessageUtil
import com.call.testsdkvoip.common.Resources
import com.streamwide.smartms.lib.core.api_ktx.contact.model.STWContact
import com.streamwide.smartms.lib.core.api_ktx.messages.STWMessagesApi
import com.streamwide.smartms.lib.core.api_ktx.messages.callback.CreationConversationCallback
import com.streamwide.smartms.lib.core.api_ktx.messages.model.ConversationTypes
import com.streamwide.smartms.lib.core.api_ktx.messages.model.MessagingError
import com.streamwide.smartms.lib.core.api_ktx.messages.model.STWConversation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetSingleConversation @Inject constructor(
 
    private val context: Context
) {
    @OptIn(ExperimentalCoroutinesApi::class)
    operator fun invoke(stwContact: STWContact): Flow<Resources<STWConversation>> =

        callbackFlow {
            val flowCollector = this
            trySend(Resources.Loading())
            try {
                var conversation =
                    STWMessagesApi.getConversationBySingleContact(context, stwContact)
                if (conversation != null)
                    trySend(Resources.Success(conversation))
                else {
                    trySend(Resources.Loading())
                    val recipients = MessageUtil.prepareParticipantArrayString(listOf(stwContact))

                    STWMessagesApi.createConversation(
                        context,
                        conversationType = ConversationTypes.ONE_TO_ONE,
                        recipients = recipients, callback = object : CreationConversationCallback {
                            override fun completion(conversationId: Int?) {
                                if (conversationId == null)
                                    trySend(Resources.Error("Unable to create conversation"))
                                else
                                    flowCollector.launch {
                                        conversation = STWMessagesApi.getConversationById(
                                            context,
                                            conversationId
                                        )
                                        trySend(Resources.Success(conversation))
                                    }

                            }

                            override fun error(error: MessagingError) {
                                trySend(Resources.Error(error.message))
                            }

                        }, conversationName = "", isAvailableOnlyRecipients = true
                    )

                }
            } catch (exception: Exception) {
                trySend(Resources.Error(exception.message))


            }
            awaitClose {}


        }


}