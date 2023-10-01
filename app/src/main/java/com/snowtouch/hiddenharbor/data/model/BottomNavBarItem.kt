package com.snowtouch.hiddenharbor.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.snowtouch.hiddenharbor.ui.components.AppRoute

data class BottomBarItem(
    val icon: ImageVector,
    val label: String,
    val route: String?
)
val bottomBarItems = listOf(
    BottomBarItem(Icons.Filled.Home, "Home", AppRoute.StartScreen.name),
    BottomBarItem(Icons.Filled.Favorite, "Favorites", ""),
    BottomBarItem(Icons.Filled.AddCircle, "Add", ""),
    BottomBarItem(Icons.Filled.Email, "Messages", ""),
    BottomBarItem(Icons.Filled.Person, "Account", AppRoute.AccountScreen.name)
)