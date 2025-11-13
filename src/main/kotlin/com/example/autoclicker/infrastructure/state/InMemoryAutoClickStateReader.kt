package com.example.autoclicker.infrastructure.state

import com.example.autoclicker.domain.AutoClickState
import com.example.autoclicker.usecase.AutoClickStateReader
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Thread-safe in-memory state shared by UI and key listener.
 */
class InMemoryAutoClickStateReader : AutoClickStateReader {
    private val toggleEnabled = AtomicBoolean(false)
    private val shiftPressed = AtomicBoolean(false)

    fun setToggleEnabled(enabled: Boolean) {
        toggleEnabled.set(enabled)
    }

    fun setShiftPressed(pressed: Boolean) {
        shiftPressed.set(pressed)
    }

    override fun read(): AutoClickState = AutoClickState(
        toggleEnabled = toggleEnabled.get(),
        shiftPressed = shiftPressed.get()
    )
}
