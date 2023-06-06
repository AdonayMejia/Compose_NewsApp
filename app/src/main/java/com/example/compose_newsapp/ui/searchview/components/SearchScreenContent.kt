package com.example.compose_newsapp.ui.searchview.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.example.compose_newsapp.R
import com.example.compose_newsapp.model.datamodel.Filter
import com.example.compose_newsapp.model.datamodel.NewsModel
import com.example.compose_newsapp.ui.searchview.model.filtersGenerator
import kotlinx.coroutines.launch

@Composable
fun SearchScreenContent(
    searchNew:(String, Filter) -> Unit,
    articles: LazyPagingItems<NewsModel>,
    onFavClick:(NewsModel) -> Unit,
    navHostController: NavHostController
) {
    val query = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val markedFilter = remember { mutableStateOf(Filter("")) }
    val filters = remember { filtersGenerator() }
    val filtersList = remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()) {
        TextField(
            value = query.value,
            onValueChange = { newValue ->
                query.value = newValue
                scope.launch {
                    searchNew(newValue,markedFilter.value)
                }
            },
            label = { Text("Search") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                scope.launch {
                    searchNew(query.value,markedFilter.value)
                }
            }),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier
                        .clickable {
                            scope.launch {
                                searchNew(query.value,markedFilter.value)
                            }
                        }
                )
            }
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(stringResource(R.string.filters))
            Spacer(modifier = Modifier.width(8.dp))
            Box {
                Button(onClick = { filtersList.value = !filtersList.value }) {
                    Text(markedFilter.value.filterName.ifEmpty { stringResource(R.string.select_a_filter) })
                }
                DropdownMenu(
                    expanded = filtersList.value,
                    onDismissRequest = { filtersList.value = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    filters.forEach { filter ->
                        DropdownMenuItem(onClick = {
                            markedFilter.value = filter
                            filtersList.value = false
                            scope.launch { searchNew(query.value, filter) }

                        }, text = {
                            Text(text = filter.filterName)
                        })
                    }
                }
            }
        }

        LazyColumn {
            items(articles) { article ->
                article?.let { news ->
                    SearchNewsItem(
                        article = news,
                        onFavClick = { onFavClick(news) },
                        navHostController = navHostController
                    )
                }
            }
        }
        when (val state = articles.loadState.refresh) {
            is LoadState.Error -> {
                ErrorToLoadNews()
            }
            is LoadState.Loading -> {
                LoadNews()
            }
            else -> {}
        }

        when (val state = articles.loadState.append) {
            is LoadState.Error -> {
                ErrorToLoadNews()
            }
            is LoadState.Loading -> {
                ErrorPagination()
            }
            else -> {}
        }
    }
}