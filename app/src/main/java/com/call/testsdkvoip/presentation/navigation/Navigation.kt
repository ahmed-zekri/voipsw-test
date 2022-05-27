package com.call.testsdkvoip.presentation.navigation

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.call.testsdkvoip.common.CONTACT_PARAM
import com.call.testsdkvoip.presentation.contacts.components.ContactList
import com.call.testsdkvoip.presentation.conversation.components.Conversation
import com.call.testsdkvoip.presentation.intro.components.IntroScreen

import com.call.testsdkvoip.presentation.login.components.LoginScreen
import com.streamwide.smartms.lib.core.api_ktx.contact.model.STWContact

@Composable
fun Navigation() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Intro.route) {

        composable(route = Screen.Intro.route) {


            IntroScreen(navHostController = navController)


        }

        composable(route = Screen.Login.route) {


            LoginScreen()


        }

        composable(route = Screen.ContactList.route) {


            ContactList(navHostController = navController)


        }
        composable(route = Screen.Conversation.route) {
            val stwContact =
                navController.previousBackStackEntry?.savedStateHandle?.get<STWContact>(
                    CONTACT_PARAM
                )
if (stwContact!= null)
            Conversation(stwContact = stwContact)


        }


    }
}