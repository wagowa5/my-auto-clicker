package com.example.autoclicker.infrastructure.click

import com.example.autoclicker.usecase.ClickPort
import java.awt.Robot
import java.awt.event.InputEvent

/**
 * Click implementation using AWT Robot.
 */
class RobotClickAdapter : ClickPort {
    private val robot = Robot()

    override fun click() {
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK)
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK)
    }
}
