package com.example.compose_newsapp.ui.searchview

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import androidx.paging.compose.items
import coil.compose.rememberAsyncImagePainter
import com.example.compose_newsapp.model.datamodel.NewsModel
import com.example.compose_newsapp.model.network.ApiServiceClient
import com.example.compose_newsapp.ui.searchview.uistate.SearchUiState
import com.example.compose_newsapp.ui.searchview.viewmodel.SearchViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun SearchScreen(
    searchViewModel:SearchViewModel
) {
    val uiState by searchViewModel.uiState.collectAsState()
    val articles = searchViewModel.articlesFlow.collectAsLazyPagingItems()
    LaunchedEffect(Unit){

    }
    SearchScreenContent(
        searchNew = uiState.onQueryChange,
        isLoading = uiState.isLoading,
        articles = articles
    )
}

@Composable
fun SearchScreenContent(
    searchNew:(String) -> Unit,
    isLoading:Boolean,
    articles:LazyPagingItems<NewsModel>
) {

//    var searchQuery = remember { mutableStateOf("") }
//    val articles = uiState.articles
//    val isLoading =
//    val scope = rememberCoroutineScope()
//    val newsPaginatedItemsProvider by uiState.newsPaginatedItemsProvider.collectAsState()
//    val newsPaginatedItems = newsPaginatedItemsProvider?.collectAsLazyPagingItems()

    val query = remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        TextField(
            value = query.value,
            onValueChange = { newValue -> query.value = newValue },
            label = { Text("Search") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = androidx.compose.ui.text.input.ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                coroutineScope.launch {
                    searchNew(query.value)
                }
            }),
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier
                        .clickable {
                            coroutineScope.launch {
                                searchNew(query.value)
                            }
                        }
                )
            }
        )
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.wrapContentSize(Alignment.Center))
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(
                    key = {it.apiUrl},
                    items = articles,
                ) { index ->
//                    val article = articles[index]
                    index?.let { news ->
                    Text(text = news.webTitle)
                    }
                }
            }
        }
    }

//    Column(
//        modifier = Modifier
//            .padding(16.dp)
//            .fillMaxSize()
//    ) {
//        OutlinedTextField(
//            value = searchQuery.value,
//            onValueChange = { textValue ->
//                uiState.onQueryChange(textValue)
//            },
//            label = {
//                Text(text = "Search News")
//            },
//            modifier = Modifier.fillMaxWidth(),
//            trailingIcon = {
//                Icon(
//                    imageVector = Icons.Default.Search,
//                    contentDescription = "Search",
//                    modifier = Modifier
//                        .clickable {
//
//                        }
//                )
//            },
//            keyboardOptions = KeyboardOptions(
//                imeAction = ImeAction.Done
//            )
//        )
//        Spacer(modifier = Modifier.height(16.dp))
//        if (isLoading) {
//            CircularProgressIndicator(modifier = Modifier
//                .fillMaxWidth()
//                .wrapContentSize(Alignment.Center))
//        } else {
//            LazyColumn(modifier = Modifier.fillMaxSize()) {
//                newsPaginatedItems?.let {
//                    items(
//                        count = newsPaginatedItems.itemCount,
//                        key = newsPaginatedItems.itemKey(),
//                        contentType = newsPaginatedItems.itemContentType()
//                    ){index ->
//                        val item = newsPaginatedItems[index]
//                        item?.let { news ->
//                            MovieItem(movie = news)
//                        }
//                    }
//                }
//
//            }
//        }
//    }
}

//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun SearchPreview() {
//    SearchScreen()
//}

//@Composable
//fun MovieList(movies: List<MovieDetails>, model: MovieViewModel) {
//    LazyColumn {
//        items(movies) { movie ->
//            MovieItem(movie = movie, viewModel = model)
//        }
//    }
//}
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun MovieItem(movie: NewsModel, viewModel: SearchViewModel) {
//    val context = LocalContext.current
//    val scaffoldState = rememberModalBottomSheetState()
//    val scope = rememberCoroutineScope()
//    var isFilled by remember { mutableStateOf(false) }
//
//    Row(modifier = Modifier
//        .padding(16.dp)
//        .clickable {
//            scope.launch {
//                scaffoldState.partialExpand()
//            }
//        }
//    ) {
//        Image(
//            painter = rememberAsyncImagePainter(
//                model = movie.fields
//            ),
//            contentDescription = "Movie Poster",
//            modifier = Modifier.size(90.dp)
//        )
//        Spacer(modifier = Modifier.width(16.dp))
//        Column {
//            Text(text = movie.)
//            IconButton(
//                onClick = {
//                    viewModel.addMovieToFavorite(movie, viewModel.getUserId(context))
//                    isFilled = !isFilled
//                },
//                content = {
//                    if (isFilled) {
//                        Icon(
//                            imageVector = Icons.Filled.Favorite,
//                            contentDescription = "Favorite",
//                            tint = Color.Red
//                        )
//                    } else {
//                        Icon(
//                            imageVector = Icons.Outlined.FavoriteBorder,
//                            contentDescription = "Favorite"
//                        )
//                    }
//                }
//            )
//        }
//
//    }
//}