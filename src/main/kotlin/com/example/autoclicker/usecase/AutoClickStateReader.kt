package com.example.autoclicker.usecase

import com.example.autoclicker.domain.AutoClickState

/**
 * Abstraction to read current state for auto click decision.
 */
fun interface AutoClickStateReader {
    fun read(): AutoClickState
}
