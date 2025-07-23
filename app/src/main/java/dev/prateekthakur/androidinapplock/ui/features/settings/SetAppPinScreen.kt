package dev.prateekthakur.androidinapplock.ui.features.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.prateekthakur.androidinapplock.ui.navigation.LocalNavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetAppPinScreen(modifier: Modifier = Modifier) {

    val navHostController = LocalNavHostController.current

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Set Pin")}, navigationIcon = {
                IconButton(onClick = {
                    navHostController.popBackStack()
                }) {
                    Icon(Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back")
                }
            })
        },
    ) { safeArea ->
        Box(modifier = modifier.padding(safeArea)){

        }
    }
}

@Preview
@Composable
private fun SetAppPinScreenPreview() {
    SetAppPinScreen()
}