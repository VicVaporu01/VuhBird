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
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun NavigationWrapper(auth: FirebaseAuth, db: FirebaseFirestore) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Login) {
        composable<Login> {
            LoginScreen({ navController.navigate(SignUp) }, { navController.navigate(Home) }, auth)
        }
        composable<SignUp> {
            SignUpScreen({ navController.navigate(Login) }, auth, db)
            //LoginScreen({ navController.navigate(SignUp) }, { navController.navigate(Home)}, auth)
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
                    )
                }
            ) { paddingValues ->
                OurBirds(paddingValues) { birdId ->
                    navController.navigate("$DonateScreen/$birdId")
                }
            }
        }
        composable("$DonateScreen/{birdId}") { backStackEntry ->
            val birdId = backStackEntry.arguments?.getString("birdId")?.toIntOrNull() ?: 1
            Scaffold(
                bottomBar = {
                    BottomNavigationBar(
                        navController = navController,
                        navigateToHome = { navController.navigate(Home) },
                        navigateToOurBirds = { navController.navigate(OurBirds) },
                    )
                }
            ) { paddingValues ->
                DonateScreen(paddingValues, birdId)
            }

        }
        composable<BottomNavigationBar> {
            BottomNavigationBar(
                navController = navController,
                navigateToHome = { navController.navigate(Home) },
                navigateToOurBirds = { navController.navigate(OurBirds) },
            )
        }
    }
}