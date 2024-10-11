package com.example.bottomnavbarapp3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.bottomnavbarapp3.ui.theme.BottomNavBarApp3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BottomNavBarApp3Theme {

                var selected by remember {
                    mutableIntStateOf(0)
                }

                Scaffold(
                    bottomBar = {

                        NavigationBar {
                            bottomNavItems.forEachIndexed { index, bottomNavItem ->
                                NavigationBarItem(
                                    selected = index == selected,
                                    onClick = {
                                        selected = index
                                        //   navController.navigate (bottomNavItem.route)
                                    },
                                    icon = {

                                        BadgedBox(
                                            badge = {
                                                if (bottomNavItem.badges != 0) {
                                                   Badge{
                                                       Text(
                                                           text = bottomNavItem.badges.toString()
                                                       )
                                                   }
                                                } else if (
                                                    bottomNavItem.hasNews) {
                                                    Badge()
                                                }
                                            }
                                        ) {
                                            Icon(
                                                imageVector =
                                                if (index == selected)
                                                    bottomNavItem.selectedIcon
                                                else
                                                    bottomNavItem.unselectedIcon,
                                                contentDescription = bottomNavItem.title

                                            )
                                        }
                                    },
                                    label = {
                                        Text(text = bottomNavItem.title)
                                    }
                                )
                            }
                        }
                    },
                    floatingActionButton = {

                        FloatingActionButton(
                            onClick = { /*TODO*/ },
                        ) {}
                    }
                ) {

                    val padding = it
                }
            }
        }
    }
}

val bottomNavItems = listOf(

    BottomNavItem(
        title = "Home",
        route = "home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        hasNews = false,
        badges = 0
    ),
    BottomNavItem(

        title = "Posts",
        route = "posts",
        selectedIcon = Icons.Filled.DateRange,
        unselectedIcon = Icons.Outlined.DateRange,
        hasNews = false,
        badges = 0
    ),
    BottomNavItem(

        title = "Notifications",
        route = "notifications",
        selectedIcon = Icons.Filled.Notifications,
        unselectedIcon = Icons.Outlined.Notifications,
        hasNews = false,
        badges = 5
    ),
    BottomNavItem(

        title = "Profile",
        route = "profile",
        selectedIcon = Icons.Filled.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle,
        hasNews = true,
        badges = 0
    )
)

data class BottomNavItem(

    val title: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val hasNews: Boolean,
    val badges: Int

)

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BottomNavBarApp3Theme {
        Greeting("Android")
    }
}