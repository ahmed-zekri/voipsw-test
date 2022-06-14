/*
 *
 * 	StreamWIDE (Team on The Run)
 *
 * @createdBy  AndroidTeam on Wed, 19 Aug 2020 17:31:54 +0200
 * @copyright  Copyright (c) 2020 StreamWIDE UK Ltd (Team on the Run)
 * @email      support@teamontherun.com
 *
 * 	© Copyright 2020 StreamWIDE UK Ltd (Team on the Run). StreamWIDE is the copyright holder
 * 	of all code contained in this file. Do not redistribute or
 *  	re-use without permission.
 *
 * @lastModifiedOn Wed, 19 Aug 2020 15:36:15 +0200
 */
package com.call.testsdkvoip.presentation.services

import android.app.*
import android.content.Context
import android.content.Intent
import android.util.Pair
import androidx.core.app.NotificationCompat
import com.call.testsdkvoip.MyApp
import com.call.testsdkvoip.R
import com.call.testsdkvoip.common.constants.LoggerConstant
import com.call.testsdkvoip.common.extensions.isRunningService
import com.call.testsdkvoip.common.extensions.startForegroundOrNormalService
import com.streamwide.smartms.lib.core.api.STWServiceListener
import com.streamwide.smartms.lib.core.api.account.STWAccountManager
import com.streamwide.smartms.lib.core.api.call.STWCallManager
import com.streamwide.smartms.lib.core.api.environment.logger.STWLoggerHelper


class MainServiceManager private constructor() {
    private val mMainServiceListener: STWServiceListener = object : STWServiceListener {
        override fun startService(context: Context) {
            val serviceIntent = Intent(
                context,
                SmartMsMainService::class.java
            )
            serviceIntent.action = SmartMsMainService.ACTION_START
            serviceIntent.putExtra(SmartMsMainService.EXTRA_TASK, SmartMsMainService.TASK_MAIN)
            context.startForegroundOrNormalService(serviceIntent)
        }

        override fun getNotification(context: Context): Pair<Notification, Int>? {
            return null
        }

        override fun stopService(context: Context) {
            instance.stopServices(SmartMsMainService.TASK_MAIN)
        }
    }

    private val mVoipServiceListener: STWServiceListener = object : STWServiceListener {
        override fun startService(context: Context) {
            STWLoggerHelper.LOGGER.i(
                Pair.create(CLASS_NAME, "dispatchStartVoipService"), LoggerConstant.APPLICATION,
                "start SmartMsMainService with ACTION_START for START_VOIP"
            )
            val serviceIntent = Intent(
                context,
                SmartMsMainService::class.java
            )
            serviceIntent.action = SmartMsMainService.ACTION_START
            serviceIntent.putExtra(SmartMsMainService.EXTRA_TASK, SmartMsMainService.TASK_VOIP)
            context.startForegroundOrNormalService(serviceIntent)
        }

        override fun getNotification(context: Context): Pair<Notification, Int>? {
            return null
        }

        override fun stopService(context: Context) {
            STWLoggerHelper.LOGGER.i(
                Pair.create(CLASS_NAME, "stopService"),
                LoggerConstant.APPLICATION,
                "STOP_VOIP"
            )
            stopServices(SmartMsMainService.TASK_VOIP)
        }
    }


    private val mVoipACMServiceListener: STWServiceListener = object : STWServiceListener {
        override fun startService(context: Context) {
            STWLoggerHelper.LOGGER.i(
                Pair.create(CLASS_NAME, "dispatchStartVoipACMService"), LoggerConstant.APPLICATION,
                "start SmartMsMainService with ACTION_START for START_VOIP_ACM"
            )
            val serviceIntent = Intent(
                context,
                SmartMsMainService::class.java
            )
            serviceIntent.action = SmartMsMainService.ACTION_START
            serviceIntent.putExtra(SmartMsMainService.EXTRA_TASK, SmartMsMainService.TASK_VOIP_ACM)
            context.startForegroundOrNormalService(serviceIntent)
        }

        override fun getNotification(context: Context): Pair<Notification, Int>? {
            return null
        }

        override fun stopService(context: Context) {
            STWLoggerHelper.LOGGER.i(
                Pair.create(CLASS_NAME, "stopService"),
                LoggerConstant.APPLICATION,
                "STOP_VOIP_ACM"
            )
            stopServices(SmartMsMainService.TASK_VOIP_ACM)
        }
    }

    fun getForegroundServiceNotification(
        context: Context,
        contentText: String
    ): Pair<Notification, Int> {
        val channelId = "MAIN_SERVICE_CHANNEL_ID"
        val channelName = "Main notifications"
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        var notificationChannel = notificationManager.getNotificationChannel(channelId)
        if (notificationChannel == null) {
            notificationChannel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_LOW
            )
            notificationChannel.setShowBadge(false)
            notificationManager.createNotificationChannel(notificationChannel)
        }
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(MyApp.getInstance(), channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(
                    "Running"
                )
                .setContentText(contentText)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setPriority(NotificationCompat.PRIORITY_LOW)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(false)
                .setOngoing(true)
                .setShowWhen(false)

                .setGroupSummary(false)
        val notification = builder.build()

        notification.flags =
            notification.flags or (Notification.FLAG_ONLY_ALERT_ONCE or Notification.FLAG_FOREGROUND_SERVICE)
        return Pair.create(
            notification,
            0x1
        )
    }

    fun registerSDKServiceListener() {

        STWCallManager.getInstance().registerVoIPServiceListener(mVoipServiceListener)
        STWCallManager.getInstance().registerVoIPACMServiceListener(mVoipACMServiceListener)
    }

    fun unregisterSDKServiceListener() {

        STWCallManager.getInstance().unregisterVoIPServiceListener(mVoipServiceListener)
        STWCallManager.getInstance().unregisterVoIPACMServiceListener(mVoipACMServiceListener)
    }

    val mainServiceListener: STWServiceListener
        get() = mMainServiceListener


    val voipServiceListener: STWServiceListener
        get() = mVoipServiceListener


    fun stopServices(extraTaskValues: Int) {
        if (!MyApp.getInstance().isRunningService(

                SmartMsMainService::class.java
            )
        ) {
            STWLoggerHelper.LOGGER.i(
                Pair.create(CLASS_NAME, "stopServices"), LoggerConstant.SERVICE,
                "SmartMsMainService is not running : no need to stop it ! "
            )
            return
        }
        STWLoggerHelper.LOGGER.i(
            Pair.create(CLASS_NAME, "stopServices"), LoggerConstant.SERVICE,
            "SmartMsMainService is running : stop it with extraTaskValues = $extraTaskValues"
        )
        val serviceIntent = Intent(
            MyApp.getInstance(),
            SmartMsMainService::class.java
        )
        serviceIntent.action = SmartMsMainService.ACTION_STOP
        serviceIntent.putExtra(SmartMsMainService.EXTRA_TASK, extraTaskValues)
        MyApp.getInstance().startForegroundOrNormalService(serviceIntent)
    }

    fun updateMainServiceNotification() {
        if (!isRunningService(
                MyApp.getInstance(),
                SmartMsMainService::class.java
            )
        ) {
            STWLoggerHelper.LOGGER.i(
                Pair.create(CLASS_NAME, "updateMainServiceNotification"), LoggerConstant.SERVICE,
                "SmartMsMainService is not running : no need to update it!"
            )
            return
        }
        val serviceIntent = Intent(
            MyApp.getInstance(),
            SmartMsMainService::class.java
        )
        serviceIntent.action = SmartMsMainService.ACTION_UPDATE
        serviceIntent.putExtra(SmartMsMainService.EXTRA_TASK, SmartMsMainService.TASK_MAIN)
        MyApp.getInstance().startForegroundOrNormalService(serviceIntent)
    }

    fun startNeededServiceNotification(context: Context) {
        var tasks: Int = SmartMsMainService.DEFAULT_NO_TASK

        val remoteAlwaysConnectedMode =
            STWAccountManager.getInstance().isAppAlwaysConnectedModeEnabled(context)


        val isVoIPACM = STWCallManager.getInstance()
            .isVoipAlwaysConnectedAllowed(context) && STWCallManager.getInstance()
            .isVoipAlwaysConnectedModeEnabled(context)
        if (isVoIPACM) {
            STWLoggerHelper.LOGGER.i(
                Pair.create(CLASS_NAME, "startNeededServiceNotification"),
                LoggerConstant.SERVICE, "isVoIPACM"
            )
            tasks = tasks or SmartMsMainService.TASK_VOIP_ACM
        }
        STWLoggerHelper.LOGGER.i(
            Pair.create(CLASS_NAME, "startNeededServiceNotification"), LoggerConstant.SERVICE,
            "SmartMsMainService start tasks = $tasks"
        )
        val serviceIntent = Intent(
            MyApp.getInstance(),
            SmartMsMainService::class.java
        )
        serviceIntent.action = SmartMsMainService.ACTION_START
        serviceIntent.putExtra(SmartMsMainService.EXTRA_TASK, tasks)
        context.startForegroundOrNormalService(serviceIntent)
    }

    companion object {
        private const val CLASS_NAME = "MainServiceManager"

        /**
         * Singleton instance
         */
        private val instance = MainServiceManager()

        fun getInstance(): MainServiceManager {
            return instance
        }
    }

    private fun isRunningService(context: Context, clazz: Class<out Service?>): Boolean {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val classFullName = clazz.name
        val runningServiceInfoList = activityManager.getRunningServices(Int.MAX_VALUE)
        for (info in runningServiceInfoList) {
            val componentName = info.service
            if (componentName.className == classFullName && componentName.packageName == MyApp.getInstance()
                    .packageName
            ) {
                return true
            }
        }
        return false
    }
}