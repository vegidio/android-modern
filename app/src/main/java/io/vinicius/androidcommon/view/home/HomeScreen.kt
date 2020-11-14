package io.vinicius.androidcommon.view.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumnFor
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.onActive
import androidx.compose.runtime.onDispose
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.navigate
import androidx.ui.tooling.preview.Preview
import io.vinicius.androidcommon.component.Screen
import io.vinicius.androidcommon.component.navController
import io.vinicius.androidcommon.ui.AndroidCommonTheme

@Composable
fun HomeScreen()
{
    val menuOptions = listOf("Countries", "Lorum", "Ipsum")

    // region - Body
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = "Android Common") }) }
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            // Compose version of RecyclerView
            LazyColumnFor(items = menuOptions) { name ->
                MenuOption(name = name)
            }
        }
    }
    // endregion
}

@Composable
private fun MenuOption(name: String)
{
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .clickable(onClick = { navController.navigate(Screen.CountryList.route) })
            .fillMaxWidth()
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = name)
    }
}

@Preview
@Composable
private fun DefaultPreview()
{
    AndroidCommonTheme {
        HomeScreen()
    }
}