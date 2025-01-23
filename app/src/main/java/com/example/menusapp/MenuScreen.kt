package com.example.menusapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.automirrored.outlined.ExitToApp
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.menusapp.ui.theme.darkBlue
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen() {
    val items = listOf(
        NavigationDrawerItems(
            title = "Bosh sahifa",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
        ),
        NavigationDrawerItems(
            title = "Qidiruv",
            selectedIcon = Icons.Filled.Search,
            unselectedIcon = Icons.Outlined.Search
        ),
        NavigationDrawerItems(
            title = "Ulashish",
            selectedIcon = Icons.Filled.Share,
            unselectedIcon = Icons.Outlined.Share,
        ),
        NavigationDrawerItems(
            title = "Bizga baho bering",
            selectedIcon = Icons.Filled.Star,
            unselectedIcon = Icons.Outlined.Star,
        ),
        NavigationDrawerItems(
            title = "Telegram kanalimiz",
            selectedIcon = Icons.AutoMirrored.Filled.Send,
            unselectedIcon = Icons.AutoMirrored.Outlined.Send,
        ),
        NavigationDrawerItems(
            title = "Sozlamalar",
            selectedIcon = Icons.Filled.Settings,
            unselectedIcon = Icons.Outlined.Settings,
        ),
        NavigationDrawerItems(
            title = "Hisobdan chiqish",
            selectedIcon = Icons.AutoMirrored.Filled.ExitToApp,
            unselectedIcon = Icons.AutoMirrored.Outlined.ExitToApp,
        ),
    )
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                windowInsets = WindowInsets.systemBars
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth().background(darkBlue).padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(Modifier.height(24.dp))
                    IconButton(
                        onClick = {}
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle, "profile",
                            modifier = Modifier.size(256.dp),
                            tint = Color.White
                        )
                    }
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = "Nodirbek Bakhromov",
                        fontWeight = FontWeight.W500,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                    Text(
                        text = "nodirbek@outlook.com",
                        fontWeight = FontWeight.W200,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                    Spacer(Modifier.height(16.dp))
                }
                Spacer(Modifier.height(16.dp))
                items.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = { Text(text = item.title) },
                        selected = index == selectedItem,
                        onClick = {
                            //  navController.navigate(item.route)

                            selectedItem = index
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItem) {
                                    item.selectedIcon
                                } else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding), //padding between items,
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedIconColor = Color.White,
                            unselectedIconColor = darkBlue,
                            selectedContainerColor = darkBlue,
                            unselectedContainerColor = Color.White,
                            selectedTextColor = Color.White,
                            unselectedTextColor = darkBlue
                        ),
                        shape = RoundedCornerShape(24.dp)
                    )
                    if(item.title == "Ulashish" || item.title == "Telegram kanalimiz"){
                        HorizontalDivider(
                            modifier = Modifier.fillMaxWidth(),
                            thickness = 3.dp,
                            color = Color.LightGray
                        )
                    }
                }

            }
        },
        gesturesEnabled = true,
        scrimColor = Color.Black.copy(alpha = 0.6f)
    ) {
        Scaffold(
            topBar = { //TopBar to show title
                TopAppBar(
                    title = {
                        Text(text = items[selectedItem].title)
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }) {
                            Icon(  //Show Menu Icon on TopBar
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    }
                )
            }
        ) { paddingValues ->
            Column(Modifier.fillMaxSize().background(Color.Magenta).padding(paddingValues)) {  }
        }
    }
}