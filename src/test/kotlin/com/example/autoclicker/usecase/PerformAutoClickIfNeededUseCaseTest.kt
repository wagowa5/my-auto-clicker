package com.example.autoclicker.usecase

import com.example.autoclicker.domain.AutoClickState
import kotlin.test.Test
import kotlin.test.assertEquals

class PerformAutoClickIfNeededUseCaseTest {

    private class FakeStateReader(var state: AutoClickState) : AutoClickStateReader {
        override fun read(): AutoClickState = state
    }

    private class FakeClickPort : ClickPort {
        var clicks = 0
        override fun click() { clicks++ }
    }

    @Test
    fun `clicks when rule allows`() {
        val reader = FakeStateReader(AutoClickState(toggleEnabled = true, shiftPressed = true))
        val clicker = FakeClickPort()
        val useCase = PerformAutoClickIfNeededUseCase(reader, clicker)

        useCase.execute()

        assertEquals(1, clicker.clicks)
    }

    @Test
    fun `does not click when rule forbids`() {
        val reader = FakeStateReader(AutoClickState(toggleEnabled = false, shiftPressed = true))
        val clicker = FakeClickPort()
        val useCase = PerformAutoClickIfNeededUseCase(reader, clicker)

        useCase.execute()

        assertEquals(0, clicker.clicks)
    }
}
