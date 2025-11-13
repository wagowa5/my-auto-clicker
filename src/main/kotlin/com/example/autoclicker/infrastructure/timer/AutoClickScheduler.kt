package com.example.autoclicker.infrastructure.timer

import com.example.autoclicker.domain.AutoClickConfig
import com.example.autoclicker.usecase.PerformAutoClickIfNeededUseCase
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class AutoClickScheduler(
    private val config: AutoClickConfig,
    private val useCase: PerformAutoClickIfNeededUseCase
) {
    private var executor: ScheduledExecutorService? = null

    fun start() {
        if (executor != null) return
        val ex = Executors.newSingleThreadScheduledExecutor { r ->
            Thread(r, "auto-click-scheduler").apply { isDaemon = true }
        }
        ex.scheduleAtFixedRate({
            try {
                useCase.execute()
            } catch (_: Throwable) {
                // ignore to keep scheduler alive
            }
        }, 0, config.intervalMillis, TimeUnit.MILLISECONDS)
        executor = ex
    }

    fun stop() {
        executor?.shutdownNow()
        executor = null
    }
}
