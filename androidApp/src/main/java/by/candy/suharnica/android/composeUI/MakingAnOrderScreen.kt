package by.candy.suharnica.android.composeUI

import android.Manifest
import android.content.pm.PackageManager
import android.os.Environment
import android.util.Log
import android.util.TimeUtils
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import by.candy.suharnica.MR
import by.candy.suharnica.android.MainViewModel
import by.candy.suharnica.android.composeUI.common.RedButton
import by.candy.suharnica.android.utils.Colors
import by.candy.suharnica.android.utils.Icons
import com.itextpdf.text.Document
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import java.io.FileOutputStream
import java.lang.Exception
import java.util.*
import kotlin.random.Random

@Composable
fun MakingAnOrderScreen(viewModel: MainViewModel) {
    val basketItems = viewModel.getBasket
        .collectAsState(initial = listOf()).value
    val totalPrice = basketItems.sumOf { it.priceSale * it.count }
    val totalWeight = basketItems.sumOf { it.weight * it.count }
    var pickUpStatus by remember { mutableStateOf(false) } //для определения статуса (самовывоз или доставка)
    var courierStatus by remember { mutableStateOf(false) }//для определения статуса (самовывоз или доставка)
    val name = remember { mutableStateOf("") }
    val secondName = remember { mutableStateOf("") }
    val mobileNumber = remember { mutableStateOf("") }
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            // Permission Accepted: Do something
            Log.d("ExampleScreen", "PERMISSION GRANTED")

        } else {
            // Permission Denied: Do something
            Log.d("ExampleScreen", "PERMISSION DENIED")
        }
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = stringResource(id = MR.strings.making_an_order_title.resourceId),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
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
                    focusedBorderColor = Colors.RedButton.color, // цвет при получении фокуса
                    focusedLabelColor = Colors.RedButton.color
                )
            )
            Spacer(Modifier.height(8.dp))

            Text(
                text = stringResource(id = MR.strings.mao_method_of_issue.resourceId),
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )

            Row(
                modifier = Modifier
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

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            )
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
        val context = LocalContext.current
        Box(Modifier.fillMaxSize()) {
            RedButton(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 12.dp, end = 12.dp, bottom = 70.dp)
                .align(Alignment.BottomCenter),
                text = stringResource(id = MR.strings.place_an_order.resourceId),
                price = totalPrice,
                weight = totalWeight,
                onClickButton = {
                    when (PackageManager.PERMISSION_GRANTED) {
                        ContextCompat.checkSelfPermission(
                            context,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                        ) -> {
                            val doc = Document()
                            val fileName = "check" + Random.nextInt(1,100)
                            val filePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString() + "/" + fileName + ".pdf"
                            try {
                                PdfWriter.getInstance(doc, FileOutputStream(filePath))
                                doc.open()
                                var orderData = ""
                                for(item in basketItems)
                                    orderData+="${item.priceSale}\n"
                                val data = "Order payment receipt for the amount $totalPrice\n" +
                                        "Weight - $totalWeight\n" +
                                        orderData +
                                        "Buyer - ${name.value} ${secondName.value}\n" +
                                        "Seller - Ivan Ivanov\n" +
                                        "Director - Aleksey Trofimovich\n" +
                                        "In the near future, you will be contacted by the number ${mobileNumber.value}\n" +
                                        "Delivery by courier: $courierStatus\n" +
                                        "Pick up: $pickUpStatus\n"
                                doc.addAuthor("admin")
                                doc.add(Paragraph(data))
                                doc.close()
                                Toast.makeText(context,"Good",Toast.LENGTH_LONG).show()
                                Log.d("ExampleScreen", "PERMISSION GRANTED")
                            } catch (e: Exception) {

                            }
                        }
                        else -> {
                            // Asking for permission
                            launcher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        }
                    }
                })
        }

    }
}