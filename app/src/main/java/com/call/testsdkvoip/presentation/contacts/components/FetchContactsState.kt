package com.call.testsdkvoip.presentation.contacts.components


import com.streamwide.smartms.lib.core.api_ktx.call.voipchannels.model.STWVoipChannel
import com.streamwide.smartms.lib.core.api_ktx.contact.model.STWContact
import com.streamwide.smartms.lib.core.network.voip.STWVCall


data class FetchContactsState(

    val isLoading: Boolean = false,
    val contactsList: List<STWContact>? = null,

    val error: String? = null,
    val success: Boolean = false,
    val callInProgress: STWVCall? = null
)