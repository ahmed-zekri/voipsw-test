package com.call.testsdkvoip.common.constants


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


object BaseConstant {
    // global definition prefix, suffix and separator
    const val ELLIPSIS = "…"
    const val PERCENT_SIGN = "%"
    const val ONE_POINT = "."
    const val SPACE_SEPARATOR = " "
    const val MINUS_SEPARATOR = " - "
    const val MINUS_PREFIX = "-"
    const val EN_DASH_SEPARATOR = "–" // en dash
    const val EM_DASH_SEPARATOR = "—" // em dash
    const val HYPHEN_SEPARATOR = "-" // hyphen
    const val TWO_POINT = ":"
    const val POINT_COMMA = ";"
    const val GROUP_PREFIX = "group:"
    const val PREARRANGED_PREFIX = "prearranged:"
    const val PREARRANGED_CONFERENCE_PREFIX = "conference_prearranged:"
    const val PREARRANGED_EMERGENCY_PREFIX = "prearrangedemergency:"
    const val MAIL_SEPARATOR = "@"
    const val MSISDN_PREFIX = "+"
    const val COMMA_SEPARATOR = ","
    const val SPLIT_ONE_POINT = "\\."
    const val OPENING_PARENTHESIS = "("
    const val CLOSING_PARENTHESIS = ")"
    const val HOOK_LEFT = "["
    const val HOOK_RIGHT = "]"
    const val DIVISION_SEPARATOR = "/"
    const val RETURN_SEPARATOR = " \n "
    const val REQUIRE_FIELD_INDICATOR = " *"
    const val SHARP_CHARACTER = "#"
    const val UNDERSCORE_SEPARATOR = "_"
    const val DOUBLE_ZERO = "00"
    const val RECIPIENT_NAMES_SEPARATOR = ", "
    const val CONTACT_THREAD_SEPERATOR = COMMA_SEPARATOR
    const val PREFIX_UDP = "Udp://"

    /** Http general prefix  */
    const val HTTPS_ADDRESS_PREFIX = "https://"

    /** Http general prefix  */
    const val HTTP_ADDRESS_PREFIX = "http://"
    const val NOT_RETRIEVED = "NOT_RETRIEVED"
    const val NULL = "null"

    // notation in the string to replace by icon in java
    const val NOTATION_ICON_BUTTON = "#iconButton"
    const val OSM_TILE_CACHE_DIRECTORY_NAME = "SWOSM"

    // Thread
    const val THREAD_RECIPIENT_SEPARATOR = " "

    /**
     * For Call-in fqdn MSISDN@OUTSIDE_SMARTMS
     */
    const val CALL_IN_PREFIX = "OUTSIDE_SMARTMS"

    /**
     * AsyncTaskLoaderIds
     */
    const val MESSAGE_LOADER_ID = 10
    const val PROCESS_LOADER_ID = 11
    const val MY_BUSINESS_CONTACT_SELECTION_LOADER_ID = 12
    const val THREAD_FRAGMENT_LOADER_ID = 13
    const val MY_BUSINESS_FRAGMENT_LOADER_ID = 14
    const val CONTACT_FRAGMENT_LOADER_ID = 15
    const val CHANNEL_FRAGMENT_LOADER_ID = 16
    const val CONVERSATION_MEDIA_LOADER_ID = 17
    const val SETTINGS_STORAGE_LOADER_ID = 18
    const val MESSAGE_CONTENT_LOADER_ID = 19
    const val MY_BUSINESS_PROCESS_BY_TAB_LOADER_ID = 20
    const val CONTACT_BUSINESS_LOADER_ID = 21
    const val VOIP_SEARCH_ACTIVITY_LOADER_ID = 22
    const val CONVERSATION_DOCS_LOADER_ID = 23
    const val CONVERSATION_LINKS_LOADER_ID = 24
    const val MY_BUSINESS_PROCESS_FRAGMENT_LOADER_ID = 25
    const val MY_BUSINESS_TEMPLATES_FRAGMENT_LOADER_ID = 26

    // max character number for status
    const val MAX_LENGTH_FOR_CUSTOMER_STATUS = 100

    // File path related
    const val CURRENT_THREAD_ID = "current_thread_id"
    const val DEFAULT_CONDITIONAL_DATE_TIME = "0"

    /**
     * Predefined prefix of data to load an attachment.
     */
    const val IMAGE_TYPE_PREFIX = "image/"
    const val AUDIO_TYPE_PREFIX = "audio/"
    const val VIDEO_TYPE_PREFIX = "video/"
    const val APPLICATION_TYPE_PREFIX = "application/"
    const val X_VCARD_TYPE_PREFIX = "text/x-vcard"
    const val VCARD_TYPE_PREFIX = "text/vcard"
    const val CALENDAR_TYPE_PREFIX = "text/calendar"
    const val X_VCALENDAR_TYPE_PREFIX = "text/x-vcalendar"
    const val GIF_TYPE_PREFIX = "image/gif"


    // Network warnings for different type of recipients
    /** application generated attachments file path. Like compressed image  */


    /** Camera path  */
    const val SW_CAMERA_FOLDER_NAME = "SwCamera"

    /**
     * The regular expression to catch SMS provisionning password content during
     * registration
     */

    const val DEFAULT_LOCATION_THUMBNAIL_ZOOM = 16
    const val VOIP_SOUND_DIRECTORY = "voip_sound"
    const val SETTINGS_HELP_FILE_NAME = "settings_help.pdf"
    const val JPG_EXTENSION = ".jpg"
    const val MAX_BODY_DISPLAY_LENGTH = 1000

    interface ExtraKey {
        companion object {
            /**
             * Extra to be used to know if should start main screen or not to handle
             * programmable key for kyocera device
             */
            const val SHOULD_GO_TO_MAIN = "should_go_to_main"
            const val SHOULD_GO_TO_MY_BUSINESS_OUT_TAB = "should_go_to_my_business_out_tab"
            const val PAGE = "page"

            /** ContactFragment extra  */
            const val EXTRA_CONTACT_PAGE = 0

            /** ThreadFragment extra  */
            const val EXTRA_THREAD_PAGE = 1

            /** ChannelFragment extra  */
            const val EXTRA_CHANNEL_PAGE = 2

            /** SettingFragment extra  */
            const val EXTRA_SETTING_PAGE = 4
            const val EXTRA_QUOTA_EXCEEDED = "quota_exceeded"
            const val QUOTA_EXCEEDED_FALSE = false
            const val QUOTA_EXCEEDED_TRUE = true

            /** Settings fragment extra  */
            const val FRAGMENT_KE_Y = "fragment_key"
            const val SETTING_LIST_FRAGMENT = "setting_list"
            const val ACCOUNT_FRAGMENT = "account"
            const val OPTIONS_FRAGMENT = "options"
            const val NETWORK_FRAGMENT = "network"
            const val PRIVACY_FRAGMENT = "privacy"
            const val STORAGE_NETWORK_FRAGMENT = "storage_network"
            const val SMSFALBACK_FRAGMENT = "smsfalback"
            const val MESSAGE_FRAGMENT = "message"
            const val NOTIFICATION_ALERT_FRAGMENT = "notification_alert"
            const val VOICE_SETTINGS_FRAGMENT = "voice_settings_fragment"
            const val SETTING_IMMOBILITY_DETECTION = "immobility_detection_settings_fragment"
            const val SETTING_LONE_WORKER = "lone_worker_settings_fragment"
            const val SETTING_LONE_WORKER_FROM_NOTIFICATION =
                "lone_worker_settings_fragment_from_notification"
            const val ADVANCED_TELEPHONY_SETTINGS_FRAGMENT = "advanced_telephony_settings_fragment"
            const val MESSAGE_SETTINGS_FRAGMENT = "message_settings_fragment"
            const val VIDEO_SETTINGS_FRAGMENT = "video_settings_fragment"
            const val ACCESSORIES_FRAGMENT = "accessories_fragment"
            const val ABOUT_HELP_FRAGMENT = "about_help"
            const val INFORMATION_FRAGMENT = "information"
            const val RATE_APPLICATION_FRAGMENT = "rate_application"
            const val VISIBILITY = "public_visibility"
            const val PTT_AUTO_RESPONSE_FRAGMENT = "ptt_autoresponse_fragment"
            const val CALL_AUTO_RESPONSE_FRAGMENT = "call_autoresponse_fragment"
            const val CONFERENCE_CALL_AUTORESPONSE_FRAGMENT =
                "conference_call_autoresponse_fragment"
            const val FLOOR_CONTROL_SOUND_EXCEPTION_FRAGMENT =
                "floor_control_sound_exception_fragment"
            const val CUSTOMIZE_APP_FRAGMENT = "customize_app"
            const val SETTING_SECTION = "section"
            const val SETTING_SECTION_GEOLOCATION = "geolocation"
            const val CALL_FORWARD_SETTINGS_FRAGMENT = "call_forward_settings_fragment"
            const val AUTO_RESPONSE_SETTINGS_FRAGMENT = "auto_response_settings_fragment"
            const val NOTIFICATION_DATA = "notification_data"
            const val EXTRA_NOTIFICATION = "notification"
            const val EXTRA_EMERGENCY_ALERT_RECEIVED = "emergency_alert_received"
            const val EXTRA_EMERGENCY_ALERT_MESSAGE_ID = "emergency_alert_message_id"
            const val EXTRA_COUNT = "count"
            const val EXTRA_NOTIFICATION_ADDED_USER = "notification_added_user"
            const val EXTRA_EDIT_CONTACT = "finishActivityOnSaveCompleted"
            /******************************************
             * Extra for My Business Process******
             */
            /**
             * extra to open process creation activity from process resource.
             */
            const val EXTRA_TEMPLATE_UUID = "template_uuid"
            const val EXTRA_PROCESS_NAME = "process_name"
            const val EXTRA_PROCESS_NOTIF_ACTION = "process_notif_action"
            const val EXTRA_PROCESS_VERSION = "process_version"
            const val EXTRA_REPEAT_ALLOWED = "repeat_allowed"
            const val EXTRA_DUE_DATE_MANDATORY = "due_date_mandatory"
            const val EXTRA_DUE_DATE_TIMEFRAME = "due_date_timeframe"
            const val EXTRA_TEMPLATE_PRIORITY = "process_priority"
            const val EXTRA_CONTENT_NFC_MY_BUSINESS_WIDGET = "nfc_content"
            const val EXTRA_CONTENT_ADDRESS_MY_BUSINESS_WIDGET = "extraAddressMyBusinessWidget"
            const val EXTRA_CONTENT_LOCATION_ATTACHMENT_MANDATORY = "extraLocationMyBusinessWidget"
            const val EXTRA_SIGNATURE_FILE_NAME = "signatureFileName"
            const val EXTRA_CONTENT_CONTACT_MY_BUSINESS_WIDGET =
                "extraContentContactMyBusinessWidget"

            /**
             * Extra key for Login activity entrance source
             */
            const val EXTRA_LOGIN_INTENT_SOURCE = "extra_intent_source"
            const val EXTRA_PERMISSIONS = "permissions"
            const val EXTRA_ADMIN_NAME = "admin_name"
            const val EXTRA_ADMIN_EMAIL = "admin_email"

            /** FallbackSmsNotification extra  */
            const val EXTRA_MESSAGES = "messages"
            const val EXTRA_RECIPIENTS = "recipients"
            const val EXTRA_GEOLOCATION_GPS_ONLY = "extra_geolocation_gps_only"
            const val MARSHMALLOW_PERMISSION_NEEDED = "marshmallow_permission_needed"

            /** Contact business Details  */
            const val EXTRA_ADD_CONTACT = "extra_add_contact"
            const val EXTRA_CONTACT_ITEM = "extra_contact_item"
            const val EXTRA_CONTACT_ID = "extra_contact_id"
            const val EXTRA_STW_CONTACT_FROM_SEARCH = "extra_stw_contact_from_search"
            const val EXTRA_SEARCH_CONTENT_DETAIL_CONTACT = "extraSearchContentDetailContact"
            const val EXTRA_MEDIA_PREVIEW_MODE = "extra_media_preview_mode"
            const val EXTRA_MEDIA_PREVIEW_CAPTION = "extra_media_preview_caption"
            const val EXTRA_MEDIA_PREVIEW_ATTACHMENT = "extra_media_preview_attachment"
            const val EXTRA_MEDIA_PREVIEW_SEND_TYPE = "extra_media_preview_send_type"
            const val EXTRA_MEDIA_PREVIEW_TITLE = "extra_media_preview_title"
            const val EXTRA_MEDIA_PREVIEW_ATTACHMENT_URI = "extra_media_preview_attachment_uri"
            const val EXTRA_MEDIA_PREVIEW_ATTACHMENT_ID = "extra_media_preview_attachment_id"

            /**
             * extra Forward Multiple
             */
            const val EXTRA_ATTACHMENT_ID = "forward_mgm_id_attachement_key"
            const val EXTRA_MESSAGE = "forward_mgm_message_key"
            const val EXTRA_FORWARD_EXTERNAL_SHARE = "forward_from_external"
            const val EXTRA_FROM_INTERNAL_SHARE = "extra_from_internal_share"
            const val IS_SETTING_ATTACHMENT = "is_setting_attachment"
            const val EXTRA_NOTIFICATION_MISSED_VIDEO_STREAMING_ID =
                "extra_notification_missed_video_streaming_id"
            const val EXTRA_EMERGENCY_ALERT_KE_Y = "emergencyalertkey"
            const val EXTRA_EMERGENCY_CALL_KE_Y = "emergencycallkey"
            const val EXTRA_SHOW_POPUP_WAITING_CALL_IN_PROGRESS =
                "extra_show_popup_waiting_call_in_progress"

            /**
             * Extra to be used to know recipients to show in map for GeolocationActivity
             */
            const val EXTRA_THREAD_ID_USERS_POSITION = "extra_thread_id_users_position"
            const val EXTRA_GEOLOC_ACTIVITY_LIST_PHONE_ITEM =
                "extra_geoloc_activity_list_phone_item"
            const val EXTRA_GEOLOC_ACTIVITY_LIST_CONTACT_ITEM =
                "extra_geoloc_activity_list_contact_item"
            const val EXTRA_GEOLOC_ACTIVITY_THREAD_ID = "extra_geoloc_activity_thread_id"

            /**
             * Extra to display error dialog
             */
            const val EXTRA_LOGIN_SHOW_ERROR_DIALOG = "extra_key_login_show_error_dialog"
            const val EXTRA_LOGIN_SHOW_WIPE_OUT_DIALOG = 1
            const val EXTRA_LOGIN_SHOW_SECURE_AUTH_TOKEN_ERROR_DIALOG = 2
            const val EXTRA_GROUP_SYSTEM_CONTACT_ID = "GroupSystemContactId"

            /**
             * dialer
             */
            const val EXTRA_DIALER_PHONE_NUMBER = "extra_dialer_phone_number"
            const val EXTRA_DIALER_SEARCH_CONTENT = "extra_dialer_search_content"
            const val EXTRA_CONTACTS_LIST = "extra_contacts_list"
            const val EXTRA_NEED_ADD_ETERNAL_NUMBER_CALL = "extra_need_add_eternal_number_call"
            const val EXTRA_ETERNAL_NUMBER_TO_BE_ADDED_TO_PTT_CONF_CALL =
                "extra_external_number_to_added_to_ptt_conf_call"
            const val EXTRA_EXTERNAL_NUMBER_TO_BE_TRANSFERRED_TO =
                "extra_external_number_to_be_transferred_to"

            /**
             * Extra used in ShortcutReceiver
             */
            const val EXTRA_START_MY_BUSINESS_RESOURCE_TAB = "extra_start_my_business_resource_tab"
            const val EXTRA_START_EMERGENCY_ALERT = "extra_start_emergency_alert"
            const val EXTRA_SELECTED_CONTACT = "extra_key_selected_contact"
            const val EXTRA_SELECTED_GROUPS = "extra_key_selected_groups"

            /** Notification Voip and WalkieTalkie features activation  */
            const val EXTRA_VOIP_FEATURE_ACTIVATED = "allow_voip_feature"
            const val ALLOW_WALKIE_TALKIE = "allow_walkie_talkie_feature"
            const val VOIP_FEATURE_ACTIVATED = "feature_activated"

            /**
             * Extra to display Subscriber/Organization blocked error
             */
            const val EXTRA_SUBSCRIBER_ORGANIZATION_BLOCKED_ERROR =
                "subscriber_organization_blocked_error"
            const val EXTRA_OSM_LATITUDE = "extra_key_osm_latitude"
            const val EXTRA_OSM_LONGITUDE = "extra_key_osm_longitude"
            const val EXTRA_OSM_ZOOM = "extra_key_osm_zoom"
            const val EXTRA_OSM_COLOR = "extra_key_osm_color"
            const val EXTRA_OSM_ADDRESS = "extra_key_osm_address"

            /**
             * Extra used to show supervisory level
             */
            const val EXTRA_NEED_TO_SHOW_SUPERVISORY_LEVEL = "extra_need_to_show_supervisory_level"
            const val EXTRA_UPGRADE_SHOW_AGAIN_VISIBLE = "extra_upgrade_show_again_visible"

            /** Voip Extra Contact  */
            const val VOIP_INVITED_PHONES_EXTRA = "invitedPhones"
            const val VOIP_INVITED_GROUP_CONTACT_EXTRA = "invitedgroups"
            const val VOIP_INVITED_ETERNAL_PHONE_EXTRA = "invitedExternalPhone"
            const val VOIP_CALL_FORWARD_SETTING_PHONE_EXTRA = "voipCallForwardSettingPhoneExtra"

            /** extra for gif  */
            const val GIF_PREVIEW_URL = "gif_preview_url"
            const val GIF_THUMBNAIL_URL = "gif_thumbnail_url"
            const val GIF_ORIGINAL_FILE_PATH = "gif_file_preview_path"
            const val GIF_FILE_THUMBNAIL_PATH = "gif_file_thumbnail_path"
            const val GIF_IDENTIFIER = "identifier"
            const val JPEG_FILE_SERVER_THUMBNAIL_PATH = "gif_file_server_thumbnail_path"
            const val VIEWER_GIF_PATH = "viewer_gif_path"
            const val EXTRA_THREAD_IS_CALL_OUT = "call_out_thread"

            /**
             * Operational status
             */
            const val EXTRA_OPERATIONAL_STATUS = "extra_operational_status"
            /******************************************
             * Extra for My Business Process******
             */
            /**
             * extra to open process creation activity from process resource.
             */
            const val EXTRA_PROCESS_UUID = "process_uuid"

            /**
             * extra of the image path
             */
            const val IMAGE_PATH = "path"

            /**
             * Contact filter
             */
            const val EXTRA_DISPLAY_ORGANIZATION_OPTION = "display_organization_option"

            /**
             * Geolocation prompt
             */
            const val COME_FROM_WIZARD = "come_from_wizard"

            /***************************************************************
             * Extra for Virality/External user (Guest) invitation******
             */
            const val EXTRA_INVITE_GUEST_ACTIVITY_PHONE_LIST =
                "extra_invite_guest_activity_phone_list"
            const val EXTRA_INVITE_GUEST_ACTIVITY_CONTACT_LIST =
                "extra_invite_guest_activity_contact_list"
            const val EXTRA_INVITE_GUEST_ACTIVITY_SELECTED_CONTACTS_COUNT =
                "extra_invite_guest_activity_selected_contact_count"

            ///////////////////////////////////////////////1 APK
            const val SETTING_MOBILE_CONFIGURATION_FRAGMENT = "mobile_configuration"
        }
    }

    object EntranceType {
        const val ENTRANCE_TYPE = "ENTRANCE_TYPE"
        const val ENTRANCE_TYPE_THREAD = 1
        const val ENTRANCE_TYPE_CONTACT_EXIST_THREAD = 2
        const val ENTRANCE_TYPE_NOTIFICATION = 3
        const val ENTRANCE_TYPE_NEW_CONVERSATION = 4
        const val ENTRANCE_TYPE_EXISTING_CONVERSATION = 5
        const val ENTRANCE_TYPE_UPDATE_EXISTING_CONVERSATION = 6
        const val ENTRANCE_TYPE_CONTACT_SELECTED = 7
        const val ENTRANCE_TYPE_CONTACT_SELECTED_GROUP = 8
        const val ENTRANCE_TYPE_CONTACT_SELECTED_GROUP_BUSINESS = 9
        const val ENTRANCE_TYPE_NOTIFICATION_NOTIFICICATION_SMS = 10
        const val ENTRANCE_TYPE_NOTIFICATION_SENDING_FALLBACK_SMS = 11
        const val ENTRANCE_TYPE_NOTIFICATION_VISIBILITY = 12
        const val ENTRANCE_TYPE_GROUP_STATE = 13
        const val ENTRANCE_TYPE_NEW_SUBSCRIBER = 14
        const val ENTRANCE_TYPE_QUOTA_EXCEEDED = 15
        const val ENTRANCE_TYPE_FORWARD_MULTIPLE_MESSAGE = 16
        const val ENTRANCE_TYPE_NOTIFICATION_VOIP_FEATURES_ACTIVATED = 17
        const val ENTRANCE_TYPE_ACCOUNT_EXPIRED = 18
        const val ENTRANCE_TYPE_NOTIFICATION_PERMISSION_NEEDED = 19

        // Voip entrance type
        const val ENTRANCE_TYPE_INCOMMING = 20
        const val ENTRANCE_TYPE_OUTGOING = 21
        const val ENTRANCE_TYPE_SECOND_INCOMMING_ACCEPTED = 22
        const val ENTRANCE_TYPE_NOTIFICATION_VOIP = 23
        const val ENTRANCE_TYPE_EMERGENCY_ALERT = 24
        const val ENTRANCE_TYPE_GO_TO_MAIN = 25

        // My Business keys
        const val ENTRANCE_TYPE_NEW_PROCESS = 26
        const val ENTRANCE_TYPE_EXISTING_PROCESS = 27
        const val ENTRANCE_TYPE_HISTORY_PROCESS = 28
        const val ENTRANCE_TYPE_DUPLICATE_PROCESS = 29
        const val ENTRANCE_TYPE_GO_TO_MY_BUSINESS_OUT_TAB = 30

        // last repeat process key
        const val ENTRANCE_TYPE_LAST_REPEAT_PROCESS = 31

        // Geolocation gps only entrance type.
        const val ENTRANCE_TYPE_NOTIFICATION_GEOLOCATION_GPS_ONLY = 32

        // Missed video streaming entrance type.
        const val ENTRANCE_TYPE_NOTIFICATION_MISSED_VIDEO_STREAMING = 33
        const val ENTRANCE_TYPE_START_MY_BUSINESS_RESOURCE_TAB = 34
        const val ENTRANCE_TYPE_START_EMERGENCY_ALERT = 35
        const val ENTRANCE_TYPE_SUBSCRIBER_ORGANIZATION_BLOCKED = 36
        const val ENTRANCE_TYPE_NOTIFICATION_ACCOUNT = 37
        const val ENTRANCE_TYPE_NOTIFICATION_MESSAGE_REQUIRES_ACK = 38
        const val ENTRANCE_TYPE_PREVIEW_PROCESS = 39
        const val ENTRANCE_TYPE_CLOUD_STORAGE_QUOTA_EXCEEDED = 41
        const val ENTRANCE_TYPE_PROCESS_AUTO_CLEANING_RETENTION = 42
        const val ENTRANCE_TYPE_PROCESS_AUTO_CLEANING_GRACE = 43
        const val ENTRANCE_TYPE_CHANNEL_RESTORE_PERMISSION = 44
        const val ENTRANCE_TYPE_PROCESS_AUTO_CANCELLATION = 45
        const val ENTRANCE_TYPE_NEW_BLE_DEVICE_CONNECTED = 46
        const val ENTRANCE_TYPE_EMERGENCY_CALL = 47
        const val ENTRANCE_TYPE_CREATE_CONVERSATION = 48
        const val ENTRANCE_TYPE_AUTHENTICATION_BAD_PSW = 49
        const val ENTRANCE_TYPE_EMERGENCY_AMBIENT_LISTENING_CALL = 50
        const val ENTRANCE_TYPE_INCORPORATE_GUEST = 51
        const val ENTRANCE_TYPE_EXTERNAL_USER_INVITATION_DEACTIVATED = 52
        const val ENTRANCE_TYPE_NOTIFICATION_VOIP_STACK_PERMISSIONS = 53
        const val ENTRANCE_TYPE_NOTIFICATION_START_SERVICE = 54
        const val ENTRANCE_TYPE_NOTIFICATION_CONNECT_BLUETOOTH_PERMISSIONS = 55
        const val ENTRANCE_TYPE_NOTIFICATION_SCAN_BLUETOOTH_PERMISSIONS = 56
        const val DATA_MESSAGE_SELECTED_POS = "data_message_selected_pos"
        const val SEARCH_CONTENT_OF_MESSAGES = "searchContentOfMessages"
        const val DATA_THREAD = "data_thread"
        const val DATA_CONTACT = "data_contact"
        const val DATA_CONTACT_LIST = "data_contact_list"
        const val DATA_NOTIFICATION = "data_notification"
        const val DATA_NOTIFICATION_MESSAGE_POPUP = "data_notification_message_popup"
        const val DATA_CONTACT_SELECTED = "data_contact_selected"
        const val DATA_PHONE_LIST = "phonesList"
        const val DATA_CONTACT_BUSINESS = "contact_business"
        const val DATA_NOTIFICATION_TAG = "data_notification_tag"
        const val DATA_VOIP_CALL_SCREEN = "data_voip_call_screen"
        const val DATA_SINGLE_CONTACT_IDS = "data_single_contact_ids"
        const val DATA_SINGLE_CONTACT_PHONES = "data_single_contact_ids"
        const val DATA_GROUP_CONTACT_IDS = "data_group_contact_ids"
        const val DATA_CONTACT_IDS = "data_contact_ids"
        const val DATA_SELECTED_PHONES = "data_selected_phones"
        const val COME_FROM_CONTACT_SELECTED = "come_from_selected"
        const val COME_FROM_MESSAGE_ACTIVITY = "come_from_message_activity"
        const val COME_FROM_GROUP = "come_from_group"
        const val NUMBER_RECIPIENT_GROUP_CONTACT = "number_recipient_group_contact"
        const val COME_FROM_CALL_TYPE = "come_from_call_type"
        const val CALL_ID = "call_id"
        const val IS_EMERGENCY_CALL = "is_emergency_call"
        const val IS_BROADCAST_CALL = "is_broadcast_call"
        const val VOIP_CALL_TYPE = "voip_call_type"
        const val DATA_VOIP_SESSION_IS_OUTGOING = "data_notification_voip_session_is_outgoing"
        const val DATA_NOTIFICATION_VOIP_PARTICIPANT_LIST =
            "data_notification_voip_participant_list"
        const val DATA_NOTIFICATION_VOIP_MEDIA_PLAYER = "data_notification_voip_media_player"
        const val DATA_NOTIFICATION_VOIP_WAITING_CALL = "data_notification_voip_waiting_call"
        const val DATA_NOTIFICATION_VOIP_WAITING_SESSION = "data_notification_voip_waiting_session"
        const val DATA_MISSING_CALL_SESSION_IDENTIFIER = "missing_call_session_identifier"
        const val DATA_VOIP_PARTICIPANT_LIST = "data_voip_participant_list"
        const val DATA_VOIP_INTERNATIONAL_NUMBER_LIST = "data_voip_international_number_list"
        const val DATA_VOIP_PARTICIPANT_LIST_OF_PREARRANGED_CALL =
            "data_voip_participant_list_of_prearranged_call"
        const val DATA_VOIP_GROUP_IN_THREAD_LIST = "data_voip_group_in_thread_list"
        const val DATA_IS_PREARRANGED_CALL = "data_is_prearranged_call"
        const val DATA_VOIP_PREARRANGED_CALL_PARTICIPANT_IDS =
            "data_voip_prearranged_call_participant_ids"
        const val DATA_BASE_MESSAGE_NOTIFICATION = "data_base_message_notification"
        const val KEY_REPLY_MESSAGE = "key_reply_message"
        const val DATA_PHONE = "data_phone"

        /**
         * Login activity entrance source
         */
        const val COME_FROM_EXTERNAL_APP_TO_LOGIN = 0
        const val COME_FROM_APP_TO_LOGIN = 1

        /**
         * ContactSelected activity entrance source
         */
        const val CONTACT_SELECTED_ENTRANCE = "contact_selected_entrance"
        const val COME_FROM_FAB = 1
        const val COME_FROM_CONTACT_BUSINESS_DETAIL = 2
        const val COME_FROM_GROUP_CONVERSATION = 3
        const val COME_FROM_ELSEWHERE = 4
        const val COME_FROM_FORWARD_MESSAGE = 5
        const val COME_FROM_SETTINGS = 6
        const val IS_NEW_GROUP_CONVERSATION = "is_new_group_conversation"
        const val COME_FROM_WIZARD_ACTIVITY = 1
    }

    interface RequestCode {
        companion object {
            /**
             * request code for opening location activity
             */
            const val LOCATION = 0x700

            /**
             * request code for opening calendar activity
             */
            const val CALENDAR = 0x701

            /**
             * request code for opening vcard activity
             */
            const val CONTACT_VCARD = 0x702
            const val ADDRESS_MY_BUSINESS_WIDGET = 0x703
            const val CROP_PICTURE = 0x704
            const val PICTURE_FROM_PHONE_CONTACT = 0x705
            const val PICTURE_FROM_CONTACT = 0x706
            const val PICTURE_FROM_GALLERY = 0x707
            const val PICTURE_FROM_CAMERA = 0x708
            const val SELECT_RECEPTIONS_FOR_SUBMIT = 0x709
            const val SELECT_RECEPTIONS_FOR_COMPLETE = 0x70A

            @Deprecated("not used anymore.é")
            val CHECK_PROCESS_OPTIONS = 0x70B
            const val TABLE_WIDGET_PREVIEW = 0x70C
            const val PHOTO_EDITOR = 0x70D

            /**
             * request code for taking a picture
             */
            const val CAMERA = 0x70E

            /**
             * request code for starting image gallery
             */
            const val GALLERY = 0x70F

            /**
             * request code for opening video gallery
             */
            const val VIDEO_GALLERY = 0x710

            /**
             * request code for opening video camera activity
             */
            const val VIDEO_CAMERA = 0x711

            /**
             * request code for opening audio activity
             */
            const val AUDIO = 0x712

            /**
             * request code for opening file activity
             */
            const val FILE = 0x713
            const val DOCUMENT = 0x714
            const val FORWARD_BY_EMAIL = 0x715
            const val SIGNATURE = 0x716
            const val SELECT_RECEPTIONS_FOR_FILTER = 0x717
            const val CALL_NOTIFICATION = 0x719
            const val PHONE_BOOK = 0x71A
            const val MEDIA_PREVIEW_DELETE = 0x71B
            const val MEDIA_PREVIEW = 0x71C
            const val SELECT_CONTACT_TO_SHARE_STREAMING = 0x71D

            /**
             * request code for enable bluetooth
             */
            const val BEACON_BLUETOOTH = 0x71F

            /**
             * request code for enable location from device settings
             */
            const val BEACON_LOCATION = 0x720
            const val CALENDAR_DETAIL = 0x721
            const val VOIP_CONTACT = 0x722
            const val DIALER_SEARCH_RESULT_ACTIVITY = 0x723
            const val DIALER_ADD_ETERNAL_NUMBER_TO_PTT_CONF = 0x724
            const val CONTACT_BUSINESS_DETAILS = 0x725

            /**
             * request code to enable bluetooth for BLE scan
             */
            const val ENABLE_BT = 0x726
            const val ENABLE_BT_REFRSH = 0x727
            const val SOUND_RINGTONE_CALL = 0x728
            const val SOUND_RINGTONE_PUSH_TO_TALK = 0x729
            const val SOUND_NOTIFICATIONS_MY_BUSINESS = 0x72A
            const val SOUND_NOTIFICATIONS_MESSAGE = 0x72B

            /**
             * request code to enable system location for BLE scan
             */
            const val ENABLE_BLE_LOCATION = 0x72C
            const val ENABLE_BLE_LOCATION_REFRSH = 0x72D

            /**
             * selecting new administrator
             */
            const val SELECT_ADMINS = 0x72E
            const val CREATE_CONVERSATION = 0x72F

            /**
             * Contact filter
             */
            const val CONTACT_FILTER = 0x730

            /**
             * Call transfer to External number
             */
            const val DIALER_TRANSFER_FREE_CALL_TO_ETERNAL_NUMBER = 0x731

            /**
             * Invited Guest Contact
             */
            const val GUEST_CONTACT = 0x732

            /**
             * Advanced telephony: Call Forward user level settings
             */
            const val SELECT_CONTACT_CALL_FORWARD_SETTINGS = 0x733
            const val SELECT_EXTERNAL_CONTACT_CALL_FORWARD_SETTINGS = 0x734
            const val WIZARD_ACTIVITY = 0x735
            const val AUTOMATIC_DISCONNECT_NOTIFICATION = 0x736
            const val SELECTED_CONTACT_TO_SEND_MESSAGE = 0x737
            const val VOIP_STACK_ACTIVITY_RESULT_REQUEST_CODE = 0x738
            const val REQUEST_HIBERNATION_CODE = 0x739
        }
    }

    interface MarshmallowPermissionTypeKey {
        companion object {
            /** LocationPermission  */
            const val LOCATION_PERMISSION_TO_ALLOW = 1
        }
    }

    interface FileExtension {
        companion object {
            const val PNG = ".png"
            const val PDF = ".pdf"
            const val JSON = ".json"
        }
    }

    //FIXME : need remove request code from core !!
    @Deprecated("") //Use PermissionRequestCode in the permissionUtil class of the specific module.
    interface PermissionRequestCode {
        companion object {
            /**
             * request code for taking a picture
             */
            const val TAKE_PICTURE_ATTACHMENT = 0x10
            const val TAKE_PICTURE_ACCOUNT = 0x11

            /**
             * request code for opening video camera activity
             */
            const val RECORD_VIDEO_ATTACHMENT = 0x20

            /**
             * request code for opening location activity
             */
            const val LOCATION_ATTACHMENT = 0x30

            /**
             * request code for opening calendar activity
             */
            const val SEND_CALENDAR_ATTACHMENT = 0x40

            /**
             * request code for opening vcard activity
             */
            const val SAVE_CONTACT_VCARD_ATTACHMENT = 0x50

            /**
             * request code for outgoint/incoming calls
             */
            const val OUTGOING_CALL = 0x60
            const val CONNECT_TO_CHANNEL = 0x61

            /**
             * request code for SMS permission
             */
            const val SMS_LOGIN = 0x70
            const val STORAGE_BASEACTIVITY = 0x71
            const val LOCATION_GEOLOC_ACTIVATED = 0x80
            const val PHONE_STATE = 0x90
            const val READ_CONTACT = 0x100
            const val WIZARD_ACTIVITY = 0x110
            const val MICROPHONE_SETTINGS_MANAGER = 0x120
            const val LOCATION_SETTINGS_MANAGER = 0x130

            /**
             * request code for scanning bluetooth
             */
            const val LOCATION_SETTINGS_BLUETOOTH = 0x140
            const val LOCATION_SETTINGS_BLUETOOTH_REFRESH = 0x141

            /**
             * request code for opening location activity from Address widget
             */
            const val LOCATION_MY_BUSINESS_ADDRESS_WIDGET = 0x150

            /**
             * request code for opening camera from Barcode widget
             */
            const val CAMERA_MY_BUSINESS_BARCODE_WIDGET = 0x160

            /**
             * request code for opening camera from contact widget
             */
            const val CAMERA_MY_BUSINESS_CONTACT_WIDGET = 0x170

            /**
             * request code for read contact from contact widget
             */
            const val READ_CONTACTS_MY_BUSINESS_CONTACT_WIDGET = 0x180

            /**
             * request code for opening read calendar from upload widget
             */
            const val READ_CALENDAR_MY_BUSINESS_UPLOAD_WIDGET = 0x190

            /**
             * request code for opening camera from QR code widget
             */
            const val CAMERA_MY_BUSINESS_QR_CODE_WIDGET = 0x200

            /**
             * Request codes for Setting activity result
             */
            const val SETTINGS_INCOMING_CALL = 0x210
            const val SETTING_ACTIVITY = 0x240
            const val LOCATION_BASE_ACTIVITY = 0x250
            const val CAMERA_VIDEO_STREAMING = 0x260
            const val OVERLAY_PERMISSION_VIDEO_STREAMING = 0x270
            const val OVERLAY_PERMISSION_VIDEO_CALL = 0x280
            const val CAMERA_VIDEO_CALL = 0x290
            const val OVERLAY_PERMISSION_VIDEO_STREAMING_ACTIVITY = 0x300
            const val READ_EXTERNAL_STORAGE_FOR_FILE_ATTACHMENT = 0x310
            const val READ_EXTERNAL_STORAGE_FOR_SHARE = 0x320
            const val READ_EXTERNAL_STORAGE_FOR_AUDIO_ATTACHMENT = 0x330
            const val READ_EXTERNAL_STORAGE_FOR_OPEN_GALLERY = 0x340
            const val READ_EXTERNAL_STORAGE_FOR_OPEN_VIDEO_GALLERY = 0x350
            const val READ_EXTERNAL_STORAGE_FOR_OPEN_GALLERY_FOR_MB_CONTACT_WIDGET = 0x360
            const val READ_EXTERNAL_STORAGE_FOR_SOUND = 0x361

            /**
             * request code for opening camera from QR code widget
             */
            const val CAMERA_FOR_QRCODE = 0x362

            /**
             * request code for read contact from contact widget
             */
            const val READ_CONTACTS_EXTERNAL_USER = 0x370
        }
    }


}
