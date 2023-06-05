package com.example.compose_newsapp.ui.favoriteview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import coil.compose.rememberAsyncImagePainter
import com.example.compose_newsapp.model.room.NewsEntity
import com.example.compose_newsapp.ui.favoriteview.viewmodel.FavoriteViewModel
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@Composable
fun FavoriteScreen(
    viewModel:FavoriteViewModel
) {
    FavoriteScreenContent(viewModel = viewModel)
}

@Composable
fun FavoriteScreenContent(
    viewModel: FavoriteViewModel
) {
    val favNews by viewModel.new.collectAsState()
    val pagerState = rememberPagerState()
    val favoriteUiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(count = favNews.size, state = pagerState, itemSpacing = 5.dp) { page ->
            Card(
                modifier = Modifier
                    .size(400.dp)
                    .padding(16.dp)
                    .graphicsLayer {
                        val pageOffset =
                            (pagerState.currentPage - page) + pagerState.currentPageOffset
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                            .also { scale ->
                                scaleX = scale
                                scaleY = scale
                            }
                    }
            ) {
                val movie = favNews[page]
                Item(movie = movie, delete = favoriteUiState.deleteNew)
            }
        }
    }
}

@Composable
fun Item(movie: NewsEntity,delete:(String) -> Unit) {
    Box {
        Image(
            painter = rememberAsyncImagePainter(movie.fields),
            contentDescription = "Movie Poster",
            modifier = Modifier.fillMaxSize()
        )
        FloatingActionButton(onClick = { delete(movie.itemId) },
            content = {
                Icon(imageVector = Icons.Outlined.Delete, contentDescription = "Delete")
            }
        )
    }

}

//@Preview(showSystemUi = true, showBackground = true)
//@Composable
//fun FavoritePreview() {
//    FavoriteScreenContent(viewModel = viewModel )
//}