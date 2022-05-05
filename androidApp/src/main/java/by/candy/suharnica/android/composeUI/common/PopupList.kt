package by.candy.suharnica.android.composeUI.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
    content: @Composable () -> Unit
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
                if(!expanded)
                    Icon(
                        painter = painterResource(Icons.Plus.image),
                        contentDescription = stringResource(id = Icons.Plus.description.resourceId),
                        Modifier.padding(end = 2.dp)
                            .clickable { expanded = !expanded }
                    ) else
                    Icon(
                        painter = painterResource(Icons.SmallMinus.image),
                        contentDescription = stringResource(id = Icons.SmallMinus.description.resourceId),
                        Modifier.padding(end = 2.dp)
                            .clickable { expanded = !expanded }
                    )
            Text(
                text = label,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            )
        }
        AnimatedVisibility(expanded) {
            content()
        }
    }
}