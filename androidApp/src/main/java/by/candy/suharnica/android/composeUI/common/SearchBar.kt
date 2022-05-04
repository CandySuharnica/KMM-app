package by.candy.suharnica.android.composeUI

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.candy.suharnica.MR
import by.candy.suharnica.android.MainViewModel
import by.candy.suharnica.android.utils.Icons
import dev.icerock.moko.resources.desc.desc


@Composable
fun SearchBar(viewModel: MainViewModel) {
    val text = viewModel.searchFlow.collectAsState().value
    val textFlow = viewModel.searchFlow
    Column() {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(
                modifier = Modifier
                    .weight(1f)
                    .height(40.dp),
                shape = RoundedCornerShape(4.dp),
                border = BorderStroke(2.dp, Color.Black)
            ) {
                Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = Icons.Search.image),
                        contentDescription = stringResource(id = Icons.Search.description.resourceId)
                    )
                    BasicTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 12.dp),
                        value = text,
                        onValueChange = { textFlow.value = it },
                        decorationBox = { innerTextField ->
                            if (text.isEmpty()) {
                                Text(
                                    modifier = Modifier.alpha(0.5f),
                                    text = stringResource(id = MR.strings.search.resourceId)
                                )
                            }
                            innerTextField()
                        }
                    )

                }
            }
            Icon(
                modifier = Modifier
                    .padding(start = 14.dp)
                    .size(40.dp),
                painter = painterResource(id = Icons.Sort.image),
                contentDescription = stringResource(id = Icons.Sort.description.resourceId)
            )
        }
        Divider(color = Color.Black, thickness = 2.dp)
    }
}
