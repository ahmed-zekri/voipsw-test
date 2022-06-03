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
    agent any
    tools {
        gradle "gradle_7.2.0"
    }
    stage('Build & Install') {
//Build the apk and the test apk which will run the tests on the apk
        sh 'chmod +x gradlew && ./gradlew --no-daemon --stacktrace clean :app:assembleDebug :app:assembleDebugAndroidTest'
    }

    stage('Tests') {
//Start all the existing tests in the test package
        sh './gradlew --no-daemon --debug :app:connectedDebugAndroidTest'
    }
}