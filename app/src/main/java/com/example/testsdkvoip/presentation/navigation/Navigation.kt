package com.example.testsdkvoip.presentation.navigation

import androidx.compose.runtime.Composable

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.testsdkvoip.presentation.intro.components.IntroScreen

import com.example.testsdkvoip.presentation.login.components.LoginScreen

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


    }
}