package com.example.autoclicker.domain

/**
 * Domain rule that decides if auto clicking should be performed.
 */
object AutoClickRule {
    fun shouldAutoClick(state: AutoClickState): Boolean =
        state.toggleEnabled && state.shiftPressed
}
