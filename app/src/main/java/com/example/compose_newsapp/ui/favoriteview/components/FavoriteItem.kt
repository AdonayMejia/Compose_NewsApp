package com.example.compose_newsapp.ui.favoriteview.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.compose_newsapp.model.room.NewsEntity
import java.net.URLEncoder

@Composable
fun Item(
    favNews: NewsEntity,
    delete: (String) -> Unit,
    navHostController: NavHostController
) {
    val url = URLEncoder.encode(favNews.webUrl, "UTF-8")

    Box {
        Image(
            painter = rememberAsyncImagePainter(favNews.fields),
            contentDescription = "Movie Poster",
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    navHostController.navigate("DetailScreen/${url}")
                }
        )
        FloatingActionButton(onClick = { delete(favNews.itemId) },
            content = {
                Icon(imageVector = Icons.Outlined.Delete, contentDescription = "Delete")
            }
        )
    }

}