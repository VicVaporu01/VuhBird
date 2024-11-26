package com.VicAndSan.vuhbird.core.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.VicAndSan.vuhbird.components.BottomNavigationBar
import com.VicAndSan.vuhbird.pages.login.LoginScreen
import com.VicAndSan.vuhbird.pages.mainApp.DonateScreen
import com.VicAndSan.vuhbird.pages.mainApp.Home
import com.VicAndSan.vuhbird.pages.mainApp.MainScreen
import com.VicAndSan.vuhbird.pages.mainApp.OurBirds
import com.VicAndSan.vuhbird.pages.signup.SignUpScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            LoginScreen({ navController.navigate(SignUp) }, { navController.navigate(Home) })
        }
        composable<SignUp> {
            SignUpScreen { navController.navigate(Login) }
        }
        composable<Main> {
            MainScreen()
        }
        composable<Home> {
            Scaffold(
                bottomBar = {
                    BottomNavigationBar(
                        navController = navController,
                        navigateToHome = { navController.navigate(Home) },
                        navigateToOurBirds = { navController.navigate(OurBirds) },
                        navigateToDonate = { navController.navigate(DonateScreen) }
                    )
                }
            ) { paddingValues ->
                Home(paddingValues)
            }
        }
        composable<OurBirds> {
            Scaffold(
                bottomBar = {
                    BottomNavigationBar(
                        navController = navController,
                        navigateToHome = { navController.navigate(Home) },
                        navigateToOurBirds = { navController.navigate(OurBirds) },
                        navigateToDonate = { navController.navigate(DonateScreen) }
                    )
                }
            ) { paddingValues ->
                OurBirds(paddingValues)
            }
        }
        composable<DonateScreen> {
            Scaffold(
                bottomBar = {
                    BottomNavigationBar(
                        navController = navController,
                        navigateToHome = { navController.navigate(Home) },
                        navigateToOurBirds = { navController.navigate(OurBirds) },
                        navigateToDonate = { navController.navigate(DonateScreen) }
                    )
                }
            ) { paddingValues ->
                DonateScreen(paddingValues)
            }

        }
        composable<BottomNavigationBar> {
            BottomNavigationBar(
                navController = navController,
                navigateToHome = { navController.navigate(Home) },
                navigateToOurBirds = { navController.navigate(OurBirds) },
                navigateToDonate = { navController.navigate(DonateScreen) }
            )
        }
    }
}