package dev.yolocat.oceanui

import dev.yolocat.oceanui.style.SizeParams
import dev.yolocat.oceanui.style.Stylesheet

interface Node {

    val stateStore: MutableMap<Int, Any?>

    fun render(x: Double, y: Double, params: SizeParams, renderer: Renderer)
    fun style(styles: Stylesheet.() -> Unit)
    fun getSize(params: SizeParams): Pair<Double, Double>

}