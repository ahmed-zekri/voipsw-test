package com.call.testsdkvoip.common


import android.content.Context
import android.os.PowerManager
import android.os.PowerManager.WakeLock
import android.util.Pair

import com.streamwide.smartms.lib.core.api.environment.logger.STWLoggerHelper


class MainWakeLockManager private constructor(context: Context) {
    private val mWakeLock: WakeLock?
    private val mMainRegisteredComponentSet: MutableSet<MainRegisteredComponent> = HashSet()

    enum class MainRegisteredComponent(private val value: Int) {
        ScreenLock(50), ManDownAlert(51), ReceiveEmergencyAlert(52), CalibrationFailed(53), VoIPSessionInvitation(
            54
        ),
        ImmobilityDetectionAlert(55);

        val componentName: String
            get() {
                if (value == value) {
                    return "ScreenLock"
                } else if (value == value) {
                    return "ManDownAlert"
                } else if (value == value) {
                    return "ReceiveEmergencyAlert"
                } else if (value == value) {
                    return "CalibrationFailed"
                } else if (value == value) {
                    return "VoIPSessionInvitation"
                } else if (value == value) {
                    return "ImmobilityDetectionAlert"
                }
                return "Unknown"
            }

    }

    /**
     * @param mainRegisteredComponent Component to be registered
     * @return True if the passed component is successfully registered or already registered.
     */
    @Synchronized
    fun acquireWakeLock(mainRegisteredComponent: MainRegisteredComponent): Boolean {
        STWLoggerHelper.LOGGER.i(
            Pair.create(CLASS_NAME, "acquireWakeLock"), "",
            "component Need WakeLock = " + mainRegisteredComponent.componentName
        )
        if (mMainRegisteredComponentSet.isEmpty()) {
            if (mWakeLock != null) {
                if (!mWakeLock.isHeld) {
                    STWLoggerHelper.LOGGER.i(
                        Pair.create(CLASS_NAME, "acquireWakeLock"), "",
                        "------ WakeLock acquire : first component acquiring wakelock"
                    )
                    mWakeLock.acquire(10 * 60 * 1000L /*10 minutes*/)
                }
            } else {
                STWLoggerHelper.LOGGER.e(
                    Pair.create(CLASS_NAME, "acquireWakeLock"),
                    "",
                    "WakeLock is null"
                )
                return false
            }
        }
        // return if operation status
        val registeredComponentAdded = mMainRegisteredComponentSet.add(mainRegisteredComponent)
        val wakeLockIsHeld = mWakeLock!!.isHeld
        STWLoggerHelper.LOGGER.d(
            Pair.create(CLASS_NAME, "acquireWakeLock"), "",
            "wakeLockIsHeld : "
                    + wakeLockIsHeld + " - registeredComponentAdded : " + registeredComponentAdded
        )
        if (registeredComponentAdded && wakeLockIsHeld) {
            return true
        } else if (!wakeLockIsHeld) {
            STWLoggerHelper.LOGGER.e(
                Pair.create(CLASS_NAME, "acquireWakeLock"),
                "",
                "WakeLock not acquired"
            )
            return false
        } else if (!registeredComponentAdded) {
            STWLoggerHelper.LOGGER.w(
                Pair.create(CLASS_NAME, "acquireWakeLock"), "",
                mainRegisteredComponent.componentName + " not added "
            )
            return true
        }
        return false
    }

    /**
     * Unregister the registered component
     *
     * @param mainRegisteredComponent Component to be unregistered
     * @return True if the passed component is successfully unregistered or already unregistered.
     */
    @Synchronized
    fun releaseWakeLock(mainRegisteredComponent: MainRegisteredComponent): Boolean {
        STWLoggerHelper.LOGGER.i(
            Pair.create(CLASS_NAME, "releaseWakeLock"), "",
            "component Release WakeLock = " + mainRegisteredComponent.componentName
        )
        // remove the RegisteredComponent
        val registeredComponentRemoved = mMainRegisteredComponentSet.remove(mainRegisteredComponent)
        STWLoggerHelper.LOGGER.d(
            Pair.create(CLASS_NAME, "releaseWakeLock"), "",
            "RegisteredComponentRemoved : $registeredComponentRemoved"
        )
        if (mMainRegisteredComponentSet.isEmpty()) {
            if (mWakeLock != null) {
                if (mWakeLock.isHeld) {
                    STWLoggerHelper.LOGGER.i(
                        Pair.create(CLASS_NAME, "releaseWakeLock"), "",
                        "------ WakeLock release : last component releasing wakelock"
                    )
                    mWakeLock.release()
                }
            } else {
                STWLoggerHelper.LOGGER.e(
                    Pair.create(CLASS_NAME, "releaseWakeLock"),
                    "",
                    "WakeLock is null"
                )
                return false
            }
        }
        return true
    }

    companion object {
        private const val CLASS_NAME = "MainWakeLockManager"

        /**
         * Singleton instance
         */
        private var mInstance: MainWakeLockManager? = null
        fun getInstance(context: Context): MainWakeLockManager {
            synchronized(MainWakeLockManager::class.java) {
                if (mInstance == null) {
                    mInstance = MainWakeLockManager(context)
                }
            }
            return (mInstance)!!
        }
    }

    init {
        val powerManager: PowerManager =
            context.getSystemService(Context.POWER_SERVICE) as PowerManager

        mWakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "TOTR:MainWakeLock")
        mWakeLock.setReferenceCounted(true)
    }
}
