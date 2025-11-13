package com.example.autoclicker.domain

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AutoClickRuleTest {

    @Test
    fun `returns true when toggle is ON and shift is pressed`() {
        val state = AutoClickState(toggleEnabled = true, shiftPressed = true)
        assertTrue(AutoClickRule.shouldAutoClick(state))
    }

    @Test
    fun `returns false when toggle is OFF regardless of shift`() {
        assertFalse(AutoClickRule.shouldAutoClick(AutoClickState(toggleEnabled = false, shiftPressed = true)))
        assertFalse(AutoClickRule.shouldAutoClick(AutoClickState(toggleEnabled = false, shiftPressed = false)))
    }

    @Test
    fun `returns false when toggle is ON but shift is not pressed`() {
        val state = AutoClickState(toggleEnabled = true, shiftPressed = false)
        assertFalse(AutoClickRule.shouldAutoClick(state))
    }
}
