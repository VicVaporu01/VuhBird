package com.VicAndSan.vuhbird.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.VicAndSan.vuhbird.R

sealed class BottomNavItem(
    val route: Any,
    @DrawableRes val icon: Int,
    @StringRes val label: Int
) {
    object Home : BottomNavItem(
        Home,
        R.drawable.home_icon,
        R.string.home
    )

    object OurBirds : BottomNavItem(
        OurBirds,
        R.drawable.ic_bird,
        R.string.our_birds
    )

    object Donate: BottomNavItem(
        Donate,
        R.drawable.ic_donate,
        R.string.donate
    )
}