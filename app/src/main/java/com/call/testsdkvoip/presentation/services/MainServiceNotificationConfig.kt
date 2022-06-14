package com.call.testsdkvoip.presentation.services

import android.app.PendingIntent
import androidx.annotation.NonNull
import androidx.annotation.Nullable


class MainServiceNotificationConfig {
    @get:Nullable
    var channelId: String? = null
        private set

    @get:Nullable
    var groupId: String? = null
        private set

    @get:Nullable
    var channelName: String? = null
        private set

    @get:Nullable
    var channelDescription: String? = null
        private set

    @get:Nullable
    var pendingIntent: PendingIntent? = null
        private set
    var smallIconResId = 0
        private set

    @get:Nullable
    var contentTitle: String? = null
        private set

    @get:Nullable
    var connectingContentText: String? = null
        private set

    @get:Nullable
    var connectedContentText: String? = null
        private set

    @get:Nullable
    var geolocationContentText: String? = null
        private set

    private constructor() {}
    internal constructor(builder: Builder) {
        channelId = builder.channelId
        groupId = builder.groupId
        channelName = builder.channelName
        channelDescription = builder.channelDescription
        pendingIntent = builder.pendingIntent
        smallIconResId = builder.smallIconResId
        contentTitle = builder.contentTitle
        connectingContentText = builder.connectingContentText
        connectedContentText = builder.connectedContentText
        geolocationContentText = builder.geolocationContentText
    }

    class Builder {
        var channelId: String? = null
            private set
        var groupId: String? = null
            private set
        var channelName: String? = null
            private set
        var channelDescription: String? = null
            private set
        var pendingIntent: PendingIntent? = null
            private set
        var smallIconResId = 0
            private set
        var contentTitle: String? = null
            private set
        var connectingContentText: String? = null
            private set
        var connectedContentText: String? = null
            private set
        var geolocationContentText: String? = null
            private set

        constructor() {}
        constructor(@Nullable config: MainServiceNotificationConfig?) {
            if (config == null) {
                return
            }
            channelId = config.channelId
            groupId = config.groupId
            channelName = config.channelName
            channelDescription = config.channelDescription
            pendingIntent = config.pendingIntent
            smallIconResId = config.smallIconResId
            contentTitle = config.contentTitle
            connectingContentText = config.connectingContentText
            connectedContentText = config.connectedContentText
            geolocationContentText = config.geolocationContentText
        }

        @NonNull
        fun channelId(@NonNull channelId: String?): Builder {
            this.channelId = channelId
            return this
        }

        @NonNull
        fun groupId(@NonNull groupId: String?): Builder {
            this.groupId = groupId
            return this
        }

        @NonNull
        fun channelName(@NonNull channelName: String?): Builder {
            this.channelName = channelName
            return this
        }

        @NonNull
        fun channelDescription(@NonNull channelDescriptio: String?): Builder {
            channelDescription = channelDescriptio
            return this
        }

        @NonNull
        fun pendingIntent(@NonNull pendingIntent: PendingIntent?): Builder {
            this.pendingIntent = pendingIntent
            return this
        }

        @NonNull
        fun smallIconResId(smallIconResId: Int): Builder {
            this.smallIconResId = smallIconResId
            return this
        }

        @NonNull
        fun contentTitle(@NonNull contentTitle: String?): Builder {
            this.contentTitle = contentTitle
            return this
        }

        @NonNull
        fun connectingContentText(@NonNull connectingContentText: String?): Builder {
            this.connectingContentText = connectingContentText
            return this
        }

        @NonNull
        fun connectedContentText(@NonNull connectedContentText: String?): Builder {
            this.connectedContentText = connectedContentText
            return this
        }

        @NonNull
        fun geolocationContentText(@NonNull geolocationContentText: String?): Builder {
            this.geolocationContentText = geolocationContentText
            return this
        }

        @NonNull
        fun build(): MainServiceNotificationConfig {
            return MainServiceNotificationConfig(this)
        }
    }
}