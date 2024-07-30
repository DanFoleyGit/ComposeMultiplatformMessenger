//package navigation
//
//import androidx.compose.animation.AnimatedContentTransitionScope
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.RowScope
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.windowInsetsPadding
//import androidx.compose.foundation.selection.selectableGroup
//import androidx.compose.material.Divider
//import androidx.compose.material.Icon
//import androidx.compose.material.IconButton
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Scaffold
//import androidx.compose.material.Surface
//import androidx.compose.material.Text
//import androidx.compose.material.TopAppBar
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.automirrored.filled.ArrowBack
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.ColorFilter
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.navigation.NavController
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.currentBackStackEntryAsState
//import androidx.navigation.compose.rememberNavController
//import org.jetbrains.compose.resources.painterResource
//
//@Composable
//fun HomeNav() {
//    val navController = rememberNavController()
//
//    NavHostMain(
//        navController = navController,
//        onNavigate = { routeName ->
//            navigateTo(routeName, navController)
//        }
//    )
//}
//
//@Composable
//fun NavHostMain(
//    navController: NavHostController = rememberNavController(),
//    onNavigate: (rootName: String) -> Unit,
//) {
//    val backStackEntry by navController.currentBackStackEntryAsState()
//    val currentScreen = backStackEntry?.destination
//
//    Scaffold(
//        topBar = {
//            val title = getTitle(currentScreen)
//            TopBar(
//                title = title,
//                canNavigateBack = currentScreen?.route == AppScreen.Detail.route,
//                navigateUp = { navController.navigateUp() }
//            )
//        },
//        bottomBar = {
//            BottomNavigationBar(navController)
//        }
//    ) { innerPadding ->
//        NavHost(
//            navController = navController,
//            startDestination = BottomBarScreen.Home.route,
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(innerPadding),
//            enterTransition = {
//                slideIntoContainer(
//                    AnimatedContentTransitionScope.SlideDirection.Left,
//                    animationSpec = tween(500)
//                )
//            },
//            exitTransition = {
//                slideOutOfContainer(
//                    AnimatedContentTransitionScope.SlideDirection.Left,
//                    animationSpec = tween(500)
//                )
//            },
//            popEnterTransition = {
//                slideIntoContainer(
//                    AnimatedContentTransitionScope.SlideDirection.Right,
//                    animationSpec = tween(500)
//                )
//            },
//            popExitTransition = {
//                slideOutOfContainer(
//                    AnimatedContentTransitionScope.SlideDirection.Right,
//                    animationSpec = tween(500)
//                )
//            }
//        ) {
//            composable(route = BottomBarScreen.Home.route) {
//                HomeView(onNavigate = onNavigate)
//            }
//            composable(route = BottomBarScreen.Reels.route) {
//                ReelsView(onNavigate = onNavigate)
//            }
//            composable(route = BottomBarScreen.Profile.route) {
//                ProfileView(onNavigate = onNavigate)
//            }
//            composable(route = AppScreen.Detail.route) {
//                DetailsView(onNavigate = onNavigate)
//            }
//        }
//    }
//}
//
//fun getTitle(currentScreen: NavDestination?): String {
//    return when (currentScreen?.route) {
//        BottomBarScreen.Home.route -> {
//            "Home"
//        }
//
//        BottomBarScreen.Reels.route -> {
//            "Reels"
//        }
//
//        BottomBarScreen.Profile.route -> {
//            "Profile"
//        }
//
//        AppScreen.Detail.route -> {
//            "Detail"
//        }
//
//        else -> {
//            ""
//        }
//    }
//}
//
//fun navigateTo(
//    routeName: String,
//    navController: NavController
//) {
//
//    navController.navigate(routeName)
//
//}
//
//sealed class AppScreen(val route: String) {
//    data object Detail : AppScreen("nav_detail")
//}
//
//sealed class BottomBarScreen(
//    val route: String,
//    var title: String,
//    val defaultIcon: ImageVector
//) {
//    data object Home : BottomBarScreen(
//        route = "HOME",
//        title = "Home",
//        defaultIcon = Icons.Filled.Home,
//    )
//
//    data object Reels : BottomBarScreen(
//        route = "REELS",
//        title = "Reels",
//        defaultIcon = Icons.Filled.Home,
//    )
//
//    data object Profile : BottomBarScreen(
//        route = "PROFILE",
//        title = "Profile",
//        defaultIcon = Icons.Filled.Home,
//    )
//}
//
////@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun TopBar(
//    title: String,
//    canNavigateBack: Boolean,
//    navigateUp: () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    TopAppBar(
//        title = { Text(title) },
//        backgroundColor = MaterialTheme.colors.background,
//        modifier = modifier,
//        navigationIcon = {
//            if (canNavigateBack) {
//                IconButton(onClick = navigateUp) {
//                    Icon(
//                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
//                        contentDescription = "back_button"
//                    )
//                }
//            }
//        }
//    )
//}
//
//@Composable
//fun BottomNavigationBar(
//    navController: NavHostController,
//) {
//    val homeItem = BottomBarScreen.Home
//    val reelsItem = BottomBarScreen.Reels
//    val profileItem = BottomBarScreen.Profile
//
//    val screens = listOf(
//        homeItem,
//        reelsItem,
//        profileItem
//    )
//
//    AppBottomNavigationBar(show = navController.shouldShowBottomBar) {
//        screens.forEach { item ->
//            AppBottomNavigationBarItem(
//                icon = item.defaultIcon,
//                label = item.title,
//                onClick = {
//                    navigateBottomBar(navController, item.route)
//                },
//                selected = navController.currentBackStackEntry?.destination?.route == item.route
//            )
//        }
//    }
//}
//
//@Composable
//fun AppBottomNavigationBar(
//    modifier: Modifier = Modifier,
//    show: Boolean,
//    content: @Composable (RowScope.() -> Unit),
//) {
//    Surface(
//        color = MaterialTheme.colors.background,
//        contentColor = MaterialTheme.colors.onBackground,
////        modifier = modifier.windowInsetsPadding(BottomAppBarDefaults.windowInsets)
//    ) {
//        if (show) {
//            Column {
//                Divider(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(1.dp),
//                    color = MaterialTheme.colors.primary.copy(alpha = 0.2f)
//                )
//
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(65.dp)
//                        .selectableGroup(),
//                    verticalAlignment = Alignment.CenterVertically,
//                    content = content
//                )
//            }
//        }
//    }
//}
//
//@Composable
//fun RowScope.AppBottomNavigationBarItem(
//    modifier: Modifier = Modifier,
//    icon: ImageVector,
//    label: String,
//    onClick: () -> Unit,
//    selected: Boolean,
//) {
//    Column(
//        modifier = modifier
//            .weight(1f)
//            .clickable(
//                onClick = onClick,
//            ),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.spacedBy(4.dp)
//    ) {
//
//        Icon(
//            icon = icon,
//            contentDescription = label,
//            tint = if (selected) {
//                MaterialTheme.colors.primary
//            } else {
//                MaterialTheme.colors.onPrimary
//            },
//            modifier =
//                Modifier.clickable {
//                    onClick()
//                }.size(24.dp)
//
//        )
//        Image(
//            painter = painterResource(icon),
//            contentDescription = icon.toString(),
//            contentScale = ContentScale.Crop,
//            colorFilter = if (selected) {
//                ColorFilter.tint(MaterialTheme.colors.primary)
//            } else {
//                ColorFilter.tint(MaterialTheme.colors.onPrimary)
//            },
//            modifier = modifier.then(
//                Modifier.clickable {
//                    onClick()
//                }
//                    .size(24.dp)
//            )
//        )
//
//
//        Text(
//            text = label,
//            style = MaterialTheme.typography.body1,
//            fontWeight = if (selected) {
//                FontWeight.SemiBold
//            } else {
//                FontWeight.Normal
//            },
//            color = if (selected) {
//                MaterialTheme.colors.primary
//            } else {
//                MaterialTheme.colors.onPrimary
//            }
//        )
//    }
//}
//
//private fun navigateBottomBar(navController: NavController, destination: String) {
//    navController.navigate(destination) {
//        navController.graph.startDestinationRoute?.let { route ->
//            popUpTo(BottomBarScreen.Home.route) {
//                saveState = true
//            }
//        }
//        launchSingleTop = true
//        restoreState = true
//    }
//}
//
//private val NavController.shouldShowBottomBar
//    get() = when (this.currentBackStackEntry?.destination?.route) {
//        BottomBarScreen.Home.route,
//        BottomBarScreen.Reels.route,
//        BottomBarScreen.Profile.route,
//        -> true
//
//        else -> false
//    }