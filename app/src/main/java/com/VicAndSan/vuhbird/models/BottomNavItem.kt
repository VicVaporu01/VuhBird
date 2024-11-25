package com.VicAndSan.vuhbird.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.VicAndSan.vuhbird.R

sealed class BottomNavItem(
    val route: String,
    @DrawableRes val icon: Int,
    @StringRes val label: Int
) {
    object Home : BottomNavItem(
        "home",
        R.drawable.home_icon,
        R.string.home
    )

    object OurBirds : BottomNavItem(
        "our_birds",
        R.drawable.ic_bird,
        R.string.our_birds
    )
}