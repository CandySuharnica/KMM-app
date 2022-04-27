package by.candy.suharnica.android.composeUI.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.candy.suharnica.MR
import by.candy.suharnica.android.utils.Icons


@Composable
fun Profile(){
    Column {
        Divider(color = Color.Black, thickness = 2.dp)
        topCard()
        Divider(color = Color.Black, thickness = 2.dp)
        myFavorites()
        Divider(color = Color.Black, thickness = 2.dp)
        Text(text = stringResource(id = MR.strings.delivery_profile.resourceId))
        myOrders()
        Divider(color = Color.Black, thickness = 2.dp)
        ourBakeries()
    }
}

@Composable
fun topCard(){
    Card(
        shape = RoundedCornerShape(4.dp),
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ){
            Image(
                painter = painterResource(Icons.Profile.image),
                contentDescription = "Profile photo",
                modifier = Modifier
                    .size(width = 100.dp, height = 90.dp)
            )
            Column() {
                Row() {
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
    Row(){
        Icon(painter = painterResource(Icons.Smile.image),
            contentDescription = "smile icon")
        Column() {
            Row() {
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(painter = painterResource(Icons.Plus.image),
                contentDescription = stringResource(id = MR.strings.plus.resourceId),
                modifier = Modifier.weight(1f))
            Text(text = stringResource(id = MR.strings.my_orders_profile.resourceId),
                //fontFamily = GolosFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                modifier = Modifier.weight(4f))
        }
        LazyColumn{

        }
    }
}

@Composable
fun ourBakeries(){

}

@Preview(showBackground = true)
@Composable
fun PreviewProfile(){
    Profile()
}

