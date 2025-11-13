package com.example.autoclicker.infrastructure.input

import com.example.autoclicker.infrastructure.state.InMemoryAutoClickStateReader
import java.awt.KeyEventDispatcher
import java.awt.KeyboardFocusManager
import java.awt.event.KeyEvent

/**
 * Listens to global keyboard events within the JVM to detect SHIFT key state changes.
 */
class GlobalShiftKeyListener(
    private val state: InMemoryAutoClickStateReader
) : KeyEventDispatcher {

    fun register() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(this)
    }

    fun unregister() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(this)
    }

    override fun dispatchKeyEvent(e: KeyEvent): Boolean {
        when (e.id) {
            KeyEvent.KEY_PRESSED -> if (isShift(e)) state.setShiftPressed(true)
            KeyEvent.KEY_RELEASED -> if (isShift(e)) state.setShiftPressed(false)
        }
        return false // do not consume
    }

    private fun isShift(e: KeyEvent): Boolean =
        e.keyCode == KeyEvent.VK_SHIFT || e.keyCode == KeyEvent.VK_SHIFT || e.keyCode == KeyEvent.VK_SHIFT
}
