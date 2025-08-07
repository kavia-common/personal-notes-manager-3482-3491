package org.example.app.ui

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// PUBLIC_INTERFACE
@Composable
fun NotesTheme(content: @Composable () -> Unit) {
    val colorScheme = lightColorScheme(
        primary = Color(0xFF1976D2), // Provided primary color
        secondary = Color(0xFF424242), // Provided secondary color
        tertiary = Color(0xFFFFEB3B), // Accent color as tertiary
        background = Color.White,
        surface = Color.White,
        onPrimary = Color.White,
        onSecondary = Color.White,
        onTertiary = Color.Black,
        onBackground = Color.Black,
        onSurface = Color.Black,
    )
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography(),
        content = content
    )
}
