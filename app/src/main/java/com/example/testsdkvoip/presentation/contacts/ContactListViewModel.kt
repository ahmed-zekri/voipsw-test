package com.example.testsdkvoip.presentation.contacts

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testsdkvoip.common.Resources
import com.example.testsdkvoip.domain.use_case.FetchContactsList
import com.example.testsdkvoip.presentation.contacts.components.FetchContactsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ContactListViewModel @Inject constructor(

    private val fetchContactsListUseCase: FetchContactsList

) : ViewModel() {

    private val _fetchContactsState = mutableStateOf(FetchContactsState())
    val fetchContactsState: State<FetchContactsState> = _fetchContactsState
    fun fetchContactsList(


    ) {
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


}