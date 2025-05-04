package com.radusalagean.stringresourcessamplekmp

import com.radusalagean.stringresourcessamplekmp.di.appModule
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.core.context.startKoin

interface Platform

fun application(p: Platform) {
    Napier.base(DebugAntilog())
    startKoin {
        modules(appModule())
    }
}