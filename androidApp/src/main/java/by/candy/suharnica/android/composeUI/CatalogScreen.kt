package by.candy.suharnica.android.composeUI

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import by.candy.suharnica.android.BasketItem
import by.candy.suharnica.android.MainViewModel
import by.candy.suharnica.android.utils.Colors
import by.candy.suharnica.android.utils.Icons
import by.candy.suharnica.android.utils.NavGraph
import by.candy.suharnica.cache.databases.OnBasketMode
import by.candy.suharnica.entity.CatalogItem
import coil.compose.SubcomposeAsyncImage


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CatalogScreen(viewModel: MainViewModel, navController: NavController) {
    val sortFlow = viewModel.sortFlow.collectAsState().value
    val searchFlow = viewModel.searchFlow.collectAsState().value
    val catalogItems = viewModel.catalogList(sortFlow, searchFlow)
        .collectAsState(initial = listOf()).value
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchBar(viewModel)
        SortBar(viewModel = viewModel)
        Box() {
            LazyVerticalGrid(
                modifier = Modifier.padding(bottom = 59.dp),
                cells = GridCells.Fixed(2)
            ) {
                items(
                    items = catalogItems,
                    itemContent = {
                        CatalogItem(item = it, navController, viewModel)
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
        }
    }
}


@Composable
fun CatalogItem(
    item: CatalogItem, navController: NavController,
    viewModel: MainViewModel
) {
    val count = viewModel.getItemCountInBasket(item.id)
        .collectAsState(initial = 0).value

    Box(modifier = Modifier
        .drawBehind {
            drawLine(
                Color.Black,
                Offset(0f, size.height),
                Offset(size.width, size.height),
                7f
            )
            drawLine(
                Color.Black,
                Offset(size.width, 0f),
                Offset(size.width, size.height),
                5f
            )
        }
        .padding(2.dp)
        .clickable(onClick = {
            navController.navigate("${NavGraph.DetailScreen.route}/itemId=${item.id}")
        })
    ) {
        Column() {
            SubcomposeAsyncImage(
                modifier = Modifier.height(200.dp),
                model = item.imgUrl[0],
                loading = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                },
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Row(modifier = Modifier.padding(start = 10.dp)) {
                Text(
                    modifier = Modifier
                        .background(Colors.RedSale.color)
                        .padding(horizontal = 2.dp),
                    text = "${item.price} BYN",
                    fontWeight = FontWeight.Black,
                    fontSize = 22.sp
                )
                Text(
                    modifier = Modifier
                        .padding(6.dp)
                        .drawBehind {
                            drawLine(
                                Color.Gray,
                                Offset(0f, size.height / 1.7f),
                                Offset(size.width, size.height / 1.7f),
                                2f
                            )
                        },
                    fontSize = 16.sp,
                    text = "${item.priceSale}",
                    color = Color.Gray
                )
            }

            Text(
                modifier = Modifier.padding(start = 10.dp),
                text = item.label,
                fontSize = 16.sp,
                maxLines = 1
            )
            Text(
                modifier = Modifier.padding(start = 10.dp, bottom = 11.dp),
                text = "${item.weight} г",
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 16.dp, top = 11.dp)
        ) {
            Icon(
                painter = painterResource(id = Icons.Smile.image),
                contentDescription = stringResource(id = Icons.Smile.description.resourceId)
            )
            Text(
                modifier = Modifier.padding(start = 4.dp),
                text = item.likes.toString()
            )
        }
        IconButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(11.dp),
            onClick = { viewModel.addItemIntoBasket(item.id, OnBasketMode.ADD) }) {
            Image(
                painter = painterResource(id = Icons.Basket.image),
                contentDescription = stringResource(id = Icons.Basket.description.resourceId)
            )
            Text(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 4.dp),
                text = if (count == 0) "" else "$count",
                fontWeight = FontWeight.Medium,
                color = Color.Gray
            )
        }

    }

}