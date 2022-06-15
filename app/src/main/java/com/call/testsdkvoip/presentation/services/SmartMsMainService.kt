/*
 *
 * 	StreamWIDE (Team on The Run)
 *
 * @createdBy  AndroidTeam on lun., 12 oct. 2020 11:42:30 +0100
 * @copyright  Copyright (c) 2020 StreamWIDE UK Ltd (Team on the Run)
 * @email      support@teamontherun.com
 *
 * 	© Copyright 2020 StreamWIDE UK Ltd (Team on the Run). StreamWIDE is the copyright holder
 * 	of all code contained in this file. Do not redistribute or
 *  	re-use without permission.
 *
 * @lastModifiedOn lun., 12 oct. 2020 11:40:03 +0100
 */
package com.call.testsdkvoip.presentation.services


import android.app.*
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.IBinder
import android.util.Pair
import androidx.core.app.NotificationCompat
import com.call.testsdkvoip.MainActivity
import com.call.testsdkvoip.MyApp
import com.call.testsdkvoip.R
import com.call.testsdkvoip.common.constants.LoggerConstant
import com.call.testsdkvoip.common.extensions.isEmpty
import com.streamwide.smartms.lib.core.api.account.ConnectionState
import com.streamwide.smartms.lib.core.api.account.STWAccountManager
import com.streamwide.smartms.lib.core.api.call.STWCallManager
import com.streamwide.smartms.lib.core.api.environment.logger.STWLoggerHelper
import com.streamwide.smartms.lib.core.api.environment.security.DeviceSecurityStatus
import com.streamwide.smartms.lib.core.api.environment.security.STWSecurityManager
import com.streamwide.smartms.lib.core.network.voip.STWVCall

/**
 * Created by streamwide on 8/3/18.
 */
class SmartMsMainService : Service() {
    override fun onCreate() {
        /*
         * Update notification is mandatory especially when start this service
         * in foreground and call stopSelf immediately
         */
        createOrUpdateServiceNotification()
    }

    /**
     * Create or update notification for foreground service
     */
    private fun createOrUpdateServiceNotification() {
        STWLoggerHelper.LOGGER.i(
            Pair.create(CLASS_NAME, "createOrUpdateServiceNotification"), LoggerConstant.SERVICE,
            "create or update ForegroundService notification with mCurrentTasks = $mCurrentTasks"
        )
        val showTaskIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, showTaskIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val mainServiceNotificationConfig: MainServiceNotificationConfig =
            MainServiceNotificationConfig
                .Builder()
                .channelId("MAIN_SERVICE_CHANNEL_ID")
                .channelName("Global service")
                .connectedContentText("Connected")
                .connectingContentText("Connecting")
                .contentTitle(
                    if (mCurrentTasks == 33 && currentCall != null) "Call received from ${
                        currentCall?.callerPhoneItem?.internationalNumber
                    }" else "Service running"
                )

                .pendingIntent(pendingIntent)
                .smallIconResId(R.drawable.ic_launcher_foreground)
                .build()
        var state: String? = null
        val pi: PendingIntent? = mainServiceNotificationConfig.pendingIntent
        val smallIcon: Int = mainServiceNotificationConfig.smallIconResId
        val channelId: String? = mainServiceNotificationConfig.channelId
        val groupId: String? = mainServiceNotificationConfig.groupId
        val channelName: String? = mainServiceNotificationConfig.channelName
        val channelDescription: String? = mainServiceNotificationConfig.channelDescription
        val contentTitle: String? = mainServiceNotificationConfig.contentTitle
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        var notificationChannel = notificationManager.getNotificationChannel(channelId)
        var notificationChannelCall =
            notificationManager.getNotificationChannel("VOIP_CALLS_NOTIFICATION_CHANNEL")
        if (notificationChannelCall == null) {
            notificationChannelCall = NotificationChannel(
                "VOIP_CALLS_NOTIFICATION_CHANNEL",
                "Calls notifications",
                NotificationManager.IMPORTANCE_HIGH
            )

            notificationChannelCall.setSound(
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM),
                Notification.AUDIO_ATTRIBUTES_DEFAULT
            )
            notificationChannelCall.enableVibration(true);
            notificationChannelCall.vibrationPattern = longArrayOf(5000, 1000, 500, 1000)





            notificationManager.createNotificationChannel(notificationChannelCall)

        }
        if (notificationChannel == null) {
            notificationChannel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_LOW
            )
            notificationChannel.setShowBadge(false)
            notificationChannel.group = groupId

            notificationChannel.description = channelDescription

            notificationManager.createNotificationChannel(notificationChannel)
        }
        if (mCurrentTasks == TASK_MAIN || mCurrentTasks >= TASK_VOIP) {
            /*
             * service running only for :
             * - always connected mode
             * - and/or VoIP Always connected mode
             * (no geolocation, and no my business start/stop geolocation)
             */
            val currentConnectionStatus: String =
                STWAccountManager.getInstance().accountConnectionState
            val deviceSecurityStatus = STWSecurityManager.getInstance().checkSecurityStatus(this)
            val isUserAuthenticated =
                STWAccountManager.getInstance().isUserAuthenticated(this)
            val isAppEnabled =
                DeviceSecurityStatus.DEVICE_SECURED == deviceSecurityStatus && isUserAuthenticated
            val isAlwaysConnectedModeFlavor = false
            val remoteAlwaysConnectedMode =
                STWAccountManager.getInstance().isAppAlwaysConnectedModeEnabled(this)

            /*
             * currentConnectionStatus is related to Always Connected Mode (SIP) only
             */state =
                if (isAppEnabled && (isAlwaysConnectedModeFlavor || remoteAlwaysConnectedMode)) {
                    when (currentConnectionStatus) {
                        ConnectionState.STATE_CONNECTING -> mainServiceNotificationConfig.connectingContentText
                        ConnectionState.STATE_CONNECTED -> mainServiceNotificationConfig.connectedContentText
                        else -> ""
                    }
                } else {
                    ""
                }
        }
        STWLoggerHelper.LOGGER.d(
            Pair.create(CLASS_NAME, "createOrUpdateServiceNotification"), LoggerConstant.SERVICE,
            "state = $state, contentTitle = $contentTitle"
        )
        if (channelId == null) {
            return
        }
        //@formatter:off
        val builder = NotificationCompat.Builder(
            this,
            if (currentCall != null && mCurrentTasks == 33) "VOIP_CALLS_NOTIFICATION_CHANNEL" else channelId
        )
            .setSmallIcon(smallIcon)
            .setContentTitle(contentTitle)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setContentIntent(pi)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setWhen(System.currentTimeMillis())
            .setShowWhen(false)


        if (mCurrentTasks == 33 && currentCall != null) {
            val acceptIntent = Intent(this, MainActivity::class.java)
            acceptIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            acceptIntent.putExtra("action", "accept")

            val rejectIntent = Intent(this, MainActivity::class.java)
            rejectIntent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            rejectIntent.putExtra("action", "reject")


            val acceptPendingIntent = PendingIntent.getActivity(
                this,
                0, acceptIntent,
                PendingIntent.FLAG_CANCEL_CURRENT
            )


            val rejectPendingIntent = PendingIntent.getActivity(
                this,
                1, rejectIntent,
                PendingIntent.FLAG_CANCEL_CURRENT
            )

            val acceptAction = NotificationCompat.Action.Builder(
                0, "accept", acceptPendingIntent
            ).build()

            val rejectAction = NotificationCompat.Action.Builder(
                0, "reject", rejectPendingIntent
            ).build()
            builder.addAction(acceptAction)
            builder.addAction(rejectAction)

        }
        //@formatter:on


        val notification = builder.build()


        startForeground(
            0x1,
            notification
        )

    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        STWLoggerHelper.LOGGER.d(
            Pair.create(CLASS_NAME, "onStartCommand"), LoggerConstant.SERVICE,
            " SmartMsMainService onStartCommand"
        )
        if (intent == null) {
            STWLoggerHelper.LOGGER.w(
                Pair.create(CLASS_NAME, "onStartCommand"), LoggerConstant.SERVICE,
                " intent is null abort Service"
            )
            stopSelf()
            return START_STICKY
        }
        val action = intent.action
        if (action!!.isEmpty()) {
            STWLoggerHelper.LOGGER.w(
                Pair.create(CLASS_NAME, "onStartCommand"), LoggerConstant.SERVICE,
                " action is null abort Service"
            )
            stopSelf()
            return START_STICKY
        }
        STWLoggerHelper.LOGGER.d(
            Pair.create(CLASS_NAME, "onStartCommand"), LoggerConstant.SERVICE,
            "action = $action, mCurrentTasks = $mCurrentTasks"
        )
        val extras = intent.extras
        if (ACTION_START == action) {
            if (extras != null) {
                val extraTask = extras.getInt(EXTRA_TASK, DEFAULT_NO_TASK)
                STWLoggerHelper.LOGGER.d(
                    Pair.create(CLASS_NAME, "onStartCommand"), LoggerConstant.SERVICE,
                    "extraTask = $extraTask"
                )
                mCurrentTasks = mCurrentTasks or extraTask
                createOrUpdateServiceNotification()
            }
        } else if (ACTION_STOP == action) {
            if (mCurrentTasks == DEFAULT_NO_TASK) {
                STWLoggerHelper.LOGGER.w(
                    Pair.create(CLASS_NAME, "onStartCommand"), LoggerConstant.SERVICE,
                    " No component running, abort Service"
                )
                stopSelf()
                return START_STICKY
            }
            if (extras != null) {
                val extraTask = extras.getInt(EXTRA_TASK, DEFAULT_NO_TASK)
                STWLoggerHelper.LOGGER.d(
                    Pair.create(CLASS_NAME, "onStartCommand"), LoggerConstant.SERVICE,
                    "extraTask = $extraTask"
                )
                if (extraTask == TASK_GEOLOC) {
                    if (mCurrentTasks == TASK_GEOLOC) {
                        stopSelf()
                    } else {
                        mCurrentTasks = mCurrentTasks and extraTask.inv()
                        createOrUpdateServiceNotification()
                    }
                } else if (extraTask == TASK_GEOLOC_BEACON) {
                    if (mCurrentTasks == TASK_GEOLOC_BEACON) {
                        stopSelf()
                    } else {
                        mCurrentTasks = mCurrentTasks and extraTask.inv()
                        createOrUpdateServiceNotification()
                    }
                } else if (extraTask == TASK_MAIN) {
                    if (mCurrentTasks == TASK_MAIN) {
                        stopSelf()
                    } else {
                        mCurrentTasks = mCurrentTasks and extraTask.inv()
                        createOrUpdateServiceNotification()
                    }
                } else if (extraTask == TASK_MB_GEOLOC) {
                    if (mCurrentTasks == TASK_MB_GEOLOC) {
                        stopSelf()
                    } else {
                        mCurrentTasks = mCurrentTasks and extraTask.inv()
                        createOrUpdateServiceNotification()
                    }
                } else if (extraTask == TASK_VOIP) {
                    if (mCurrentTasks == TASK_VOIP) {
                        stopSelf()
                        mCurrentTasks = DEFAULT_NO_TASK
                    } else if (mCurrentTasks >= TASK_VOIP) {
                        mCurrentTasks = mCurrentTasks and extraTask.inv()
                        if (mCurrentTasks < TASK_VOIP) {
                            stopForeground(true)
                        }
                        createOrUpdateServiceNotification()
                    } else {
                        mCurrentTasks = mCurrentTasks and extraTask.inv()
                        createOrUpdateServiceNotification()
                    }
                } else if (extraTask == TASK_VOIP_ACM) {
                    if (mCurrentTasks == TASK_VOIP_ACM) {
                        stopSelf()
                        mCurrentTasks = DEFAULT_NO_TASK
                    } else if (mCurrentTasks >= TASK_VOIP_ACM) {
                        mCurrentTasks = mCurrentTasks and extraTask.inv()
                        if (mCurrentTasks < TASK_VOIP) {
                            stopForeground(true)
                        }
                        createOrUpdateServiceNotification()
                    } else {
                        mCurrentTasks = mCurrentTasks and extraTask.inv()
                        createOrUpdateServiceNotification()
                    }
                } else if (extraTask == TASK_MIGRATION) {
                    if (mCurrentTasks == TASK_MIGRATION) {
                        stopSelf()
                    } else {
                        mCurrentTasks = mCurrentTasks and extraTask.inv()
                        createOrUpdateServiceNotification()
                    }
                } else if (extraTask == ALL_TASK) {
                    stopSelf()
                    mCurrentTasks = DEFAULT_NO_TASK
                } else if (extraTask == TASK_HYTERA) {
                    if (mCurrentTasks == TASK_HYTERA) {
                        stopSelf()
                    } else {
                        mCurrentTasks = mCurrentTasks and extraTask.inv()
                        createOrUpdateServiceNotification()
                    }
                }
            }
        } else if (ACTION_UPDATE == action) {
            createOrUpdateServiceNotification()
        }
        return START_STICKY
    }


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        mCurrentTasks = DEFAULT_NO_TASK
        STWLoggerHelper.LOGGER.i(
            Pair.create(CLASS_NAME, "onDestroy"), LoggerConstant.SERVICE,
            "SmartMsMainService is destroyed"
        )

        super.onDestroy()
    }


    /**
     * Call this method to delete Notification Channel
     */

    // @formatter:off
    // @formatter:off

    companion object {
        private const val CLASS_NAME = "SmartMsMainService"
        private val SERVICE_NAME: String =
            MyApp.getInstance().packageName.toString() + ".SmartMsMainService."

        // action to start the service with a specific task
        val ACTION_START = SERVICE_NAME + "start"

        // Action to stop service with a specific task
        val ACTION_STOP = SERVICE_NAME + "stop"

        var currentCall: STWVCall? = null

        // Action to update service with a specific task
        val ACTION_UPDATE = SERVICE_NAME + "update"

        // key for extra : Task to be started, stopped or updated
        const val EXTRA_TASK = "extra_component"
        const val DEFAULT_NO_TASK = 0x00000000
        const val TASK_MAIN = 0x00000001 // always connected mode
        const val TASK_GEOLOC = 0x00000002 // geoloc
        const val TASK_GEOLOC_BEACON = 0x00000004 // geoloc beacon
        const val TASK_MB_GEOLOC = 0x00000008 // mybusiness start/stop geoloc
        const val TASK_MIGRATION = 0x00000010 // migrations
        const val TASK_VOIP = 0x00000020 // voip
        const val TASK_VOIP_ACM = 0x00000040 // voip ACM
        const val TASK_HYTERA = 0x00000080 // hytera service

        // public static final int TASK_NEXT_VALUE = 0x00000080;
        // public static final int TASK_NEXT_VALUE = 0x00000100;
        // public static final int TASK_NEXT_VALUE = 0x00000200;
        const val ALL_TASK =
            TASK_MAIN or TASK_GEOLOC or TASK_GEOLOC_BEACON or TASK_MB_GEOLOC or TASK_VOIP or TASK_VOIP_ACM or TASK_MIGRATION or TASK_HYTERA

        private var mCurrentTasks = DEFAULT_NO_TASK
    }
}