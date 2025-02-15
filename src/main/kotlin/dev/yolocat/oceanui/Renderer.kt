package dev.yolocat.oceanui

import dev.yolocat.oceanui.style.Color

interface Renderer {

    fun beginFrame()
    fun endFrame()
    fun drawRect(x: Double, y: Double, width: Double, height: Double, color: Color)
    fun drawText(x: Double, y: Double, text: String, color: Color)

}