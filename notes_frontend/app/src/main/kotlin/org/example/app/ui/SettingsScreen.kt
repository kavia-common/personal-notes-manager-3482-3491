package org.example.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

// PUBLIC_INTERFACE
@Composable
fun SettingsScreen() {
    Column(Modifier.fillMaxSize().padding(20.dp)) {
        Text(
            text = "Settings",
            style = androidx.compose.material3.MaterialTheme.typography.headlineSmall
        )
        Spacer(Modifier.height(8.dp))
        Text("Minimal settings UI. Add customizable app preferences here.")
    }
}
