package com.call.testsdkvoip.presentation.contacts

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.call.testsdkvoip.common.Resources
import com.call.testsdkvoip.domain.use_case.call.CallUser
import com.call.testsdkvoip.domain.use_case.call.LoadVoipChannels
import com.call.testsdkvoip.domain.use_case.contacts.FetchContactsList
import com.call.testsdkvoip.presentation.contacts.components.FetchContactsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ContactListViewModel @Inject constructor(

    private val fetchContactsListUseCase: FetchContactsList, private val callUser: CallUser,
    loadVoipChannels: LoadVoipChannels

) : ViewModel() {

    private val _fetchContactsState = mutableStateOf(FetchContactsState())
    val fetchContactsState: State<FetchContactsState> = _fetchContactsState

    init {
        loadVoipChannels().onEach {

            when (it) {
                is Resources.Success -> _fetchContactsState.value =
                    FetchContactsState(voipChannels = it.data)


            }


        }.launchIn(viewModelScope)
    }

    fun hangCall() {


    }

    fun callUserNumber(phoneNumber: String) =
        callUser(phoneNumber).onEach {

            when (it) {

                is Resources.Success -> {
                    _fetchContactsState.value = FetchContactsState(
                        callInProgress = true,
                        contactsList = fetchContactsState.value.contactsList,
                        voipChannels = fetchContactsState.value.voipChannels
                    )

                }
                is Resources.Error -> {
                    _fetchContactsState.value = FetchContactsState(
                        error = it.message, contactsList = fetchContactsState.value.contactsList,
                        voipChannels = fetchContactsState.value.voipChannels
                    )

                }


                is Resources.Loading -> {
                    _fetchContactsState.value = FetchContactsState(
                        isLoading = true, contactsList = fetchContactsState.value.contactsList,
                        voipChannels = fetchContactsState.value.voipChannels
                    )

                }

            }

        }.launchIn(viewModelScope)

    fun fetchContactsList(


    ) =
        fetchContactsListUseCase.invoke().onEach {
            when (it) {
                is Resources.Loading -> _fetchContactsState.value =
                    FetchContactsState(isLoading = true)
                is Resources.Success -> _fetchContactsState.value =
                    FetchContactsState(contactsList = it.data)

                is Resources.Error -> _fetchContactsState.value =
                    FetchContactsState(error = it.message)


            }

        }.launchIn(viewModelScope)


}