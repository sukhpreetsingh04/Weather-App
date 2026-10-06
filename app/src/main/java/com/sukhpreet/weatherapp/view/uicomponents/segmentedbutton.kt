package com.sukhpreet.weatherapp.view.uicomponents

import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun `Segmented-button`(
    isFahrenheit: Boolean,
    onUnitSelected: (Boolean) -> Unit
) {
    val options = listOf("F°", "C°")
    SingleChoiceSegmentedButtonRow {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                selected = isFahrenheit == (index == 0),
                onClick = { onUnitSelected(index == 0) },
                shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size)
            ) {
                Text(label)
            }
        }
    }
}
