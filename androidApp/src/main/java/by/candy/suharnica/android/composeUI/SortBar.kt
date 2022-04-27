package by.candy.suharnica.android.composeUI

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.unit.dp
import by.candy.suharnica.android.MainViewModel

@Composable
fun SortBar(viewModel: MainViewModel) {
    val typesList = viewModel.getTypes.collectAsState(initial = listOf()).value
    Column {
        LazyRow(content = {
            item {
                Card(
                    modifier = Modifier.padding(10.dp),
                    shape = RoundedCornerShape(8.dp),
                    border = BorderStroke(2.dp, Color.Black)
                ) {
                    Row(modifier = Modifier.height(38.dp)) {
                        typesList.forEach {
                            Text(
                                modifier = Modifier.padding(10.dp),
                                text = it
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