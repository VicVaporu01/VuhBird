package com.VicAndSan.vuhbird.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.VicAndSan.vuhbird.pages.login.LoginScreen
import com.VicAndSan.vuhbird.pages.mainApp.MainScreen
import com.VicAndSan.vuhbird.pages.ourBrids.OurBirds
import com.VicAndSan.vuhbird.pages.signup.SignUpScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            LoginScreen({ navController.navigate(SignUp) }, { navController.navigate(OurBirds) })
        }
        composable<SignUp> {
            SignUpScreen { navController.navigate(Login) }
        }
        composable<Main> {
            MainScreen()
        }
        composable<OurBirds> {
            OurBirds()
        }
    }
}