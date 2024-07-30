import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.jetbrains.compose.ui.tooling.preview.Preview

import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import screens.AccountScreen
import screens.ChatScreen

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector
)

enum class ScreenNames() {
    Home,
    Account
}

@OptIn(KoinExperimentalAPI::class)
@Composable
@Preview
fun App() {
    MaterialTheme {

        KoinContext {
            val navController: NavHostController = rememberNavController()
            val viewModel = koinViewModel<ChatViewModel>()

            val items = listOf(
                BottomNavigationItem(
                    title = "Home",
                    selectedIcon = Icons.Filled.Home,
                    unSelectedIcon = Icons.Outlined.Home
                ),
                BottomNavigationItem(
                    title = "Account",
                    selectedIcon = Icons.Filled.AccountBox,
                    unSelectedIcon = Icons.Outlined.AccountBox
                )
            )

            var selectedIndex by rememberSaveable {
                mutableStateOf(0)
            }

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
//                    MyBottomBar(items)
                    Column(modifier = Modifier.fillMaxWidth()) {
                        HorizontalDivider()

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(60.dp)
                                .background(Color.White),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            items.forEachIndexed { index, item ->
                                NavigationBarButton(
                                    item = item,
                                    updateIndex = {
                                        selectedIndex = it
                                    },
                                    itemIndex = index,
                                    selectedIndex = selectedIndex,
                                    navController = navController
                                )
                            }
                        }
                    }
                }) {
                NavHost(
                    navController = navController,
                    startDestination = "Home"
                ) {
                    composable("Home") {
                        ChatScreen(viewModel)
                    }
                    composable("Account") {
                        AccountScreen()
                    }
                }
            }



        }
    }
}

@Composable
private fun NavigationBarButton(
    item: BottomNavigationItem,
    selectedIndex: Int,
    navController: NavHostController,
    updateIndex: (Int) -> Unit,
    itemIndex: Int
) {
    Box(
        modifier = Modifier
            .background(
                if (selectedIndex == itemIndex) {
                    Color.Gray
                } else {
                    Color.LightGray
                },
                shape = CircleShape
            )
            .height(40.dp)
            .width(100.dp)
            .clickable {
                updateIndex(itemIndex)
                navController.navigate(item.title)
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = rememberVectorPainter(
                image = if (selectedIndex == 0) {
                    item.selectedIcon
                } else {
                    item.unSelectedIcon
                }
            ),
            contentDescription = "Account Box",
        )
    }
}