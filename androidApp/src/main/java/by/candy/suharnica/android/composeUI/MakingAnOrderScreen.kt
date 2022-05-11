package by.candy.suharnica.android.composeUI

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import by.candy.suharnica.android.utils.Colors
import by.candy.suharnica.android.utils.Icons

@Composable
fun MakingAnOrderScreen(viewModel: MainViewModel) {
    val basketItems = viewModel.getBasket
        .collectAsState(initial = listOf()).value
    val totalPrice = basketItems.sumOf { it.priceSale * it.count }
    val totalWeight = basketItems.sumOf { it.weight * it.count }
    var pickUpStatus by remember { mutableStateOf(false)} //для определения статуса (самовывоз или доставка)
    var courierStatus by remember { mutableStateOf(false)}//для определения статуса (самовывоз или доставка)
    val name = remember { mutableStateOf("") }
    val secondName = remember { mutableStateOf("") }
    val mobileNumber = remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Text(modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = MR.strings.making_an_order_title.resourceId),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(8.dp))
        Divider(
            thickness = 2.dp,
            color = Color.Black
        )
        Column(modifier = Modifier.padding(start = 8.dp, end = 8.dp))
        {
            Text(text = stringResource(id = MR.strings.mao_name.resourceId))
            OutlinedTextField(
                value = name.value,
                onValueChange = {
                    name.value = it
                },
                label = { Text("Иван") },
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
            )
            Text(text = stringResource(id = MR.strings.mao_second_name.resourceId))
            OutlinedTextField(
                value = secondName.value,
                onValueChange = {
                    secondName.value = it
                },
                label = { Text("Иванов") },
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth()
            )
            Text(text = stringResource(id = MR.strings.mao_mobile_number.resourceId))
            OutlinedTextField(
                value = mobileNumber.value,
                onValueChange = {
                    mobileNumber.value = it
                },
                label = { Text("+375__ ___-__-__") },
                modifier = Modifier
                    .height(60.dp)
                    .fillMaxWidth(),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor= Colors.RedButton.color, // цвет при получении фокуса
                        focusedLabelColor = Colors.RedButton.color
            )
            )
            Spacer(Modifier.height(8.dp))

            Text(text = stringResource(id = MR.strings.mao_method_of_issue.resourceId),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray)

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (!pickUpStatus) {
                    if (!courierStatus)
                    Icon(
                        painter = painterResource(Icons.Plus.image),
                        contentDescription = stringResource(id = Icons.Plus.description.resourceId),
                        Modifier
                            .padding(end = 2.dp)
                            .clickable { courierStatus = !courierStatus }
                    ) else
                    Icon(
                        painter = painterResource(Icons.Smile.image),
                        contentDescription = stringResource(id = Icons.SmallMinus.description.resourceId),
                        Modifier
                            .padding(end = 2.dp)
                            .clickable { courierStatus = !courierStatus }
                    )
                    Text(
                        text = stringResource(id = MR.strings.mao_delivery_by_courier.resourceId),
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    )
                }
            }

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically)
            {
                if (!courierStatus) {
                    if (!pickUpStatus)
                    Icon(
                        painter = painterResource(Icons.Plus.image),
                        contentDescription = stringResource(id = Icons.Plus.description.resourceId),
                        Modifier
                            .padding(end = 2.dp)
                            .clickable { pickUpStatus = !pickUpStatus }
                    ) else
                    Icon(
                        painter = painterResource(Icons.Smile.image),
                        contentDescription = stringResource(id = Icons.SmallMinus.description.resourceId),
                        Modifier
                            .padding(end = 2.dp)
                            .clickable { pickUpStatus = !pickUpStatus }
                    )
                    Text(
                        text = stringResource(id = MR.strings.mao_pick_up.resourceId),
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    )
                    AnimatedVisibility(pickUpStatus) {
                        Text(text = stringResource(id = MR.strings.mao_address_pick_up.resourceId))
                    }
                }
            }
        }
        Box(Modifier.fillMaxSize()) {
        RedButton(modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, bottom = 70.dp)
            .align(Alignment.BottomCenter),
            text = stringResource(id = MR.strings.place_an_order.resourceId),
            price = totalPrice,
            weight = totalWeight)
        }
    }
}