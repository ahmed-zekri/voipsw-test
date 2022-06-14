package com.call.testsdkvoip.common.constants

/*
 *
 * 	StreamWIDE (Team on The Run)
 *
 * @createdBy  AndroidTeam on jeu., 16 sept. 2021 15:52:14 +0100
 * @copyright  Copyright (c) 2021 StreamWIDE UK Ltd (Team on the Run)
 * @email      support@teamontherun.com
 *
 * 	© Copyright 2021 StreamWIDE UK Ltd (Team on the Run). StreamWIDE is the copyright holder
 * 	of all code contained in this file. Do not redistribute or
 *  	re-use without permission.
 *
 * @lastModifiedOn jeu., 16 sept. 2021 15:33:51 +0100
 */

object LoggerConstant {
    const val LOGGER_SEPARATOR = "|"

    // a general tag for application
    const val APPLICATION = "APPLICATION"

    // TAG used to log all actions related to the authentication process
    const val AUTHENTICATION = "AUTHENTICATION"

    // TAG used to log all user interface actions related to the list of messages
    const val MESSAGE = "MESSAGE"
    const val LOG_REFRESH_CONVERSATION = "RefreshConversation|"

    // TAG used to all Attchaments Actions
    const val ATTACHMENT = "ATTACHMENT"

    // TAG used to log all User Interface Action related to Contact
    const val CONTACT = "CONTACT"

    // TAG used to all Notifications actions
    const val NOTIFICATION = "NOTIFICATION"

    // TAG used to log all user interface actions related to the thread or the list
    // of thread
    const val THREAD = "THREAD"

    // TAG used to log all receivers actions
    const val RECEIVER = "RECEIVER"

    // TAG used to log all actions related to system settings (network status, gps
    // enable/disabled....)
    const val SETTINGS = "SETTINGS"

    // TAG used to all action of VOIP
    const val VOIP = "VOIP"
    const val LOG_VOIP_SIP = "VOIP_SIP"
    const val VOIP_STACK = VOIP + LOGGER_SEPARATOR + "VOIP_STACK"
    const val VOIP_CALL = VOIP + LOGGER_SEPARATOR + "VOIP_Call"
    const val VOIP_WALKIE_TALKIE = VOIP + LOGGER_SEPARATOR + "VOIP_Walkie_Talkie"
    const val VOIP_CHANNEL = VOIP + LOGGER_SEPARATOR + "VOIP_Channel"
    const val VOIP_VIDEO_CALL = VOIP + LOGGER_SEPARATOR + "VOIP_VideoCall"
    const val VIDEO_STREAMING = "VIDEO_STREAMING"
    const val VIDEO_CONFERENCING = "VIDEO_CONFERENCING"
    const val VOIP_AUDIO = VOIP + LOGGER_SEPARATOR + "VOIP_AUDIO"
    const val VOIP_WAITING_CALL = VOIP + LOGGER_SEPARATOR + "WAITING_CALL"
    const val VOIP_CONFERENCE_CALL = VOIP + LOGGER_SEPARATOR + "VOIP_Conference_Call"
    const val VOIP_AMBIENT_LISTENING = VOIP + LOGGER_SEPARATOR + "VOIP_AmbientListening"
    const val VOIP_BROADCAST_CALL = VOIP + LOGGER_SEPARATOR + "VOIP_broadcast_call"

    // TAG used to all action of CALL_OUT
    const val CALL_OUT = "CALL_OUT"
    const val VOIP_CALL_OUT = VOIP + LOGGER_SEPARATOR + CALL_OUT

    // TAG used to all action of GEOLOC
    const val GEOLOC = "GEOLOC"

    // TAG used to log all User Interface Action related to myBusiness
    const val MY_BUSINESS = "MY_BUSINESS"
    const val CAMERA = "CAMERA"
    const val BLUETOOTH_LE = "BLUETOOTH_LE"
    const val LONE_WORKER = "LONE_WORKER"
    const val MAN_DOWN = LONE_WORKER + LOGGER_SEPARATOR + "MAN_DOWN"
    const val POSITIVE_SECURITY = LONE_WORKER + LOGGER_SEPARATOR + "POSITIVE_SECURITY"

    // TAG
    const val NETWORK = "NETWORK"

    // Sub TAG used to log all actions related to an emergency alert
    const val EMERGENCY = "EMERGENCY"

    // TAG used to log all user interface actions related to the widget of process
    const val MY_BUSINESS_WIDGET = MY_BUSINESS + "WIDGET"

    // TAG used to log all user interface actions related to the process or the list
    // of process
    const val MY_BUSINESS_PROCESS = MY_BUSINESS + "_PROCESS"

    // TAG used to log all user interface actions related to the process or the list
    // of process
    const val MY_BUSINESS_TEMPLATE = MY_BUSINESS + "_TEMPLATE"
    const val MY_BUSINESS_WIDGET_CONDITIONAL = MY_BUSINESS_WIDGET + LOGGER_SEPARATOR + "CONDITIONAL"

    // SUB TAG used to log all user interfaces actions related to the settings
    const val USER_SETTINGS = SETTINGS + LOGGER_SEPARATOR + "SETTINGS_USER"

    // SUB TAG used to log all default TOTR settings values
    const val SETTINGS_APPLICATION = SETTINGS + LOGGER_SEPARATOR + "SETTINGS_APPLICATION"
    const val LOG_UPDATE_ATTACHMENT_VIEW = "RefreshAttachmentView|"
    const val NFC_TAG = APPLICATION + LOGGER_SEPARATOR + "NFC_TAG"
    const val ALFRESCO = "ALFRESCO"
    const val BLUETOOTH = "BLUETOOTH"

    // a general tag for security
    const val SECURITY = "SECURITY"
    const val PREFERENCES = "PREFERENCES"

    // Sub TAG used to all actions of GEOLOC where are you
    const val GEOLOC_WHERE_ARE_YOU = GEOLOC + "_WHERE_ARE_YOU"

    // TAG used to all action of GEOLOC realtime
    const val GEOLOC_REALTIME = GEOLOC + "_REALTIME"

    // TAG used to in SmartMsManager
    const val SERVICE = "SERVICE"
    const val MY_BUSINESS_ALARM = MY_BUSINESS + LOGGER_SEPARATOR + "NOTIFICATION_ALARM"

    // TAG used to log all actions in custom views
    const val CUSTOM_VIEW = "CUSTOM_VIEW"
    const val DIALOG = "DIALOG"

    // TAG used to all action of BEACON
    const val BEACON = "BEACON"

    // TAG used to all action of Operational status
    const val OPERATIONAL_STATUS = "OPERATIONAL_STATUS"
    const val CONFIGURATION = "CONFIGURATION"

    // TAG used to log all actions related to the phones
    const val PHONE = "PHONE"

    // TAG used to log all actions related to the Hardware
    const val HARDWARE = "HARDWARE"

    //Tag used to log all actions related to WiFi lock management in the device
    const val LOG_VOIP_WIFI_LOCK = "VOIP_WIFI_LOCK|"

    //Tag used to log all enhanced intent
    const val ENHANCED_INTENT = "ENHANCED_INTENT"

    //Tag used to log all operation in main wakelock manager
    const val WAKELOCK = "WAKELOCK"

    // Tag used to log all operation related to OSM & ArcGis
    const val LOCATION = "LOCATION"

    //Tags used to log all operations related to Http Requests
    const val HTTP = NETWORK + LOGGER_SEPARATOR + "HTTP"
    const val FALLBACK_HTTP = NETWORK + LOGGER_SEPARATOR + "HTTP" + LOGGER_SEPARATOR + "FALLBACK"

    // TAG used to log all actions related to the my rules feature
    const val MY_RULES = "MY_RULES"

    // Tag used to log all operation to featureFlags
    const val FEATURE_FLAGS = "FEATURE_FLAGS"

    // TAG used to log all actions related to the Actuator hardware
    const val USB_HARDWARE = "USB_HARDWARE"

    // TAG used to log all User Interface Action related to external_user
    const val EXTERNAL_USER = "EXTERNAL_USER"

    // TAG used to log all actions related to the Actuator hardware
    const val DEFAULT_CALLING_APP = "DEFAULT_CALLING_APP"

    // SUB TAG used to log all user interfaces actions related to the immobility detection
    const val IMMOBILITY_DETECTION = SETTINGS + LOGGER_SEPARATOR + "IMMOBILITY_DETECTION"
}