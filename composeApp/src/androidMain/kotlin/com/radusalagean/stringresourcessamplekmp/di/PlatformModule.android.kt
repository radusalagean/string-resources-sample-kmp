package com.radusalagean.stringresourcessamplekmp.di

import com.radusalagean.stringresourcessamplekmp.util.LanguageManager
import com.radusalagean.stringresourcessamplekmp.util.LanguageManagerAndroid
import org.koin.dsl.module

actual val platformModule = module {
    factory<LanguageManager> { LanguageManagerAndroid() }
}