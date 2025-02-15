package dev.yolocat.oceanui_test.ocean

import dev.yolocat.oceanui.Renderer
import dev.yolocat.oceanui.style.Color

object ConsoleRenderer : Renderer {
    override fun beginFrame() {
        println("Begin frame")
    }

    override fun endFrame() {
        println("End frame")
    }

    override fun drawRect(x: Double, y: Double, width: Double, height: Double, color: Color) {
        println("Drawing rect at $x, $y with width $width and height $height with color $color")
    }

    override fun drawText(x: Double, y: Double, text: String, color: Color) {
        println("Drawing text at $x, $y with value '$text' and color $color")
    }

}