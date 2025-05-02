package com.radusalagean.stringresourcessamplekmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform