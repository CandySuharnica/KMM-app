package by.candy.suharnica.android.composeUI

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.candy.suharnica.MR
import by.candy.suharnica.android.MainViewModel
import by.candy.suharnica.android.composeUI.common.PopupList
import by.candy.suharnica.android.composeUI.items.CatalogItem
import by.candy.suharnica.android.utils.Icons
import by.candy.suharnica.android.utils.NavGraph
import by.candy.suharnica.cache.databases.OnBasketMode
import sqldelight.GetLikes


@OptIn(ExperimentalFoundationApi::class)
@Composable

fun Profile(viewModel: MainViewModel) {
    val orderItems = viewModel.getBasket.collectAsState(initial = listOf()).value
    val orderItemsNames: MutableList<String> = mutableListOf()
    val listOfLikes =
        viewModel.listOfLikes.collectAsState(initial = GetLikes(listOf())).value?.likes ?: listOf()
    for (item in orderItems) {
        orderItemsNames.add(item.label)
    }
    Column(
        Modifier
            .scrollable(rememberScrollState(), Orientation.Vertical)
            .fillMaxSize()
    ) {
        TopBar()
        TopCard()
        Spacer(modifier = Modifier.height(20.dp))
        PopupList(
            label = stringResource(id = MR.strings.my_orders_profile.resourceId),
            content = {
                LazyVerticalGrid(
                    cells = GridCells.Fixed(2)
                ) {
                    items(
                        items = listOfLikes,
                        itemContent = {
                            CatalogItem(
                                item = viewModel.getItemFromId(it))
                        }
                    )
                }
            }
        )
        Spacer(modifier = Modifier.height(2.dp))
        PopupList(
            label = stringResource(id = MR.strings.our_bakeries_profile.resourceId),
            content = {
                Text(text = stringResource(id = MR.strings.street_example_profile_1.resourceId))
            }

        )
        Spacer(modifier = Modifier.height(2.dp))
        PopupList(
            label = stringResource(id = MR.strings.support_profile.resourceId),
            content = {
                Text(text = stringResource(id = MR.strings.phone_number_example_profile_1.resourceId))
            }
        )
    }
}

@Composable
fun TopBar() {
    Box(modifier = Modifier.fillMaxWidth()) {
        /*IconButton(
            modifier = Modifier.align(Alignment.CenterStart),
            onClick = {  }) {
            Image(
                painter = painterResource(id = Icons.ArrowBack.image),
                contentDescription = stringResource(id = Icons.ArrowBack.description.resourceId),

                )
        }*/
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = stringResource(id = MR.strings.profile_title.resourceId),
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
    Divider(
        thickness = 2.dp,
        color = Color.Black
    )
}

@Composable
fun TopCard() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, end = 8.dp)
    ) {
        Image(
            painter = painterResource(Icons.Profile.image),
            contentDescription = "Profile photo",
            modifier = Modifier
                .size(width = 90.dp, height = 80.dp)
                .padding(start = 8.dp)
                .border(2.dp, Color.Black, shape = RectangleShape)
        )
        Column(
            modifier = Modifier
                .height(100.dp)
                .fillMaxHeight()
                .padding(start = 8.dp)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = MR.strings.full_name_example.resourceId),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp
                )
                IconButton(
                    onClick = { },
                    modifier = Modifier
                        .size(31.dp)
                ) {
                    Icon(
                        painter = painterResource(Icons.Edit.image),
                        contentDescription = "Edit icon"
                    )
                }
            }
            Text(
                text = stringResource(id = MR.strings.phone_number_example.resourceId),
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
            Text(
                text = stringResource(id = MR.strings.address_example.resourceId),
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
            Divider(color = Color.Black, thickness = 2.dp)
        }
    }
}
