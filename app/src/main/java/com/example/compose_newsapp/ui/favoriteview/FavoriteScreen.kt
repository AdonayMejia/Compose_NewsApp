package com.example.compose_newsapp.ui.favoriteview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun FavoriteScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            fontSize = 24.sp,
            text = "Favorite Screen",
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun FavoritePreview() {
    FavoriteScreen()
}