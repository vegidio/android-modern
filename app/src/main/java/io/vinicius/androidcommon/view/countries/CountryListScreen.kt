package io.vinicius.androidcommon.view.countries

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.ui.tooling.preview.Preview
import io.vinicius.androidcommon.ui.AndroidCommonTheme

@Composable
fun CountryListScreen()
{
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Country List") }) }
    ) {
        Text(text = "Body")
    }
}

@Preview
@Composable
private fun DefaultPreview()
{
    AndroidCommonTheme {
        CountryListScreen()
    }
}