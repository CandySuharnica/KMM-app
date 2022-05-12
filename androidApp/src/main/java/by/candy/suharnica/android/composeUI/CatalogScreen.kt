package by.candy.suharnica.android.composeUI

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import by.candy.suharnica.android.MainViewModel
import by.candy.suharnica.android.composeUI.common.SearchBar
import by.candy.suharnica.android.composeUI.common.SortBar
import by.candy.suharnica.android.composeUI.items.CatalogItem
import by.candy.suharnica.android.utils.Colors
import by.candy.suharnica.android.utils.Icons
import by.candy.suharnica.android.utils.NavGraph
import by.candy.suharnica.cache.databases.OnBasketMode
import sqldelight.GetLikes


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CatalogScreen(viewModel: MainViewModel, navController: NavController) {
    val sortFlow = viewModel.sortFlow.collectAsState().value
    val initialList = listOf("Все", "Любимое")
    val sortTypes = viewModel.getTypes.collectAsState(initial = listOf()).value
    val finalListTypes = initialList + sortTypes
    val searchFlow = viewModel.searchFlow.collectAsState().value
    val catalogItems = viewModel.catalogList(sortFlow, searchFlow)
        .collectAsState(initial = listOf()).value.toMutableList()
    val admin = viewModel.admin().collectAsState(initial = false).value
    val listOfLikes =
        viewModel.listOfLikes.collectAsState(initial = listOf()).value.firstNotNullOfOrNull { it }?.likes
            ?: emptyList()
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchBar(text = searchFlow) {
            viewModel.searchFlow.value = it
        }
        SortBar(
            content = finalListTypes,
            sortMode = sortFlow,
        ) {
            viewModel.sortFlow.value = it
        }
        Box() {
            LazyVerticalGrid(
                modifier = Modifier.padding(bottom = 59.dp),
                cells = GridCells.Fixed(2)
            ) {
                if (admin)
                    items(
                        items = catalogItems,
                        itemContent = {
                            CatalogItem(
                                item = it,
                                count = viewModel.getItemCountInBasket(it.id)
                                    .collectAsState(initial = 0).value,
                                liked = listOfLikes.contains(it.id),
                                onClickAddItem = {
                                    viewModel.addItemIntoBasket(
                                        it.id,
                                        OnBasketMode.ADD
                                    )
                                },
                                onClickItem = {
                                    navController.navigate("${NavGraph.DetailScreen.route}/itemId=${it.id}")
                                },
                                onClickLike = {
                                    viewModel.like(it.id)
                                },
                                onRemoveItem = {
                                    viewModel.removeFromCatalog(it.id)
                                    catalogItems.removeIf { item -> item.id == it.id }
                                }
                            )
                        }
                    )
                else items(
                    items = catalogItems,
                    itemContent = {
                        CatalogItem(
                            item = it,
                            count = viewModel.getItemCountInBasket(it.id)
                                .collectAsState(initial = 0).value,
                            liked = listOfLikes.contains(it.id),
                            onClickAddItem = {
                                viewModel.addItemIntoBasket(
                                    it.id,
                                    OnBasketMode.ADD
                                )
                            },
                            onClickItem = {
                                navController.navigate("${NavGraph.DetailScreen.route}/itemId=${it.id}")
                            },
                            onClickLike = {
                                viewModel.like(it.id)
                            }
                        )
                    }
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(8.dp)
                    .align(Alignment.CenterStart),
                color = Color.White
            )
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(8.dp)
                    .align(Alignment.CenterEnd),
                color = Color.White
            )
            if (admin)
                Box(
                    modifier = Modifier
                        .padding(end = 16.dp, bottom = 80.dp)//Не трогать отступ - это костыль!!!
                        .align(Alignment.BottomEnd)
                ) {
                    IconButton(
                        onClick = {
                            navController.navigate(NavGraph.AdminScreen.route)
                        },
                        modifier = Modifier
                            .then(Modifier.size(50.dp))
                            .border(2.dp, Color.Black, shape = CircleShape)
                            .background(color = Colors.RedButton.color, shape = CircleShape)
                    ) {
                        Icon(
                            painter = painterResource(Icons.Plus.image),
                            contentDescription = "Plus icon", tint = Color.Black
                        )
                    }
                }
        }
    }
}