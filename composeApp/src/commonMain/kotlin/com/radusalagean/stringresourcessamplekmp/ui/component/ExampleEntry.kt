package com.radusalagean.stringresourcessamplekmp.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.radusalagean.stringresourcessamplekmp.ui.theme.PurpleGrey40

@Composable
fun ExampleEntry(
    model: ExampleEntryModel,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = model.label.buildStringComposable(),
            style = MaterialTheme.typography.labelSmall.copy(color = Color.White),
            modifier = Modifier.background(color = PurpleGrey40)
        )
        Text(
            text = model.value.buildAnnotatedStringComposable(),
            style = MaterialTheme.typography.titleMedium
        )
    }
}