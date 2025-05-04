package com.radusalagean.stringresourcessamplekmp.di

import org.koin.dsl.module
import org.koin.core.module.dsl.viewModelOf
import com.radusalagean.stringresourcessamplekmp.ui.screen.MainViewModel

val commonModule = module {
    viewModelOf(::MainViewModel)
}