package com.radusalagean.stringresourcessamplekmp

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

interface Platform

fun application(p: Platform) {
    Napier.base(DebugAntilog())
}