package com.example.testsdkvoip

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.testsdkvoip.common.lifecycleOwner
import com.example.testsdkvoip.ui.theme.TestSdkVoipTheme
import com.streamwide.smartms.lib.core.api.account.STWAccountError
import com.streamwide.smartms.lib.core.api.account.STWAccountManager
import com.streamwide.smartms.lib.core.api.account.login.CompletionCallback
import com.streamwide.smartms.lib.core.api.account.login.RegisterInfo
import com.streamwide.smartms.lib.core.api.account.login.RegisterOrganisationCallback
import com.streamwide.smartms.lib.core.api.account.login.RegistrationCallback
import com.streamwide.smartms.lib.core.api.contact.STWContactFilterOption
import com.streamwide.smartms.lib.core.api_ktx.contact.STWContactApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        STWAccountManager.getInstance().register(this, "21625306014", "278768", object :
            RegisterOrganisationCallback {
            override fun onError(p0: STWAccountError) {

            }

            override fun onSuccess(p0: Int, p1: RegisterInfo) {
                STWAccountManager.getInstance()
                    .login(this@MainActivity,
                        RegistrationCallback.LoginType.ACTIVATION_CODE_PROVIDED_BY_ADMINISTRATOR,
                        "131",
                        object : CompletionCallback {
                            override fun onError(p0: STWAccountError) {

                            }

                            override fun onSuccess() {
                                (
                                        STWContactApi.getCompanyContactsAsFlow(
                                            context = this@MainActivity,
                                            filter = STWContactFilterOption.ORGANIZATION.GROUP
                                        ).onEach {
                                            it?.size

                                        }
                                            .launchIn((this@MainActivity.baseContext as Context).lifecycleOwner()!!.lifecycleScope))

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