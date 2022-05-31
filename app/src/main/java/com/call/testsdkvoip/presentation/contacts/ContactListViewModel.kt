package com.call.testsdkvoip.presentation.contacts

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.call.testsdkvoip.common.Resources
import com.call.testsdkvoip.domain.use_case.call.CallUser
import com.call.testsdkvoip.domain.use_case.call.HangCall
import com.call.testsdkvoip.domain.use_case.contacts.FetchContactsList
import com.call.testsdkvoip.presentation.contacts.components.FetchContactsState
import com.streamwide.smartms.lib.core.network.voip.STWVCall
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ContactListViewModel @Inject constructor(

    private val fetchContactsListUseCase: FetchContactsList,
    private val callUser: CallUser,
    private val hangCall: HangCall,


    ) : ViewModel() {

    private val _fetchContactsState = mutableStateOf(FetchContactsState())
    val fetchContactsState: State<FetchContactsState> = _fetchContactsState


    fun hangCall(call: STWVCall) {
        hangCall.invoke(call).onEach {
            when (it) {

                is Resources.Success -> {
                    _fetchContactsState.value = FetchContactsState(

                        contactsList = fetchContactsState.value.contactsList

                    )

                }
                is Resources.Error -> {
                    _fetchContactsState.value = FetchContactsState(
                        error = it.message, contactsList = fetchContactsState.value.contactsList

                    )

                }


                is Resources.Loading -> {
                    _fetchContactsState.value = FetchContactsState(
                        isLoading = true, contactsList = fetchContactsState.value.contactsList

                    )

                }

            }


        }.launchIn(viewModelScope)

    }

    fun callUserNumber(phoneNumber: String) =
        callUser(phoneNumber).onEach {

            when (it) {

                is Resources.Success -> {
                    _fetchContactsState.value = FetchContactsState(
                        callInProgress = it.data,
                        contactsList = fetchContactsState.value.contactsList

                    )

                }
                is Resources.Error -> {
                    _fetchContactsState.value = FetchContactsState(
                        error = it.message, contactsList = fetchContactsState.value.contactsList

                    )

                }


                is Resources.Loading -> {
                    _fetchContactsState.value = FetchContactsState(
                        isLoading = true, contactsList = fetchContactsState.value.contactsList

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