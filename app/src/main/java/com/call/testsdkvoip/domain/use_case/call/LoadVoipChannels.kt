package com.call.testsdkvoip.domain.use_case.call

import android.content.Context
import com.call.testsdkvoip.common.Resources
import com.streamwide.smartms.lib.core.api_ktx.call.STWCallApi
import com.streamwide.smartms.lib.core.api_ktx.call.voipchannels.model.STWVoipChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoadVoipChannels @Inject constructor(
    private val stwCallApi: STWCallApi,
    private val context: Context
) {
    operator fun invoke(): Flow<Resources<List<STWVoipChannel>>> =

        flow {

            emit(Resources.Loading())
            try {

                val voipChannels =
                    stwCallApi.getAllVoipChannels(
                        context,
                        activeFirst = true,
                        activeChannelOrderedByLevel = true, searchContent = null
                    )
                emit(Resources.Success(voipChannels))


            } catch (exception: Exception) {
                emit(Resources.Error(exception.message))


            }


        }


}



