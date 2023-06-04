package com.example.compose_newsapp.ui.searchview.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.compose_newsapp.R
import com.example.compose_newsapp.model.datamodel.NewsModel

@Composable
fun SearchNewsItem(
    article: NewsModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val painter = rememberAsyncImagePainter(article.fields.thumbnail)
        Image(
            painter = painter,
            contentDescription = stringResource(id = R.string.news_image),
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .padding(end = 16.dp),
            contentScale = ContentScale.Crop
            )
        Column {
            Text(
                text = article.webTitle,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}