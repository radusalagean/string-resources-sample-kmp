package com.radusalagean.stringresourcessamplekmp.util

interface LanguageManager {
    fun getCurrentLanguageCode(): String
    fun onLanguageSelected(code: String)
}