package by.candy.suharnica.android.composeUI

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import by.candy.suharnica.android.MainViewModel
import by.candy.suharnica.android.utils.Colors
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun SortBar(viewModel: MainViewModel) {
    val initialList = listOf("Все", "Любимое")
    val typesList = viewModel.getTypes.collectAsState(initial = listOf()).value
    val finalList = initialList + typesList
    val sortMode = viewModel.sortFlow.collectAsState().value

    Column {
        LazyRow(content = {
            item {
                Card(
                    modifier = Modifier.padding(10.dp),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(2.dp, Color.Black)
                ) {
                    Row(modifier = Modifier.height(38.dp)) {
                        finalList.forEach {
                            Text(
                                modifier = Modifier
                                    .background(if (sortMode == it) Colors.RedButton.color
                                                else Color.White)
                                    .padding(10.dp)
                                    .clickable {
                                        viewModel.sortFlow.value = it
                                    },
                                text = it,
                                color = if (sortMode == it) Color.Black else Color.Gray
                            )
                            Divider(
                                modifier = Modifier
                                    .height(38.dp)
                                    .width(2.dp),
                                color = Color.Black
                            )
                        }
                    }
                }
            }
        })
        Divider(
            color = Color.Black,
            thickness = 2.dp
        )
    }

}