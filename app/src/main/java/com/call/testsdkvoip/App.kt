package com.call.testsdkvoip


import android.app.Application
import android.util.Log
import com.call.testsdkvoip.common.MainManager.Companion.incomingSessionsListener
import com.call.testsdkvoip.common.MainManager.Companion.sessionStateListener
import com.streamwide.smartms.lib.core.api.STWApplicationStateListener
import com.streamwide.smartms.lib.core.api.SmartMsSDK
import com.streamwide.smartms.lib.core.api.call.STWCallManager
import com.streamwide.smartms.lib.core.api.environment.certif.ITrustStore
import com.streamwide.smartms.lib.core.api.environment.configuration.STWConfiguration
import com.streamwide.smartms.lib.core.api.environment.logger.LogLevel
import com.streamwide.smartms.lib.core.api.environment.logger.STWLoggerHelper
import dagger.hilt.android.HiltAndroidApp
import java.io.InputStream


@HiltAndroidApp
class MyApp : Application(), STWApplicationStateListener {

    companion object {
        private lateinit var mInstance: MyApp
        fun getInstance(): MyApp {
            return mInstance
        }

    }


    override fun onCreate() {
        mInstance = this
        init()
        super.onCreate()
        //init SmartMsSdk


    }


    private fun init() {
        configureSdk()
        SmartMsSDK.getInstance().initializeApp(this, this, null)
        //define the application log level
        //define the application log level
        val applicationLogLevel: LogLevel = LogLevel.DEBUG
        STWLoggerHelper.initApplicationLogLevel(this, applicationLogLevel)
        STWLoggerHelper.debuggableMode(this, true)
        // MainManager.instance.registerOnSDKCallbacks()
        STWCallManager.getInstance().registerForSessionEvents(
            incomingSessionsListener, sessionStateListener,
            null, null, null
        )

    }


    private fun configureSdk() {
        STWConfiguration.Builder(this).apply {
            setDefaultConfigurationServerUrl(resources.getString(R.string.default_fqdn))
            setHostNameUrl(resources.getString(R.string.url_hostname))
            //App Crashed without this params
            enableUserPasswordEncryption(true)
            enableAlwaysConnectedMode(true)
            //App Crashed without this params
            enableTlsForSip(true)
            //  enableTlsForOSM(false)
            setKeystoreAlias(resources.getString(R.string.key_store_alias))
            setTrustStore(CustomTrustStore())


        }.build()
    }

    override fun onApplicationStarted() {
        Log.d("AppState", "Started")
    }

    override fun onApplicationStopped() {
        Log.d("AppState", "Stopped")

    }


    internal class CustomTrustStore : ITrustStore {
        /**
         * @return keystore type (like [#getDefaultType()][java.security.KeyStore])
         */
        override fun getKeyStoreType(): String {
            return "BKS"
        }

        /**
         * @return an [InputStream] for the bks/jks file
         */
        override fun getKeyStoreInputStream(): InputStream {
            return getInstance().resources.openRawResource(R.raw.smartms_client_truststore)
            //return null
        }

        /**
         * @return the trust password
         */


        override fun getKeyStorePassword(): String {
            /*try {
                 return DecodeUtil.decode(ApplicationParams.application.get(ApplicationParams.SP))
             } catch (ignored: UnsupportedEncodingException) {
             }*/
            return ""
        }
    }

}