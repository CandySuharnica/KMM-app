package by.candy.suharnica.android.composeUI

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.candy.suharnica.MR
import by.candy.suharnica.android.BasketItem
import by.candy.suharnica.android.MainViewModel
import by.candy.suharnica.android.utils.Colors
import by.candy.suharnica.android.utils.Icons
import by.candy.suharnica.cache.databases.OnBasketMode
import coil.compose.SubcomposeAsyncImage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.math.absoluteValue

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BasketScreen(viewModel: MainViewModel) {
    val basketItems = viewModel.getBasket
        .collectAsState(initial = listOf()).value
    //val totalPrice = basketItems.sumOf { it.priceSale * it.count }
    val totalPrice = viewModel.totalPrice.collectAsState(initial = 0.0)
    val totalWeight = basketItems.sumOf { it.weight * it.count }
    Box(modifier = Modifier.fillMaxSize()) {
        Column() {
            Box(modifier = Modifier.fillMaxWidth()) {
                IconButton(
                    modifier = Modifier.align(Alignment.CenterStart),
                    onClick = { /*TODO*/ }) {
                    Image(
                        painter = painterResource(id = Icons.ArrowBack.image),
                        contentDescription = stringResource(id = Icons.ArrowBack.description.resourceId)
                    )
                }
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(id = MR.strings.basket.resourceId),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .align(Alignment.CenterEnd),
                    text = stringResource(id = MR.strings.choose.resourceId),
                    fontSize = 18.sp,
                )
            }
            Divider(
                thickness = 2.dp,
                color = Color.Black
            )
            Box {
                LazyColumn(
                    modifier = Modifier
                        .padding(12.dp)
                ) {
                    items(items = basketItems,
                        itemContent = { item ->
                            BasketItem(item = item, viewModel)
                        }
                    )
                }
                Divider(
                    modifier = Modifier.align(Alignment.BottomCenter),
                    thickness = 14.dp,
                    color = Color.White
                )
            }

        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, bottom = 70.dp)
                .align(Alignment.BottomCenter),
            backgroundColor = Colors.RedButton.color,
            border = BorderStroke(2.dp, color = Color.Black),
            shape = RoundedCornerShape(8.dp),
            onClick = { }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.padding(start = 15.dp, top = 10.dp, bottom = 10.dp),
                    text = stringResource(id = MR.strings.place_an_order.resourceId),
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Normal
                )
                Column(
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .align(Alignment.CenterEnd)
                ) {
                    Text(
                        text = "$totalPrice BYN",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier.align(Alignment.End),
                        text = "$totalWeight г"
                    )
                }
            }
        }
    }

}

@Composable
fun BasketItem(item: BasketItem, viewModel: MainViewModel) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            SubcomposeAsyncImage(
                modifier = Modifier
                    .size(90.dp)
                    .drawBehind {
                        drawLine(
                            color = Color.Black,
                            Offset(size.width, 0f),
                            Offset(size.width, size.height),
                            10f
                        )
                    },
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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp)
                    .padding(start = 14.dp)
            ) {
                Column() {
                    Text(
                        text = item.label,
                        fontWeight = FontWeight.Black,
                        fontSize = 22.sp,
                        maxLines = 1
                    )

                    Text(
                        text = item.priceSale.absoluteValue.toString().plus(" BYN"),
                        fontSize = 16.sp
                    )
                    Text(
                        text = item.weight.toString().plus(" г"),
                        color = Color.Gray,
                        fontSize = 12.sp
                    )
                }
                Row(
                    modifier = Modifier
                        .align(Alignment.BottomEnd),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        viewModel.addItemIntoBasket(
                            item.id,
                            OnBasketMode.REMOVE
                        )
                    }) {
                        Image(
                            painter = painterResource(id = Icons.Minus.image),
                            contentDescription = stringResource(id = Icons.Minus.description.resourceId)
                        )
                    }
                    Text(
                        modifier = Modifier.widthIn(min = 25.dp),
                        textAlign = TextAlign.Center,
                        text = "${item.count}",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Medium,
                        color = Colors.RedButton.color
                    )
                    IconButton(onClick = {
                        viewModel.addItemIntoBasket(
                            item.id,
                            OnBasketMode.ADD
                        )
                    }) {
                        Image(
                            painter = painterResource(id = Icons.BigPlus.image),
                            contentDescription = stringResource(id = Icons.BigPlus.description.resourceId)
                        )
                    }
                }
            }

        }
        Divider(thickness = 2.dp, color = Color.Black)
    }
}