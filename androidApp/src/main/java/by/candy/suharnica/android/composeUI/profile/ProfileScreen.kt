package by.candy.suharnica.android.composeUI.profile

import androidx.compose.animation.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.candy.suharnica.MR
import by.candy.suharnica.android.MainViewModel
import by.candy.suharnica.android.utils.Icons
import com.google.firebase.auth.FirebaseUser
import sqldelight.User


@Composable

fun Profile(user: User){
    //val orderItems = viewModel.getBasket.collectAsState(initial = listOf()).value
    Column {
        topBar()
        topCard()
        Spacer(modifier = Modifier.height(10.dp))
        myFavorites()
        Spacer(modifier = Modifier.height(10.dp))
        myOrders()
        Spacer(modifier = Modifier.height(20.dp))
        ourBakeries()
        ourSupport()
    }
}

@Composable
fun topBar() {
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
fun topCard(){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, end = 8.dp)
        ){
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
                    Text(text = stringResource(id = MR.strings.full_name_example.resourceId),
                        //fontFamily = GolosFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp
                    )
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
                Text(text = stringResource(id = MR.strings.phone_number_example.resourceId),
                    //fontFamily = GolosFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
                Text(text = stringResource(id = MR.strings.address_example.resourceId),
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
fun myFavorites() {
    Row(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .fillMaxWidth()
    ){
        Icon(painter = painterResource(Icons.Smile.image),
            contentDescription = "smile icon",
            modifier = Modifier.padding(end = 2.dp))
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
                Text(text = stringResource(id = MR.strings.favorite_desserts.resourceId),
                    //fontFamily = GolosFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp)
                Text(text = stringResource(id = MR.strings.number_count_example.resourceId),
                    //fontFamily = GolosFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp)
            }
            Divider(color = Color.Black, thickness = 2.dp)
        }
    }
}

@Composable
fun myOrders(){
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 8.dp)
    ) {
        Text(text = stringResource(id = MR.strings.delivery_profile.resourceId),
        Modifier.padding(start = 2.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painter = painterResource(Icons.Plus.image),
                contentDescription = stringResource(id = MR.strings.plus.resourceId),
                Modifier.padding(end = 2.dp)
                    .clickable { expanded.not() })
            Text(text = stringResource(id = MR.strings.my_orders_profile.resourceId),
                //fontFamily = GolosFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp)
        }
        AnimatedVisibility(visible = expanded) {
            LazyColumn{

            }
        }
    }
}

@Composable
fun ourBakeries(){
        var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painter = painterResource(Icons.Plus.image),
                contentDescription = stringResource(id = MR.strings.plus.resourceId),
                Modifier
                    .padding(end = 2.dp)
                    .clickable { expanded != expanded })
            Text(text = stringResource(id = MR.strings.our_bakeries_profile.resourceId),
                //fontFamily = GolosFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp)
        }
        if(expanded) {
            Text(text = stringResource(id = MR.strings.street_example_profile_1.resourceId),
                //fontFamily = GolosFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp)
        }
    }
}

@Composable
fun ourSupport() {
    var visible by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, start = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(Icons.Plus.image),
                contentDescription = stringResource(id = MR.strings.plus.resourceId),
                Modifier.padding(end = 2.dp)
                    .clickable { visible != visible }
            )
            Text(
                text = stringResource(id = MR.strings.support_profile.resourceId),
                //fontFamily = GolosFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
        }
        AnimatedVisibility(visible = visible,
            enter = expandVertically(),
            exit = shrinkVertically()) {
            Text(text = stringResource(id = MR.strings.phone_number_example_profile_1.resourceId))
        }
    }
}
