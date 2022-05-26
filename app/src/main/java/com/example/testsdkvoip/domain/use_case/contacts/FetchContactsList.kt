package com.example.testsdkvoip.domain.use_case.contacts

import android.content.Context
import com.example.testsdkvoip.common.Resources
import com.streamwide.smartms.lib.core.api_ktx.contact.STWContactApi
import com.streamwide.smartms.lib.core.api_ktx.contact.model.STWContact
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FetchContactsList @Inject constructor(
    private val stwContactApi: STWContactApi,
    private val context: Context
) {
    operator fun invoke(): Flow<Resources<List<STWContact>>> =
        flow {

            emit(Resources.Loading())
            try {
                val contacts = stwContactApi.getAllContacts(context)!!.sortedBy { stwContact ->
                    stwContact
                        .sortKey
                }
                emit(Resources.Success(contacts))
            } catch (exception: Exception) {
                emit(Resources.Error(exception.message))

            }

        }
}