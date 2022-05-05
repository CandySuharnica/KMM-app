package by.candy.suharnica.android.composeUI.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import by.candy.suharnica.MR
import by.candy.suharnica.android.utils.Icons

@Composable
fun PopupList(
    label: String,
    content: List<String>
) {
    var expanded by remember { mutableStateOf(false) }
    Column(Modifier
            .padding(start = 8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            //IconButton(onClick = { expanded = !expanded }) {
                if(!expanded || content.isEmpty())
                    Icon(
                        painter = painterResource(Icons.Plus.image),
                        contentDescription = stringResource(id = MR.strings.plus.resourceId),
                        Modifier.padding(end = 2.dp)
                            .clickable { expanded = !expanded }
                    ) else
                    Icon(
                        painter = painterResource(Icons.Minus.image),
                        contentDescription = stringResource(id = MR.strings.minus.resourceId),
                        Modifier.padding(end = 2.dp)
                            .clickable { expanded = !expanded }
                    )
            //}
            Text(
                text = label,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
        }
        AnimatedVisibility(expanded) {
            Column(
                Modifier.padding(start = 8.dp)
            ) {
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