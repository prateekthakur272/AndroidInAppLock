package dev.prateekthakur.androidinapplock.ui.features.elements

import androidx.compose.material3.Checkbox
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CheckboxListItem(
    headline: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    subHeadline: String = "",
) {
    ListItem(
        modifier = modifier,
        headlineContent = { Text(headline) },
        supportingContent = {
            if (subHeadline.isNotBlank())
                Text(subHeadline)
        },
        trailingContent = {
            Checkbox(checked = checked, onCheckedChange = onCheckedChange)
        }
    )
}

@Preview
@Composable
private fun CheckboxListItemPreview() {
    CheckboxListItem(headline = "Preview", checked = true, onCheckedChange = {}, subHeadline = "")
}