package com.call.testsdkvoip.presentation.contacts

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.call.testsdkvoip.common.Resources
import com.call.testsdkvoip.domain.use_case.contacts.FetchContactsList
import com.call.testsdkvoip.presentation.contacts.components.FetchContactsState
import com.streamwide.smartms.lib.core.api_ktx.contact.model.STWContact
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ContactListViewModel @Inject constructor(

    private val fetchContactsListUseCase: FetchContactsList,


    ) : ViewModel() {
    private lateinit var initialList: List<STWContact>
    private val _fetchContactsState = mutableStateOf(FetchContactsState())
    val fetchContactsState: State<FetchContactsState> = _fetchContactsState


    fun fetchContactsList(


    ) =
        fetchContactsListUseCase.invoke().onEach {
            when (it) {
                is Resources.Loading -> _fetchContactsState.value =
                    FetchContactsState(isLoading = true)
                is Resources.Success -> {
                    _fetchContactsState.value =
                        FetchContactsState(contactsList = it.data)
                    initialList = it.data ?: listOf()
                }

                is Resources.Error -> _fetchContactsState.value =
                    FetchContactsState(error = it.message)


            }

        }.launchIn(viewModelScope)

    fun filterContactList(value: String) {
        if (_fetchContactsState.value.contactsList != null)
            if (value == "")
                _fetchContactsState.value = FetchContactsState(contactsList = initialList)
            else
                _fetchContactsState.value =
                    FetchContactsState(contactsList = fetchContactsState.value.contactsList?.filter { contact ->

                        contact.displayName?.contains(value) ?: true
                    })
    }


}