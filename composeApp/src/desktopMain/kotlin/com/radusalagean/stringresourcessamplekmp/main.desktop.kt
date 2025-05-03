package com.radusalagean.stringresourcessamplekmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.radusalagean.stringresourcessamplekmp.ui.screen.MainScreen

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "StringResourcesSampleKMP",
    ) {
        application(DesktopPlatform())
        MainScreen()
    }
}