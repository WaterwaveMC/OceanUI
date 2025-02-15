package dev.yolocat.oceanui.components

import dev.yolocat.oceanui.Renderer
import dev.yolocat.oceanui.Component
import dev.yolocat.oceanui.Context
import dev.yolocat.oceanui.style.Color
import dev.yolocat.oceanui.style.Color.Companion.rgb
import dev.yolocat.oceanui.style.SizeParams

class Text(context: Context, private var text: String) : Component() {

    init {
        context.parent.addChild(this)
    }

    override fun render(x: Double, y: Double, params: SizeParams, renderer: Renderer) {
        renderer.drawText(x, y, text, rgb(0, 0, 0))
    }

    override fun getSize(params: SizeParams): Pair<Double, Double> {
        TODO("Not yet implemented")
    }

}