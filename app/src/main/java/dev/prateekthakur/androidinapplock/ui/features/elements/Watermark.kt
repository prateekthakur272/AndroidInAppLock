package dev.prateekthakur.androidinapplock.ui.features.elements

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.prateekthakur.androidinapplock.R
import java.time.LocalDate

@Composable
fun Watermark(modifier: Modifier = Modifier, context: Context = LocalContext.current) {
    val date: LocalDate = LocalDate.now()
    val appName = stringResource(R.string.app_name)
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp), horizontalArrangement = Arrangement.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("$appName - ${date.year}", style = MaterialTheme.typography.labelMedium)
            Text("Prateek Thakur", style = MaterialTheme.typography.labelMedium, color = Color.Gray)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun WatermarkPreview() {
    Watermark()
}