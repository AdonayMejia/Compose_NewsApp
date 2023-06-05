package com.example.compose_newsapp.ui.detailview

import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun DetailScreen(webUrl:String) {
    DetailScreenContent(webUrl = webUrl)
}

@Composable
fun DetailScreenContent(
    webUrl: String
) {
    Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = {
                WebView(it).apply {
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    webViewClient = WebViewClient()
                    loadUrl(webUrl)
                }
            },
            update = {
                it.loadUrl(webUrl)
            }
        )

    }

}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun Preview() {
//    DetailScreen()
//}