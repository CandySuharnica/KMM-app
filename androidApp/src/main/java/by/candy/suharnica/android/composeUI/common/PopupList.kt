package by.candy.suharnica.android.composeUI.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import by.candy.suharnica.MR
import by.candy.suharnica.android.utils.Icons

@Composable
fun PopupList(
    label: String,
    content: List<String>
) {
    var expanded by remember { mutableStateOf(false) }
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    painter = painterResource(Icons.Plus.image),
                    contentDescription = stringResource(id = MR.strings.plus.resourceId)
                )
            }
            Text(
                text = label,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
        }
        AnimatedVisibility(expanded) {
            Column {
                content.forEach {
                    Text(
                        text = it,
                        fontWeight = FontWeight.Normal,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}