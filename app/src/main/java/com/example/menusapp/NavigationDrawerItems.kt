package com.example.menusapp

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationDrawerItems(
    val title:String,
    val selectedIcon:ImageVector,
    val unselectedIcon:ImageVector,
    val route:String = ""
)