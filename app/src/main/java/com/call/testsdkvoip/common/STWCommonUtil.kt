package com.call.testsdkvoip.common

import android.app.Activity
import android.app.ActivityManager
import android.app.KeyguardManager
import android.content.Context
import android.media.Ringtone
import android.media.RingtoneManager
import android.provider.Settings
import android.util.TypedValue
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.call.testsdkvoip.common.constants.LoggerConstant
import com.streamwide.smartms.lib.hardware.logger.Logger

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

import kotlin.math.abs
import kotlin.math.floor

/*
*
* 	StreamWIDE (Team on The Run)
*
* @createdBy  AndroidTeam on Thu, 12 May 2022 16:32:30 +0100
* @copyright  Copyright (c) 2022 StreamWIDE UK Ltd (Team on the Run)
* @email      support@teamontherun.com
*
* 	© Copyright 2022 StreamWIDE UK Ltd (Team on the Run). StreamWIDE is the copyright holder
* 	of all code contained in this file. Do not redistribute or
*  	re-use without permission.
*
* @lastModifiedOn Thu, 12 May 2022 16:26:02 +0100
*/



object STWCommonUtil {
    private const val CLASS_NAME = "STWCommonUtil"

    /** GPS decimal format is always in US based format  */
    private val GPS_DECIMAL_SYMBOLS = DecimalFormatSymbols(Locale.US)

    /**
     * get device id
     *
     * @param context
     * @return
     */
    fun getDeviceId(context: Context): String =
        Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)


    /**
     * Will the decimal degrees into degree provides a format
     *
     * @param numberString
     * @return
     */
    fun convertToSexagesimal(numberString: String): String {
        val mNumber = numberString.toDouble()
        val mDegree = floor(abs(mNumber)).toInt()
        val temp = getdPoint(abs(mNumber)) * 60
        val mPoint = floor(temp).toInt()
        val mSecond = getdPoint(temp) * 60
        return if (mNumber < 0) "-$mDegree/1,$mPoint/1,$mSecond/1" else "$mDegree/1,$mPoint/1,$mSecond/1"
    }

    /**
     * Decimal part
     *
     * @param number
     * @return
     */
    private fun getdPoint(number: Double): Double {
        val fInt = number.toInt()
        val b1 = BigDecimal(number.toString())
        val b2 = BigDecimal(fInt.toString())
        return b1.subtract(b2).toFloat().toDouble()
    }

    /**
     * Convert decimal
     *
     * @param mDegree
     * @param mPoint
     * @param mSecond
     * @return
     */
    fun convertToDecimal(mDegree: Double, mPoint: Double, mSecond: Double): String {
        val mResult: String
        val mDecimalFormat = DecimalFormat("0.000000", GPS_DECIMAL_SYMBOLS)
        mResult = if (mDegree < 0) {
            mDecimalFormat.format(-(abs(mDegree) + (abs(mPoint) + abs(mSecond) / 60) / 60))
        } else {
            mDecimalFormat.format(abs(mDegree) + (abs(mPoint) + abs(mSecond) / 60) / 60)
        }
        return mResult
    }

    /**
     *
     * @param context
     * @return
     */
    fun isApplicationFront(context: Context): Boolean {
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val appProcesses = activityManager.runningAppProcesses
        if (appProcesses == null) {
            Logger.debug(
                LoggerConstant.APPLICATION,
                "isApplicationFront : result = false (because appProcesses == null)"
            )
            return false
        }
        val packageName = context.packageName
        for (appProcess in appProcesses) {
            if (appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND
                && appProcess.processName == packageName
            ) {
                Logger.debug(
                    LoggerConstant.APPLICATION,
                    "isApplicationFront : result = true"
                )
                return true
            }
        }
        Logger.debug(
            LoggerConstant.APPLICATION,
            "isApplicationFront : result = false"
        )
        return false
    }

    /**
     *
     * @param context
     * @return
     */
    fun isScreenLocked(context: Context): Boolean {
        val manager = context.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager
        return manager.inKeyguardRestrictedInputMode()
    }

    /**
     *
     * @param context
     * @return
     */
    fun getSystemRingtones(context: Context): List<Ringtone> {
        // Get system ringtones
        val ringtoneList = ArrayList<Ringtone>()
        val manager = RingtoneManager(context)
        manager.setType(RingtoneManager.TYPE_NOTIFICATION)
        val cursor = manager.cursor
        val count = cursor.count
        for (i in 0 until count) {
            ringtoneList.add(manager.getRingtone(i))
        }
        return ringtoneList
    }

    /**
     *
     * @param context
     * @param position
     * @return
     */

    /**
     * Get pixel value from millimeter real world distance, depending on device
     * density factor
     *
     * @param context
     * @param swipeMarginMillimeter
     * @return
     */
    fun millimeters2px(context: Context, swipeMarginMillimeter: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_MM, swipeMarginMillimeter.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }

    /**
     * hideSoftInput
     */
    fun hideSoftInput(context: Activity) {
        // check the input is active
        val manager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val currentFocusedView = context.currentFocus
        if (manager.isActive && currentFocusedView != null) {
            val windowToken = currentFocusedView.windowToken
            manager.hideSoftInputFromWindow(windowToken, 0)
        }
    }

    /**
     * displaySoftInput
     */
    fun displaySoftInput(context: Context, view: View) {
        // check the input is active
        val manager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        view.requestFocus()
        manager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
        manager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    /**
     * check if location is enabled in device setting
     * and if location (ACCESS_FINE_LOCATION & ACCESS_COARSE_LOCATION)
     * marshmallow permission is granted
     *
     * Note that CHECK Permission with 'ACCESS_FINE_LOCATION' can send Settings
     * request with GeolocationDeviceStatus to true
     * => Be careful for PENDING_CUG_RESPONSE error!
     *
     * @see STWCommonUtil.isLocationEnabled
     * @param context
     * @return true if location is enabled in device, false otherwise
     */


}