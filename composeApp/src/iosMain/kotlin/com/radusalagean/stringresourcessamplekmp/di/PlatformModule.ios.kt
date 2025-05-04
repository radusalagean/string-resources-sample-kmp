package com.radusalagean.stringresourcessamplekmp.di

import com.radusalagean.stringresourcessamplekmp.util.LanguageManager
import com.radusalagean.stringresourcessamplekmp.util.LanguageManagerIOS
import org.koin.dsl.module

actual val platformModule = module {
    factory<LanguageManager> { LanguageManagerIOS() }
}