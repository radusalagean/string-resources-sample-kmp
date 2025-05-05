package com.radusalagean.stringresourcessamplekmp.di

import com.radusalagean.stringresourcessamplekmp.util.LanguageManager
import com.radusalagean.stringresourcessamplekmp.util.LanguageManagerDesktop
import org.koin.dsl.module

actual val platformModule = module {
    single<LanguageManager> { LanguageManagerDesktop() }
}