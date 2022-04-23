package by.candy.suharnica.android.composeUI

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
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
import by.candy.suharnica.android.utils.Icons
import coil.compose.SubcomposeAsyncImage

@Composable
fun DetailScreen(viewModel: MainViewModel, itemId: Long) {
    val item = viewModel.getItemFromId(itemId)
    Box(modifier = Modifier.fillMaxHeight()) {
        LazyColumn {
            item() {
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
                                //contentScale = ContentScale.Crop
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
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = stringResource(id = MR.strings.composition.resourceId),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = item.productComposition.toString(),
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
                            text = item.nutritionalValue.toString(),
                            fontSize = 12.sp
                        )
                    }
                }

            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, bottom = 70.dp)
                .background(color = Color.Red)
                .border(2.dp, color = Color.Black, shape = RoundedCornerShape(20.dp))
                .align(Alignment.BottomCenter)
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
                    .padding(end = 8.dp)
                    .align(Alignment.CenterEnd),
                painter = painterResource(id = Icons.BigPlus.image),
                contentDescription = stringResource(id = Icons.BigPlus.description.resourceId)
            )
        }
    }
}

