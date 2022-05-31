package com.call.testsdkvoip.domain.use_case.call

import android.content.Context
import com.call.testsdkvoip.common.Resources
import com.streamwide.smartms.lib.core.api.call.STWCallManager
import com.streamwide.smartms.lib.core.network.voip.STWVCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HangCall @Inject constructor(

    private val context: Context
) {
    operator fun invoke(call: STWVCall): Flow<Resources<Unit>> =

        flow {

            emit(Resources.Loading())
            try {


                STWCallManager.getInstance().stopCall(

                    context, call
                )
                emit(Resources.Success(Unit))


            } catch (exception: Exception) {
                emit(Resources.Error(exception.message))


            }


        }


}