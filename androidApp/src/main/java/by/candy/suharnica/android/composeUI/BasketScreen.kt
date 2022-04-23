package by.candy.suharnica.android.composeUI

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import by.candy.suharnica.android.BasketItem
import by.candy.suharnica.android.MainViewModel
import coil.compose.SubcomposeAsyncImage

@Composable
fun BasketScreen(viewModel: MainViewModel) {
    val list: List<BasketItem> = viewModel.getBasket()
    LazyColumn() {
        items(items = list,
            itemContent = { item ->
                BasketItem(item = item)
            })
    }
}

@Composable
fun BasketItem(item: BasketItem) {
    Row(modifier = Modifier.fillMaxWidth()) {
        SubcomposeAsyncImage(
            modifier = Modifier
                .height(340.dp)
                .widthIn(min = 200.dp),
            model = item.imgUrl[0],
            loading = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator()
                }
            },
            contentDescription = null,
            //contentScale = ContentScale.Crop
        )
        Divider(modifier = Modifier
            .fillMaxHeight()
            .width(2.dp))
    }
}