package com.example.compose_newsapp.ui.searchview

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun SearchScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            fontSize = 24.sp,
            text = "Search Screen",
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SearchPreview() {
    SearchScreen()
}