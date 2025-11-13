package com.example.autoclicker.usecase

import com.example.autoclicker.domain.AutoClickRule

/**
 * Use case: checks current state and performs a single click if rule allows.
 * Intended to be called repeatedly by a scheduler while the app runs.
 */
class PerformAutoClickIfNeededUseCase(
    private val stateReader: AutoClickStateReader,
    private val clickPort: ClickPort,
) {
    fun execute() {
        val state = stateReader.read()
        if (AutoClickRule.shouldAutoClick(state)) {
            clickPort.click()
        }
    }
}
