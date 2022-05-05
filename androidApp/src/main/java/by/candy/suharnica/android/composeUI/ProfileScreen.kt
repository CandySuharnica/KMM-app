package by.candy.suharnica.android.composeUI

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import by.candy.suharnica.android.utils.Icons
import sqldelight.User


@Composable

fun Profile(user: User, viewModel: MainViewModel) {
    val orderItems = viewModel.getBasket.collectAsState(initial = listOf()).value
    val orderItemsNames: MutableList<String> = mutableListOf();
    for (item in orderItems) {
        orderItemsNames.add(item.label)
    }
    Column(Modifier.scrollable(rememberScrollState(),Orientation.Vertical)
        .fillMaxSize()) {
        TopBar()
        TopCard()
        Spacer(modifier = Modifier.height(10.dp))
        MyFavorites()
        Spacer(modifier = Modifier.height(10.dp))
        //MyOrders()
        Row(Modifier.offset(x=8.dp)){
        Text(
            text = stringResource(id = MR.strings.delivery_profile.resourceId),
        )}
        PopupList(// the same like OurBakeries()
            label = stringResource(id = MR.strings.my_orders_profile.resourceId),
            content = orderItemsNames
        )
        Spacer(modifier = Modifier.height(20.dp))
        PopupList(// the same like OurBakeries()
            label = stringResource(id = MR.strings.our_bakeries_profile.resourceId),
            content = listOf(
                stringResource(id = MR.strings.street_example_profile_1.resourceId)
            )
        )
        Spacer(modifier = Modifier.height(2.dp))
        PopupList(// the same like OurBakeries()
            label = stringResource(id = MR.strings.support_profile.resourceId),
            content = listOf(
                stringResource(id = MR.strings.phone_number_example_profile_1.resourceId)
            )
        )
        //OurBakeries()
        //OurSupport()
    }
}

@Composable
fun TopBar() {
    Box(modifier = Modifier.fillMaxWidth()) {
        IconButton(
            modifier = Modifier.align(Alignment.CenterStart),
            onClick = { /*TODO*/ }) {
            Image(
                painter = painterResource(id = Icons.ArrowBack.image),
                contentDescription = stringResource(id = Icons.ArrowBack.description.resourceId),

                )
        }
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
                    //fontFamily = GolosFontFamily,
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
                //fontFamily = GolosFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
            Text(
                text = stringResource(id = MR.strings.address_example.resourceId),
                //fontFamily = GolosFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
            Divider(color = Color.Black, thickness = 2.dp)
        }
    }
}

/*@Composable
fun someInfo(){
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround
        ){
            Text(text = stringResource(id = MR.strings.bank_card_number_profile),
                //fontFamily = GolosFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp)
            Text(text = stringResource(id = MR.strings.amount_example_xx),
                //fontFamily = GolosFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp)
            IconButton(
                onClick = {  },
                modifier = Modifier
                    .size(31.dp)
            ) {
                Icon(
                    painter = painterResource(Icons.Edit.image),
                    contentDescription = "Edit icon")
            }
        }
    }
}*/

@Composable
fun MyFavorites() {
    Row(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(Icons.Smile.image),
            contentDescription = "smile icon",
            modifier = Modifier.padding(end = 2.dp)
        )
        Column(
            modifier = Modifier
                .padding(bottom = 2.dp)
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = stringResource(id = MR.strings.favorite_desserts.resourceId),
                    //fontFamily = GolosFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                Text(
                    text = stringResource(id = MR.strings.number_count_example.resourceId),
                    //fontFamily = GolosFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
            }
            Divider(color = Color.Black, thickness = 2.dp)
        }
    }
}

@Composable
fun MyOrders() {
    val expanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 8.dp)
    ) {
        Text(
            text = stringResource(id = MR.strings.delivery_profile.resourceId),
            Modifier.padding(start = 2.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painter = painterResource(Icons.Plus.image),
                contentDescription = stringResource(id = MR.strings.plus.resourceId),
                Modifier
                    .padding(end = 2.dp)
                    .clickable { expanded.not() })
            Text(
                text = stringResource(id = MR.strings.my_orders_profile.resourceId),
                //fontFamily = GolosFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
        }
        AnimatedVisibility(visible = expanded) {
            LazyColumn {

            }
        }
    }
}

@Composable
fun OurBakeries() {
    var expanded by remember { mutableStateOf(false) }
    Column(Modifier.scrollable(rememberScrollState(),Orientation.Vertical)) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { expanded = !expanded }) {
                if(!expanded)
                Icon(
                    painter = painterResource(Icons.Plus.image),
                    contentDescription = stringResource(id = MR.strings.plus.resourceId)
                ) else
                    Icon(
                        painter = painterResource(Icons.Plus.image),
                        contentDescription = stringResource(id = MR.strings.minus.resourceId)
                    )
            }
            Text(
                text = stringResource(id = MR.strings.our_bakeries_profile.resourceId),
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
        }
        AnimatedVisibility(expanded) {
            Column {
                Text(
                    text = stringResource(id = MR.strings.street_example_profile_1.resourceId),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
                Text(
                    text = stringResource(id = MR.strings.street_example_profile_1.resourceId),
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Composable
fun OurSupport() {
    var visible by remember { mutableStateOf(false) }
    Column {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(Icons.Plus.image),
                contentDescription = stringResource(id = MR.strings.plus.resourceId),
                Modifier
                    .padding(end = 2.dp)
                    .clickable { visible = !visible }
            )
            Text(
                text = stringResource(id = MR.strings.support_profile.resourceId),
                //fontFamily = GolosFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
        }
        AnimatedVisibility(
            visible = visible,
            enter = expandVertically(),
            exit = shrinkVertically()
        ) {
            Text(text = stringResource(id = MR.strings.phone_number_example_profile_1.resourceId))
        }
    }

}
