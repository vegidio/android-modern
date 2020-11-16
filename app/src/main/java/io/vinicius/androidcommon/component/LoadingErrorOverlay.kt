package io.vinicius.androidcommon.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview
import io.vinicius.androidcommon.ui.AndroidCommonTheme

typealias RetryHandler = () -> Unit

@Composable
fun LoadingErrorOverlay(isError: Boolean = false, retry: RetryHandler? = null)
{
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.background(Color.White).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        if (isError) ErrorOverlay(retry) else LoadingOverlay()
    }
}

@Composable
private fun LoadingOverlay()
{
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp),
    ) {
        CircularProgressIndicator()

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Loading...")
    }
}

@Composable
private fun ErrorOverlay(retry: RetryHandler?)
{
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(16.dp),
    ) {
        Icon(Icons.Filled.Warning)

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "There is a problem connecting to the server. Please check your connection and try again.",
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { retry?.invoke() }) {
            Text(text = "Try Again")
        }
    }
}

@Preview
@Composable
private fun DefaultPreview()
{
    AndroidCommonTheme {
        LoadingErrorOverlay()
    }
}