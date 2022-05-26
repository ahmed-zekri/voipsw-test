package com.example.testsdkvoip.domain.use_case.messaging

import android.content.Context
import androidx.paging.PagingData
import com.example.testsdkvoip.common.Resources
import com.streamwide.smartms.lib.core.api_ktx.messages.STWMessagesApi
import com.streamwide.smartms.lib.core.api_ktx.messages.model.STWBaseMessage
import com.streamwide.smartms.lib.core.api_ktx.messages.model.STWConversation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoadConversation @Inject constructor(
    private val stwMessagesApi: STWMessagesApi,
    private val context: Context
) {
    operator fun invoke(stwConversation: STWConversation): Flow<Resources<Flow<PagingData<STWBaseMessage>>>> =

        flow {

            emit(Resources.Loading())
            try {

                val messages =
                    stwMessagesApi.getMessagesByThreadId(
                        context,
                        stwConversation.threadId!!
                    )
                emit(Resources.Success(messages))

            } catch (exception: Exception) {
                emit(Resources.Error(exception.message))


            }


        }


}



