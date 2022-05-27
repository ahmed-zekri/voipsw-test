package com.call.testsdkvoip.presentation.intro

import androidx.lifecycle.ViewModel
import com.streamwide.smartms.lib.core.api.account.STWAccountManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class IntroViewModel @Inject constructor(val stwAccountManager: STWAccountManager) :
    ViewModel()