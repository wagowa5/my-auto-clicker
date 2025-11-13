package com.example.autoclicker.domain

/**
 * Represents the current state relevant to auto clicking.
 */
data class AutoClickState(
    val toggleEnabled: Boolean,
    val shiftPressed: Boolean
)
