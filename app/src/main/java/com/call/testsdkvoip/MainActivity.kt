package com.call.testsdkvoip


import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.call.testsdkvoip.presentation.navigation.Navigation
import com.call.testsdkvoip.presentation.services.SmartMsMainService
import com.streamwide.smartms.lib.core.api.call.STWCallManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkForCall()
        setContent {
            Navigation()
        }

    }


    override fun onStart() {
        super.onStart()
        checkForCall()
    }

    override fun onResume() {
        super.onResume()
        checkForCall()
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        checkForCall()
    }

    private fun checkForCall() {


        when (intent?.getStringExtra("action")) {
            "accept" -> STWCallManager.getInstance()
                .acceptCall(this, SmartMsMainService.currentCall, null)
            "reject" -> {
                STWCallManager.getInstance().stopCall(this, SmartMsMainService.currentCall)
                finish()
            }
        }

        intent.removeExtra("action")
    }


}
