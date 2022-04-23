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
import by.candy.suharnica.android.MainViewModel
import by.candy.suharnica.android.utils.Icons
import by.candy.suharnica.android.utils.NavGraph


@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        NavGraph.Catalog,
        NavGraph.Basket,
        NavGraph.Profile
    )
    BottomNavigation(
        backgroundColor = Color.White,
        contentColor = Color.Black
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                //modifier = Modifier.padding(top = 35.dp),
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                selected = true,

                icon = {
                    Icon(
                        painter = painterResource(id = item.icon!!.image),
                        contentDescription = stringResource(id = item.icon.description.resourceId)
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
fun BasketScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SearchBar()
        Text(
            text = "Basket",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            //.background(colorResource(id = R.color.grey))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Profile",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            //modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
        //Profile()
    }
}

@Composable
fun Navigation(navController: NavHostController, viewModel: MainViewModel) {
    NavHost(navController, startDestination = NavGraph.Catalog.route) {
        composable(NavGraph.Catalog.route) {
            CatalogScreen(viewModel, navController)
        }
        composable(NavGraph.Basket.route) {
            BasketScreen()
        }
        composable(NavGraph.Profile.route) {
            ProfileScreen()
        }
        composable(
            "${NavGraph.DetailScreen.route}/itemId={itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.LongType })
        ) { backStackEntry ->
            DetailScreen(viewModel, backStackEntry.arguments?.getLong("itemId") ?: 0)
        }
    }
}


//@Preview(showBackground = true)
@Composable
fun MainScreen(viewModel: MainViewModel) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) {
        Navigation(navController, viewModel)
    }
}
