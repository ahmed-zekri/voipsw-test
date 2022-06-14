package com.call.testsdkvoip.common.extensions

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Pair
import androidx.core.app.NotificationCompat
import com.call.testsdkvoip.BuildConfig
import com.call.testsdkvoip.MainActivity
import com.call.testsdkvoip.MyApp
import com.call.testsdkvoip.common.constants.LoggerConstant
import com.streamwide.smartms.lib.core.api.account.STWAccountManager
import com.streamwide.smartms.lib.core.api.environment.logger.STWLoggerHelper


fun Context.isRunningService(clazz: Class<out Service?>): Boolean {
    val activityManager = this.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val classFullName = clazz.name
    val runningServiceInfoList = activityManager.getRunningServices(Int.MAX_VALUE)
    for (info in runningServiceInfoList) {
        val componentName = info.service
        if (componentName.className == classFullName && componentName.packageName == BuildConfig.APPLICATION_ID) {
            return true
        }
    }
    return false
}

fun Context.startForegroundOrNormalService(serviceIntent: Intent) {
    STWLoggerHelper.LOGGER.d(
        Pair.create(
            this.packageName,
            "startForegroundOrNormalService"
        ), LoggerConstant.APPLICATION,
        "startForegroundOrNormalService"
    )

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        //add try catch
        try {
            this.startForegroundService(serviceIntent)
        } catch (foregroundServiceStartNotAllowedException: ForegroundServiceStartNotAllowedException) {
            //show a notification to allow ignore optimization
            STWLoggerHelper.LOGGER.w(
                Pair.create(
                    this.packageName,
                    "startForegroundOrNormalService"
                ), LoggerConstant.APPLICATION,
                "foregroundServiceStartNotAllowedException"
            )
       this.showRequestPermissionNotification()
        }
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        this.startForegroundService(serviceIntent)
    } else {
        STWLoggerHelper.LOGGER.w(
            Pair.create(
                this.packageName,
                "startForegroundOrNormalService"
            ), LoggerConstant.APPLICATION,
            "startService"
        )
        this.startService(serviceIntent)
    }
}

fun Context.createNotification(

): Notification? {

    val isUserAuthenticated = STWAccountManager.getInstance().isUserAuthenticated(this)
    if (!isUserAuthenticated) {
        STWLoggerHelper.LOGGER.w(
            Pair.create(MyApp.getInstance().packageName, "createNotification"),
            LoggerConstant.NOTIFICATION,
            "Application is not active. Can't show notification!"
        )
        return null
    }
    val intent = Intent(this, MainActivity::class.java)


    val pendingIntent = PendingIntent.getActivity(
        this,
        0x1,
        intent,

        PendingIntent.FLAG_CANCEL_CURRENT or PendingIntent.FLAG_IMMUTABLE

    )


    val configIntent: PendingIntent? = pendingIntent
    val notificationBuilder: NotificationCompat.Builder?

    notificationBuilder =
        NotificationCompat.Builder(this, "")


            .setWhen(System.currentTimeMillis())
            .setContentTitle("Title")
            .setContentText("Description")

            .setStyle(NotificationCompat.BigTextStyle())
            .setContentIntent(configIntent)
            .setAutoCancel(true)


    notificationBuilder.setShowWhen(false)


    return notificationBuilder.build()
}

fun Context.showRequestPermissionNotification() {


    val notificationManager =
        this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


    val notification = createNotification()


    notification?.let {
        val notificationTag = "REQUEST_PERMISSION"
        val notificationId = 28


        STWLoggerHelper.LOGGER.d(
            Pair.create(this.packageName, "showNewMessageNotification"),
            LoggerConstant.NOTIFICATION,
            "Show notification (tag=$notificationTag, id=$notificationId)"
        )


        notificationManager.notify(
            notificationTag,
            notificationId,
            it
        )


    }

}


