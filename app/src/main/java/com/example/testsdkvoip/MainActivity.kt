package com.example.testsdkvoip

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.testsdkvoip.ui.theme.TestSdkVoipTheme
import com.streamwide.smartms.lib.core.api.account.STWAccountError
import com.streamwide.smartms.lib.core.api.account.STWAccountManager
import com.streamwide.smartms.lib.core.api.account.login.CompletionCallback
import com.streamwide.smartms.lib.core.api.account.login.RegisterInfo
import com.streamwide.smartms.lib.core.api.account.login.RegisterOrganisationCallback
import com.streamwide.smartms.lib.core.api.account.login.RegistrationCallback
import com.streamwide.smartms.lib.core.api_ktx.contact.STWContactApi

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!STWAccountManager.getInstance().isUserAuthenticated(this))
            STWAccountManager.getInstance().register(this, "21625123459", "733112", object :
                RegisterOrganisationCallback {
                override fun onError(p0: STWAccountError) {

                }

                override fun onSuccess(p0: Int, p1: RegisterInfo) {
                    STWAccountManager.getInstance()
                        .login(this@MainActivity,
                            RegistrationCallback.LoginType.ACTIVATION_CODE_PROVIDED_BY_ADMINISTRATOR,
                            "9",
                            object : CompletionCallback {
                                override fun onError(p0: STWAccountError) {

                                }

                                override fun onSuccess() {

                                }


                                override fun onSynchronizationStarted() {

                                }

                                override fun onSynchronizationFinished() {

                                }
                            })
                }

                override fun onServiceConfigurationSuccess() {

                }

                override fun onServiceOrganizationSuccess() {

                }
            })

        GlobalScope.launch {
            val contacts = STWContactApi.getAllContacts(
                context = this@MainActivity


            )
            contacts?.size

        }


    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TestSdkVoipTheme {
        Greeting("Android")
    }
}