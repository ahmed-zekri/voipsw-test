package com.call.testsdkvoip.presentation.navigation

import com.call.testsdkvoip.common.*
import com.streamwide.smartms.lib.core.api_ktx.contact.model.STWContact

sealed class Screen(val route: String) {
    object Intro : Screen(INTRO_PATH)
    object Login : Screen(LOGIN_PATH)
    object ContactList : Screen(CONTACT_LIST_PATH)
    object Conversation : Screen(CONVERSATION_PATH)

}