package by.candy.suharnica.android.composeUI

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import by.candy.suharnica.android.BasketItem
import by.candy.suharnica.android.MainViewModel
import by.candy.suharnica.android.composeUI.profile.Profile
import by.candy.suharnica.android.utils.Colors
import by.candy.suharnica.android.utils.Icons
import by.candy.suharnica.android.utils.NavGraph
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlin.coroutines.coroutineContext


@Composable
fun BottomNavigationBar(navController: NavController, viewModel: MainViewModel) {
    val items = listOf(
        NavGraph.Catalog,
        NavGraph.Basket,
        NavGraph.Profile
    )
    val basketItems = viewModel.getBasket.collectAsState(initial = listOf()).value
    val totalCount = basketItems.sumOf { it.count }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Gray,
                selected = currentRoute == item.route,
                icon = {
                    if (item.icon == Icons.Basket)
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                painter = painterResource(id = item.icon.image),
                                contentDescription = stringResource(id = item.icon.description.resourceId),
                            )
                            Text(
                                modifier = Modifier.padding(top = 2.dp),
                                text =
                                if (totalCount > 0) "$totalCount"
                                else "",
                                color = Colors.RedButton.color,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    else
                        Icon(
                            painter = painterResource(id = item.icon!!.image),
                            contentDescription = stringResource(id = item.icon.description.resourceId),
                        )
                },
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
    Divider(
        color = Color.Black,
        thickness = 2.dp
    )
}

@Composable
fun Navigation(navController: NavHostController, viewModel: MainViewModel) {
    val user = viewModel.userFlow.collectAsState(null).value
    NavHost(navController, startDestination = NavGraph.Catalog.route) {
        composable(NavGraph.Catalog.route) {
            CatalogScreen(viewModel, navController)
        }
        composable(NavGraph.Basket.route) {
            BasketScreen(viewModel)
        }
        composable(NavGraph.Profile.route) {
            if (user != null) {
                Profile(user)
            } else LogInAndSignUpScreen(viewModel)
        }
        composable(NavGraph.LogInAndSignUpScreen.route) {
            LogInAndSignUpScreen(viewModel)
        }
        composable(
            "${NavGraph.DetailScreen.route}/itemId={itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.LongType })
        ) { backStackEntry ->
            DetailScreen(viewModel, navController, backStackEntry.arguments?.getLong("itemId") ?: 0)
        }
    }
}


@Composable
fun MainScreen(viewModel: MainViewModel) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = Color.White
    )
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController, viewModel)
        }
    ) {
        Navigation(navController, viewModel)
    }
}
