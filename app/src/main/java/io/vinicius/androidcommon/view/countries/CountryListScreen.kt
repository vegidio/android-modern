package io.vinicius.androidcommon.view.countries

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.viewinterop.viewModel
import androidx.ui.tooling.preview.Preview
import io.vinicius.androidcommon.ui.AndroidCommonTheme
import io.vinicius.androidcommon.viewmodel.CountryListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch

@Composable
fun CountryListScreen()
{
    val viewModel: CountryListViewModel = viewModel()
    val scope = rememberCoroutineScope()
    val countries = viewModel.getCountries().collectAsState(initial = emptyList())

    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Country List") }) }
    ) {
        Text(text = countries.value.size.toString())
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