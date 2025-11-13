package com.example.autoclicker.presentation.ui

import com.example.autoclicker.infrastructure.state.InMemoryAutoClickStateReader
import java.awt.BorderLayout
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JToggleButton
import javax.swing.SwingUtilities

class MainWindow(
    private val state: InMemoryAutoClickStateReader
) : JFrame("Auto Clicker") {

    private val toggle = JToggleButton("AutoClick: OFF")

    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(300, 120)
        setLocationRelativeTo(null)
        layout = BorderLayout()

        val info = JLabel("Hold SHIFT to auto-click when enabled")
        val panel = JPanel()
        panel.add(toggle)
        val quit = JButton("Quit")
        quit.addActionListener { dispose() }

        add(info, BorderLayout.NORTH)
        add(panel, BorderLayout.CENTER)
        add(quit, BorderLayout.SOUTH)

        toggle.addActionListener {
            val enabled = toggle.isSelected
            state.setToggleEnabled(enabled)
            toggle.text = if (enabled) "AutoClick: ON" else "AutoClick: OFF"
        }
    }

    companion object {
        fun showOnEdt(window: MainWindow) {
            SwingUtilities.invokeLater { window.isVisible = true }
        }
    }
}
