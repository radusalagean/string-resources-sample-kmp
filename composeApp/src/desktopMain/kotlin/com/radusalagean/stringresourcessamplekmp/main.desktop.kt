package com.radusalagean.stringresourcessamplekmp

import androidx.compose.runtime.key
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.radusalagean.stringresourcessamplekmp.ui.screen.MainScreen
import com.radusalagean.stringresourcessamplekmp.util.LanguageManager
import com.radusalagean.stringresourcessamplekmp.util.LanguageManagerDesktop
import org.koin.compose.koinInject

fun main() = application {
    application(DesktopPlatform())
    val languageManager: LanguageManagerDesktop =
        koinInject<LanguageManager>() as LanguageManagerDesktop

    Window(
        onCloseRequest = ::exitApplication,
        title = "StringResourcesSampleKMP",
    ) {
        key(languageManager.currentLanguage) {
            MainScreen()
        }
    }
}