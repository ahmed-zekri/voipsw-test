package com.call.testsdkvoip.presentation.contacts.components


import com.streamwide.smartms.lib.core.api_ktx.call.voipchannels.model.STWVoipChannel
import com.streamwide.smartms.lib.core.api_ktx.contact.model.STWContact


data class FetchContactsState(

    val isLoading: Boolean = false,
    val contactsList: List<STWContact>? = null,
    val voipChannels: List<STWVoipChannel>? = null,
    val error: String? = null,
    val success: Boolean = false
)