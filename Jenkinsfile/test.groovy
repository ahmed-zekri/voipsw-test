/*
 *
 * 	StreamWIDE (Team on The Run)
 *
 * @createdBy  AndroidTeam on mar., 12 avr. 2022 14:14:07 +0200
 * @copyright  Copyright (c) 2022 StreamWIDE UK Ltd (Team on the Run)
 * @email      support@teamontherun.com
 *
 * 	© Copyright 2022 StreamWIDE UK Ltd (Team on the Run). StreamWIDE is the copyright holder
 * 	of all code contained in this file. Do not redistribute or
 *  	re-use without permission.
 *
 * @lastModifiedOn mar., 12 avr. 2022 14:05:40 +0200
 */

pipeline {
    agent {
        node any
    }
    stages {
        stage('Build & Install') {
            steps {
                parallel(
                        "step 1":
                                {
                                    sh 'chmod +x ./gradlew && ./gradlew --no-daemon --stacktrace clean :app:assembleDebug :app:assembleDebugAndroidTest'
                                }
                )
            }

        }
//Build the apk and the test apk which will run the tests on the apk

    }
}

