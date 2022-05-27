/*
 *
 * 	StreamWIDE (Team on The Run)
 *
 * @createdBy  AndroidTeam on Wed, 18 May 2022 10:09:58 +0100
 * @copyright  Copyright (c) 2022 StreamWIDE UK Ltd (Team on the Run)
 * @email      support@teamontherun.com
 *
 * 	© Copyright 2022 StreamWIDE UK Ltd (Team on the Run). StreamWIDE is the copyright holder
 * 	of all code contained in this file. Do not redistribute or
 *  	re-use without permission.
 *
 * @lastModifiedOn Tue, 17 May 2022 18:27:20 +0100
 */
package com.call.testsdkvoip.common

class MainManager private constructor() {
/*
    @ConnectionState
    private val mCurrentConnectionState = ConnectionState.STATE_IDLE
    fun getCurrentConnectionState(): String {
        return mCurrentConnectionState
    }

    fun registerOnSDKCallbacks() {
        currentConnectionState = STWAccountManager.getInstance().accountConnectionState
        STWCallManager.getInstance().registerForSessionEvents(
            mIncomingSessionsEvents, mSessionsStateEvents,
            mSessionParticipantEvents, mPocFloorControlEvents, null
        )

    }

    fun startImmobilityDetectionEnable() {
        val isImmobilityActive: Boolean =
            MainPrefsHelper.getInstance(MainApplication.getContext()).getBoolean(
                MainPreference.IMMOBILITY_DETECTION_FEATURE_ENABLED,
                MainPreference.DefaultPrefs.DEFAULT_IMMOBILITY_DETECTION_FEATURE_ENABLED
            )
        val isEmergencyCallAllowed =
            STWEmergencyManager.getInstance().isEmergencyCallAllowed(MainApplication.getContext())
        if (isImmobilityActive && isEmergencyCallAllowed) {
            ImmobilityDetectionHelper.Companion.startImmobilityDetection(MainApplication.getContext())
        }
    }

    private val mIncomingSessionsEvents: IncomingSessionsListener =
        object : IncomingSessionsListener {
            override fun onReceiveIncomingCall(call: STWVCall) {
                MainWakeLockManager.getInstance(MainApplication.getContext())
                    .acquireWakeLock(MainWakeLockManager.MainRegisteredComponent.VoIPSessionInvitation)
                VoipCallUtil.handleIncomingCall(MainApplication.getContext(), call)

                *//**
                 * Notify USB device call is ringing
                 *//*
                sendDataAction(OutData.RINGING)
            }

            override fun onReceiveMissedCall(voipSessionItem: VoipSessionItem) {

            }

            override fun onReceiveIncomingMergedCall(call: STWVCall) {

            }
        }



        @get:Synchronized
        val instance: MainManager
            get() {
                if (mInstance == null) {
                    STWLoggerHelper.LOGGER.i(
                        Pair.create(CLASS_NAME, "getInstance"), LoggerConstant.APPLICATION,
                        "initialize MainManager singleton"
                    )
                    mInstance = MainManager()
                }
                return mInstance!!
            }
    }*/
}