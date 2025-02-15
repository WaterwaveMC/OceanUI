package dev.yolocat.oceanui.components

import dev.yolocat.oceanui.Renderer
import dev.yolocat.oceanui.Container
import dev.yolocat.oceanui.Context
import dev.yolocat.oceanui.style.SizeParams
import dev.yolocat.oceanui.style.Unit.Companion.stackSize
import dev.yolocat.oceanui.style.Unit.Direction

class VStack(context: Context, content: (Context) -> Unit = {}) : Container() {

    init {
        context.parent.addChild(this)
        val subContext = Context(this, context.invalidate)
        content.invoke(subContext)
    }

    override fun render(x: Double, y: Double, params: SizeParams, renderer: Renderer) {
        println("Rendering VStack ($x, $y) with ${children.size} children")
        renderSelf(x, y, params, renderer)

        var offsetY = 0.0

        children.forEach {
            val size = it.getSize(params)
            it.render(x, y + offsetY, params, renderer)
            offsetY += size.second + (stylesheet.gap?.getPixels(params, Direction.VERTICAL) ?: 0.0)
        }
    }

    override fun getSize(params: SizeParams): Pair<Double, Double> {
        val parentWidth = stylesheet.width?.getPixels(params, Direction.HORIZONTAL)
        val parentHeight = stylesheet.height?.getPixels(params, Direction.VERTICAL)
        val width = parentWidth ?: children.maxOfOrNull { it.getSize(parentSize(params, null, parentHeight)).first } ?: 0.0
        val height = parentHeight ?: stackSize(children.map { it.getSize(parentSize(params, parentWidth, null)).second }, stylesheet.gap?.getPixels(params, Direction.VERTICAL) ?: 0.0)

        return Pair(width, height)
    }

}