package com.VicAndSan.vuhbird.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.VicAndSan.vuhbird.R
import com.VicAndSan.vuhbird.models.BottomNavItem

@Composable
fun BottomNavigationBar(
    navController : NavController,
    navigateToHome: () -> Unit,
    navigateToOurBirds: () -> Unit
    ) {
    val items = listOf(BottomNavItem.Home, BottomNavItem.OurBirds)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination


    BottomAppBar {
        NavigationBar {
            NavigationBarItem(
                selected = false,
                onClick = navigateToHome,
                icon = { Icon(painterResource(id = R.drawable.home_icon), contentDescription = "Home")}
            )
            NavigationBarItem(
                selected = false,
                onClick = navigateToOurBirds,
                icon = { Icon(painterResource(id = R.drawable.ic_bird), contentDescription = "Our Birds")}
            )
        }
    }

}