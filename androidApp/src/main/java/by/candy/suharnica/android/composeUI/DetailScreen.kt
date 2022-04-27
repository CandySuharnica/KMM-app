package by.candy.suharnica.android.composeUI

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import by.candy.suharnica.MR
import by.candy.suharnica.android.MainViewModel
import by.candy.suharnica.android.utils.Colors
import by.candy.suharnica.android.utils.Icons
import by.candy.suharnica.cache.databases.OnBasketMode
import coil.compose.SubcomposeAsyncImage

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailScreen(viewModel: MainViewModel, navController: NavController, itemId: Long) {
    val item = viewModel.getItemFromId(itemId)
    val count = viewModel.getItemCountInBasket(item.id)
        .collectAsState(initial = 0).value
    Box(modifier = Modifier.fillMaxHeight()) {
        LazyColumn {
            item() {
                Box() {
                    LazyRow {
                        items(item.imgUrl.size) { index ->
                            Row() {
                                SubcomposeAsyncImage(
                                    modifier = Modifier
                                        .height(340.dp)
                                        .widthIn(min = 200.dp),
                                    model = item.imgUrl[index],
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
                                Divider(
                                    modifier = Modifier
                                        .height(340.dp)
                                        .width(2.dp),
                                    color = Color.Black,
                                    thickness = 2.dp
                                )
                            }
                        }
                    }
                    IconButton(
                        modifier = Modifier.align(Alignment.TopStart),
                        onClick = { navController.navigateUp()}) {
                        Image(
                            painter = painterResource(id = Icons.ArrowBack.image),
                            contentDescription = stringResource(id = Icons.ArrowBack.description.resourceId)
                        )
                    }
                }
                Divider(
                    color = Color.Black,
                    thickness = 2.dp
                )
                Box(
                    modifier = Modifier
                        .height(70.dp)
                        .fillMaxWidth()
                        .padding(start = 18.dp, end = 18.dp, top = 13.dp)
                ) {
                    Text(
                        modifier = Modifier.align(Alignment.TopStart),
                        text = item.label,
                        fontSize = 22.sp,
                    )
                    Row(modifier = Modifier.align(Alignment.BottomStart)) {
                        Text(
                            text = item.price.toString().plus(" BYN"),
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 10.dp, bottom = 2.dp)
                                .align(Alignment.Bottom),
                            text = item.weight.toString().plus(" г"),
                            fontSize = 16.sp,
                            color = Color.Gray
                        )
                    }
                    Row(
                        modifier = Modifier.align(Alignment.BottomEnd),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            modifier = Modifier.size(28.dp),
                            painter = painterResource(id = Icons.BigSmile.image),
                            contentDescription = stringResource(
                                id = Icons.BigSmile.description.resourceId
                            )
                        )
                        Icon(
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .size(28.dp),
                            painter = painterResource(id = Icons.Share.image),
                            contentDescription = stringResource(
                                id = Icons.Share.description.resourceId
                            )
                        )
                    }

                }
                Divider(
                    modifier = Modifier.padding(12.dp),
                    color = Color.Black,
                    thickness = 2.dp
                )
                Text(
                    modifier = Modifier.padding(horizontal = 18.dp),
                    text = item.about,
                    fontSize = 16.sp
                )
                Row(
                    modifier = Modifier
                        .padding(start = 12.dp, end = 12.dp, top = 12.dp, bottom = 150.dp)
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp)
                    ) {
                        Text(
                            text = stringResource(id = MR.strings.composition.resourceId),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = item.productComposition,
                            fontSize = 12.sp
                        )
                    }
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 4.dp)
                    ) {
                        Text(
                            text = stringResource(id = MR.strings.nutrition_facts.resourceId),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = item.toString(),
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
        if (count == 0)
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp, bottom = 70.dp)
                    .align(Alignment.BottomCenter),
                backgroundColor = Colors.RedButton.color,
                border = BorderStroke(2.dp, color = Color.Black),
                shape = RoundedCornerShape(8.dp),
                onClick = { viewModel.addItemIntoBasket(itemId, OnBasketMode.ADD) }
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterStart),
                        text = stringResource(id = MR.strings.add_to_basket.resourceId),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Icon(
                        modifier = Modifier
                            .padding(end = 15.dp)
                            .align(Alignment.CenterEnd),
                        painter = painterResource(id = Icons.BigPlus.image),
                        contentDescription = stringResource(id = Icons.BigPlus.description.resourceId)
                    )
                }

            }
        else
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, end = 12.dp, bottom = 70.dp)
                    .align(Alignment.BottomCenter),
                border = BorderStroke(2.dp, color = Color.Black),
                shape = RoundedCornerShape(8.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    Text(
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.CenterStart),
                        text = stringResource(id = MR.strings.add_to_basket.resourceId),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Row(
                        modifier = Modifier
                            .padding(end = 25.dp)
                            .align(Alignment.CenterEnd),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = {
                            viewModel.addItemIntoBasket(
                                itemId,
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
                            text = "$count",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Medium,
                            color = Colors.RedButton.color
                        )
                        IconButton(onClick = {
                            viewModel.addItemIntoBasket(
                                itemId,
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

    }
}


