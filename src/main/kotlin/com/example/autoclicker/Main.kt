package com.example.autoclicker

import com.example.autoclicker.domain.AutoClickConfig
import com.example.autoclicker.infrastructure.click.RobotClickAdapter
import com.example.autoclicker.infrastructure.input.GlobalShiftKeyListener
import com.example.autoclicker.infrastructure.state.InMemoryAutoClickStateReader
import com.example.autoclicker.infrastructure.timer.AutoClickScheduler
import com.example.autoclicker.presentation.ui.MainWindow
import com.example.autoclicker.usecase.PerformAutoClickIfNeededUseCase
import javax.swing.SwingUtilities

fun main() {
    System.setProperty("java.awt.headless", "false")

    val state = InMemoryAutoClickStateReader()
    val keyListener = GlobalShiftKeyListener(state)
    keyListener.register()

    val clickPort = RobotClickAdapter()
    val useCase = PerformAutoClickIfNeededUseCase(state, clickPort)
    val scheduler = AutoClickScheduler(AutoClickConfig(intervalMillis = 50L), useCase)
    scheduler.start()

    Runtime.getRuntime().addShutdownHook(Thread {
        scheduler.stop()
        keyListener.unregister()
    })

    SwingUtilities.invokeLater {
        val window = MainWindow(state)
        window.isVisible = true
    }
}
