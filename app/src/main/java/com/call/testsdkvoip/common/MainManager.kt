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

import android.util.Log
import com.call.testsdkvoip.MyApp
import com.call.testsdkvoip.presentation.services.SmartMsMainService
import com.streamwide.smartms.lib.core.api.call.IncomingSessionsListener
import com.streamwide.smartms.lib.core.api.call.SessionStateListener
import com.streamwide.smartms.lib.core.data.item.VoipSessionItem
import com.streamwide.smartms.lib.core.network.voip.STWVCall
import com.streamwide.smartms.lib.hardware.logger.Logger

class MainManager private constructor() {
    companion object {

        lateinit var globalListener: GlobalListener
        val incomingSessionsListener: IncomingSessionsListener =
            object : IncomingSessionsListener {
                override fun onReceiveIncomingCall(call: STWVCall) {
                    MainWakeLockManager.getInstance(MyApp.getInstance())
                        .acquireWakeLock(MainWakeLockManager.MainRegisteredComponent.VoIPSessionInvitation)
                    globalListener.onCallReceived(call)
                    SmartMsMainService.currentCall = call

                }

                override fun onReceiveMissedCall(voipSessionItem: VoipSessionItem) {

                }

                override fun onReceiveIncomingMergedCall(call: STWVCall) {


                }
            }
        val sessionStateListener: SessionStateListener = object : SessionStateListener {
            override fun calling(p0: STWVCall) {
                globalListener.onCallInitiated(p0)

            }

            override fun onSessionStarted(p0: STWVCall) {

            }

            override fun inCall(p0: STWVCall) {
                globalListener.onCallInProgress(p0)


            }

            override fun onSessionConnected(p0: STWVCall) {

            }

            override fun onSessionReconnecting(p0: STWVCall) {
                Log.d("Recived call", "onSessionReconnecting")
            }

            override fun onAllSessionsReconnecting() {
                Log.d("Recived call", "onAllSessionsReconnecting")
            }

            override fun onSessionOnHold(p0: STWVCall) {
                Log.d("Recived call", "onSessionOnHold")
            }

            override fun onSessionResumed(p0: STWVCall) {
                Log.d("Recived call", "onSessionResumed")
            }

            override fun onQosChanged(p0: STWVCall) {
                Log.d("Recived call", "onQosChanged")
            }

            override fun onAudioPlayVolumeChanged(p0: STWVCall) {
                Log.d("Recived call", "onAudioPlayVolumeChanged")
            }

            override fun onAudioCaptureVolumeChanged(p0: STWVCall) {
                Log.d("Recived call", "onAudioCaptureVolumeChanged")
            }

            override fun onSessionStopped(p0: VoipSessionItem) {
                globalListener.onCallStopped(p0)
                Log.d("Recived call", "onSessionStopped")
            }

            override fun onSessionStoppedToMerge(p0: VoipSessionItem) {
                Log.d("Recived call", "onSessionStoppedToMerge")
            }

            override fun onVoipSessionClosed(p0: VoipSessionItem) {

                Log.d("Recived call", "onVoipSessionClosed")
            }

            override fun onSessionAnsweredFromOtherDevice(p0: String) {
                Log.d("Recived call", "onSessionAnsweredFromOtherDevice")
            }

            override fun onAudioOutputChanged() {
                Log.d("Recived call", "onAudioOutputChanged")
            }
        }
    }


}


