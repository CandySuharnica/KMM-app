package by.candy.suharnica.android.composeUI

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.ExposedDropdownMenuDefaults.outlinedTextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.candy.suharnica.android.MainViewModel
import by.candy.suharnica.android.utils.Colors
import by.candy.suharnica.entity.CatalogItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AdminScreen(viewModel: MainViewModel) {
    val scrollState = rememberScrollState()
    val id = remember { mutableStateOf("") }
    val label = remember { mutableStateOf("") }
    val type = remember { mutableStateOf("") }
    val weight = remember { mutableStateOf("") }
    val imgURL = remember { mutableStateOf("") }
    val price = remember { mutableStateOf("") }
    val priceSale = remember { mutableStateOf("") }
    val about = remember { mutableStateOf("") }
    val calorie = remember { mutableStateOf("") }
    val carbohydrates = remember { mutableStateOf("") }
    val fats = remember { mutableStateOf("") }
    val protein = remember { mutableStateOf("") }
    val productComposition = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                scrollState,
                true
            )
            /*.scrollable(
                state = scrollState,
                orientation = Orientation.Vertical
            )*/
            .padding(start = 8.dp)
    ) {

        Text(
            text = "Добавление товара",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Divider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 2.dp,
            color = Color.Black
        )
        OutlinedTextField(
            value = id.value,
            onValueChange = { id.value = it },
            label = { Text(text = "Item id") },
            colors = outlinedTextFieldColors(
                focusedBorderColor = Colors.RedButton.color,
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black
            )
        )
        OutlinedTextField(
            value = label.value,
            onValueChange = { label.value = it },
            label = { Text(text = "Item label") },
            colors = outlinedTextFieldColors(
                focusedBorderColor = Colors.RedButton.color,
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black
            )
        )
        OutlinedTextField(
            value = type.value,
            onValueChange = { type.value = it },
            label = { Text(text = "Item type") },
            colors = outlinedTextFieldColors(
                focusedBorderColor = Colors.RedButton.color,
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black
            )
        )
        OutlinedTextField(
            value = weight.value,
            onValueChange = { weight.value = it },
            label = { Text(text = "Item weight") },
            colors = outlinedTextFieldColors(
                focusedBorderColor = Colors.RedButton.color,
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black
            )
        )
        OutlinedTextField(
            value = imgURL.value,
            onValueChange = { imgURL.value = it },
            label = { Text(text = "Item imgURL") },
            colors = outlinedTextFieldColors(
                focusedBorderColor = Colors.RedButton.color,
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black
            )
        )
        OutlinedTextField(
            value = price.value,
            onValueChange = { price.value = it },
            label = { Text(text = "Item price") },
            colors = outlinedTextFieldColors(
                focusedBorderColor = Colors.RedButton.color,
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black
            )
        )
        OutlinedTextField(
            value = priceSale.value,
            onValueChange = { priceSale.value = it },
            label = { Text(text = "Item priceSale") },
            colors = outlinedTextFieldColors(
                focusedBorderColor = Colors.RedButton.color,
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black
            )
        )
        OutlinedTextField(
            value = about.value,
            onValueChange = { about.value = it },
            label = { Text(text = "Item about") },
            colors = outlinedTextFieldColors(
                focusedBorderColor = Colors.RedButton.color,
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black
            )
        )
        OutlinedTextField(
            value = calorie.value,
            onValueChange = { calorie.value = it },
            label = { Text(text = "Item calorie") },
            colors = outlinedTextFieldColors(
                focusedBorderColor = Colors.RedButton.color,
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black
            )
        )
        OutlinedTextField(
            value = carbohydrates.value,
            onValueChange = { carbohydrates.value = it },
            label = { Text(text = "Item carbohydrates") },
            colors = outlinedTextFieldColors(
                focusedBorderColor = Colors.RedButton.color,
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black
            )
        )
        OutlinedTextField(
            value = fats.value,
            onValueChange = { fats.value = it },
            label = { Text(text = "Item fats") },
            colors = outlinedTextFieldColors(
                focusedBorderColor = Colors.RedButton.color,
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black
            )
        )
        OutlinedTextField(
            value = protein.value,
            onValueChange = { protein.value = it },
            label = { Text(text = "Item protein") },
            colors = outlinedTextFieldColors(
                focusedBorderColor = Colors.RedButton.color,
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black
            )
        )
        OutlinedTextField(
            value = productComposition.value,
            onValueChange = { productComposition.value = it },
            label = { Text(text = "Item Product Composition") },
            colors = outlinedTextFieldColors(
                focusedBorderColor = Colors.RedButton.color,
                unfocusedBorderColor = Color.Black,
                focusedLabelColor = Color.Black
            )
        )
        Button(
            onClick = {
                if (id.value == "" && label.value == "" && type.value == "" && weight.value == "" && imgURL.value == "" && price.value == "" && priceSale.value == "" && about.value == "") CoroutineScope(
                    Dispatchers.Default
                ).launch { viewModel.errorHandler.emit("some fields is empty") }
                else
                viewModel.addToCatalog(
                    CatalogItem(
                        id.value.toLong() ?: 0,
                        label.value,
                        type.value,
                        weight.value.toInt() ?: 0,
                        listOf(imgURL.value),
                        price.value.toDouble() ?: 0.0,
                        priceSale.value.toDouble() ?: 0.0,
                        0,
                        about.value,
                        productComposition.value,
                        calorie.value,
                        carbohydrates.value,
                        fats.value,
                        protein.value
                    )
                )
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Colors.RedButton.color)
        ) {
            Text(text = "Добавить товар")
        }

        Spacer(modifier = Modifier.height(80.dp))
    }
}
