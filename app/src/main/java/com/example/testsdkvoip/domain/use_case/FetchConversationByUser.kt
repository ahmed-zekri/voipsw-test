package com.example.testsdkvoip.domain.use_case

import android.content.Context
import com.example.testsdkvoip.common.Resources
import com.streamwide.smartms.lib.core.api_ktx.contact.model.STWContact
import com.streamwide.smartms.lib.core.api_ktx.messages.STWMessagesApi
import com.streamwide.smartms.lib.core.api_ktx.messages.model.STWConversation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchConversationByUser @Inject constructor(
    private val stwMessagesApi: STWMessagesApi,
    private val context: Context
) {
    operator fun invoke(stwContact: STWContact): Flow<Resources<STWConversation>> =
        flow {

            emit(Resources.Loading())
            try {
                val conversation =
                    stwMessagesApi.getConversationBySingleContact(context, stwContact)
                emit(Resources.Success(conversation))
            } catch (exception: Exception) {
                emit(Resources.Error(exception.message))

            }

        }
}