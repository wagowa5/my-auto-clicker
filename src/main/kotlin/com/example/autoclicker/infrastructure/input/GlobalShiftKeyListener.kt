package com.example.autoclicker.infrastructure.input

import com.example.autoclicker.infrastructure.state.InMemoryAutoClickStateReader
import com.github.kwhat.jnativehook.GlobalScreen
import com.github.kwhat.jnativehook.NativeHookException
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener
import java.util.logging.Level
import java.util.logging.Logger

/**
 * Global SHIFT key listener using JNativeHook.
 * Detects SHIFT press/release system-wide regardless of application focus.
 */
class GlobalShiftKeyListener(
    private val state: InMemoryAutoClickStateReader
) : NativeKeyListener {

    fun register() {
        try {
            // Suppress JNativeHook's default verbose logging
            Logger.getLogger(GlobalScreen::class.java.packageName).level = Level.WARNING
            if (!GlobalScreen.isNativeHookRegistered()) {
                GlobalScreen.registerNativeHook()
            }
            GlobalScreen.addNativeKeyListener(this)
        } catch (e: NativeHookException) {
            // If registration fails, we cannot provide global keys; leave state updates inactive
        }
    }

    fun unregister() {
        GlobalScreen.removeNativeKeyListener(this)
        if (GlobalScreen.isNativeHookRegistered()) {
            try {
                GlobalScreen.unregisterNativeHook()
            } catch (_: NativeHookException) {
            }
        }
    }

    override fun nativeKeyPressed(e: NativeKeyEvent) {
        if (isShift(e)) state.setShiftPressed(true)
    }

    override fun nativeKeyReleased(e: NativeKeyEvent) {
        if (isShift(e)) state.setShiftPressed(false)
    }

    override fun nativeKeyTyped(e: NativeKeyEvent) {
        // not used
    }

    private fun isShift(e: NativeKeyEvent): Boolean {
        return when (e.keyCode) {
            NativeKeyEvent.VC_SHIFT -> true
            else -> false
        }
    }
}
