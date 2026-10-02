package com.sukhpreet.weatherapp.view.uicomponents

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun `Elevated-card`() {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        modifier = Modifier
            .size(size = 200.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = "this is a elevated card!",
            modifier = Modifier
                .padding(16.dp)
        )
    }
}