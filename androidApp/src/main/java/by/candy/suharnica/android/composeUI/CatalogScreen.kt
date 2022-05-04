package by.candy.suharnica.android.composeUI

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import by.candy.suharnica.android.MainViewModel
import by.candy.suharnica.android.composeUI.common.SearchBar
import by.candy.suharnica.android.composeUI.common.SortBar
import by.candy.suharnica.android.composeUI.items.CatalogItem


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CatalogScreen(viewModel: MainViewModel, navController: NavController) {
    val sortFlow = viewModel.sortFlow.collectAsState().value
    val initialList = listOf("Все", "Любимое")
    val sortTypes = viewModel.getTypes.collectAsState(initial = listOf()).value
    val finalListTypes = initialList + sortTypes
    val searchFlow = viewModel.searchFlow.collectAsState().value
    val catalogItems = viewModel.catalogList(sortFlow, searchFlow)
        .collectAsState(initial = listOf()).value
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        SearchBar(text = searchFlow){
            viewModel.searchFlow.value = it
        }
        SortBar(
            content = finalListTypes,
            sortMode = sortFlow,
        ) {
            viewModel.sortFlow.value = it
        }
        Box() {
            LazyVerticalGrid(
                modifier = Modifier.padding(bottom = 59.dp),
                cells = GridCells.Fixed(2)
            ) {
                items(
                    items = catalogItems,
                    itemContent = {
                        CatalogItem(item = it, navController, viewModel)
                    }
                )
            }
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(8.dp)
                    .align(Alignment.CenterStart),
                color = Color.White
            )
            Divider(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(8.dp)
                    .align(Alignment.CenterEnd),
                color = Color.White
            )
        }
    }
}