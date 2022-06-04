package com.example.formapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = Brown500,
    primaryVariant = Brown900,
    onPrimary = Color.White,
    secondary = Brown500,
    secondaryVariant = Brown900,
    onSecondary = Color.White,
    onError = Red800,
    onBackground = Color.Black
)


private val DarkColorPalette = darkColors(
    primary = Brown200,
    primaryVariant = Brown700,
    onPrimary = Color.Black,
    secondary = Brown200,
    onSecondary = Color.Black,
    onError = Red300,
    onBackground = Color.White
)

@Composable
fun FormAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    MaterialTheme(
        colors = if (darkTheme) DarkColorPalette else LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}