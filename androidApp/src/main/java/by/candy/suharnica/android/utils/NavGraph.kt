package by.candy.suharnica.android.utils

import by.candy.suharnica.MR
import by.candy.suharnica.android.MainViewModel
import by.candy.suharnica.android.R

sealed class NavGraph(
    val icon: Icons?,
    val route: String) {

    object Catalog : NavGraph(
        Icons.Catalog,
        "catalog"
    )

    object Basket : NavGraph(
        Icons.Basket,
        "basket"
    )

    object Profile : NavGraph(
        Icons.Profile,
        "profile"
    )

    object DetailScreen : NavGraph(
        null,
        "detail"
    )

    object LogInAndSignUpScreen : NavGraph(
        null,
        "loginAndSignUp"
    )

    object AdminScreen : NavGraph(
        null,
        "AdminScreen"
    )

    object MakingAnOrderScreen : NavGraph(
        null,
        "MakingAnOrderScreen"
    )

}