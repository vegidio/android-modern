package io.vinicius.androidcommon.view.countries

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.onActive
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import androidx.ui.tooling.preview.Preview
import io.vinicius.androidcommon.component.LoadingErrorOverlay
import io.vinicius.androidcommon.constant.NetworkState
import io.vinicius.androidcommon.model.Country
import io.vinicius.androidcommon.ui.AndroidCommonTheme
import io.vinicius.androidcommon.viewmodel.CountryListViewModel
import kotlinx.coroutines.launch

@Composable
fun CountryListScreen()
{
    val viewModel: CountryListViewModel = viewModel()
    val scope = rememberCoroutineScope()
    val state = viewModel.state.collectAsState()
    val countries = viewModel.countries.collectAsState()

    // region - Lifecycle
    onActive {
        scope.launch { viewModel.getCountries() }
    }
    // endregion

    // region - View
    Box {
        // Content
        Scaffold(
            topBar = { TopAppBar(title = { Text(text = "Country List") }) }
        ) {
            LazyColumnFor(items = countries.value) { country ->
                CountryRow(country)
            }
        }

        // Overlay
        if (state.value != NetworkState.IDLE) {
            LoadingErrorOverlay(
                isError = state.value == NetworkState.ERROR,
                retry = { scope.launch { viewModel.getCountries() } }
            )
        }
    }
    // endregion
}

@Composable
private fun CountryRow(country: Country)
{
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = country.name)
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