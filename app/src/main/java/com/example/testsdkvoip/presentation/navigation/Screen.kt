package com.example.testsdkvoip.presentation.navigation

import com.example.testsdkvoip.common.CONTACT_LIST
import com.example.testsdkvoip.common.LOGIN_PATH

sealed class Screen(val route: String) {
    object Login : Screen(LOGIN_PATH)
    object ContactList : Screen(CONTACT_LIST)
}