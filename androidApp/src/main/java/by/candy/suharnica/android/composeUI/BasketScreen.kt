package by.candy.suharnica.android.composeUI

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.candy.suharnica.MR
import by.candy.suharnica.android.MainViewModel
import by.candy.suharnica.android.composeUI.common.RedButton
import by.candy.suharnica.android.composeUI.items.BasketItem
import by.candy.suharnica.android.utils.Icons

@SuppressLint("StateFlowValueCalledInComposition")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BasketScreen(viewModel: MainViewModel) {
    val basketItems = viewModel.getBasket
        .collectAsState(initial = listOf()).value
    val totalPrice = basketItems.sumOf { it.priceSale * it.count }
    val totalWeight = basketItems.sumOf { it.weight * it.count }
    Box(modifier = Modifier.fillMaxSize()) {
        Column() {
            Box(modifier = Modifier.fillMaxWidth()) {
                /*IconButton(
                    modifier = Modifier.align(Alignment.CenterStart),
                    onClick = { *//*TODO*//* }) {
                    Image(
                        painter = painterResource(id = Icons.ArrowBack.image),
                        contentDescription = stringResource(id = Icons.ArrowBack.description.resourceId)
                    )
                }*/
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = stringResource(id = MR.strings.basket.resourceId),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
                /*Text(
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .align(Alignment.CenterEnd),
                    text = stringResource(id = MR.strings.choose.resourceId),
                    fontSize = 18.sp,
                )*/
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
                            BasketItem(
                                item = item,
                                onClickAdd = {

                                },
                                onClickRemove = {

                                },
                                viewModel)
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
        RedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, bottom = 70.dp)
                .align(Alignment.BottomCenter),
            text = stringResource(id = MR.strings.place_an_order.resourceId),
            price = totalPrice,
            weight = totalWeight
           // onClickButton = viewModel.createCheck
        )
    }

}