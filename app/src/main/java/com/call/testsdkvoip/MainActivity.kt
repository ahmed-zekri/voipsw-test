package com.call.testsdkvoip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.call.testsdkvoip.presentation.navigation.Navigation
import com.call.testsdkvoip.presentation.theme.TestSdkVoipTheme
import com.streamwide.smartms.lib.core.api.account.STWAccountError
import com.streamwide.smartms.lib.core.api.account.STWAccountManager
import com.streamwide.smartms.lib.core.api.account.login.CompletionCallback
import com.streamwide.smartms.lib.core.api.account.login.RegisterInfo
import com.streamwide.smartms.lib.core.api.account.login.RegisterOrganisationCallback
import com.streamwide.smartms.lib.core.api.account.login.RegistrationCallback
import com.streamwide.smartms.lib.core.api_ktx.contact.STWContactApi
import dagger.hilt.android.AndroidEntryPoint


import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }
}
