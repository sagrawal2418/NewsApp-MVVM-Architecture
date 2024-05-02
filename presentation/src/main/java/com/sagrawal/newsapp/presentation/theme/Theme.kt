package com.sagrawal.newsapp.presentation.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF6200EE),     // A vibrant indigo
    onPrimary = Color.White,         // White text/iconography on primary
    primaryContainer = Color(0xFF3700B3),
    onPrimaryContainer = Color.White,
    secondary = Color(0xFF03DAC6),   // A teal secondary color
    onSecondary = Color.Black,       // Black text/iconography on secondary
    secondaryContainer = Color(0xFF018786),
    onSecondaryContainer = Color.Black,
    tertiary = Color(0xFF03DAC6),    // Another shade of teal or a different color
    onTertiary = Color.Black,
    surface = Color(0xFFFAFAFA),     // Near white surfaces
    onSurface = Color.Black,         // Black text/iconography on surfaces
    background = Color(0xFFFAFAFA),  // Background color
    onBackground = Color.Black,      // Text/iconography on background
    error = Color(0xFFB00020),       // Error color for error states
    onError = Color.White            // Text/iconography on error colors
)

@Composable
fun NewsAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}