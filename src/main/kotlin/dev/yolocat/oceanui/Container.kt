package dev.yolocat.oceanui

import dev.yolocat.oceanui.style.Color.Companion.rgb
import dev.yolocat.oceanui.style.SizeParams
import dev.yolocat.oceanui.style.Stylesheet
import dev.yolocat.oceanui.style.Unit.Companion.stackSize
import dev.yolocat.oceanui.style.Unit.Direction

open class Container : Node {

    override val stateStore: MutableMap<Int, Any?> = mutableMapOf()
    internal val stylesheet: Stylesheet = Stylesheet()
    internal val children: MutableList<Node> = mutableListOf()

    override fun render(x: Double, y: Double, params: SizeParams, renderer: Renderer) {
        renderSelf(x, y, params, renderer)
        var offsetX = 0.0

        children.forEach {
            val size = it.getSize(params)
            it.render(x + offsetX, y, params, renderer)
            offsetX += size.first + (stylesheet.gap?.getPixels(params, Direction.HORIZONTAL) ?: 0.0)
        }
    }

    internal fun renderSelf(x: Double, y: Double, params: SizeParams, renderer: Renderer) {
        val size = getSize(params)
        renderer.drawRect(x, y, size.first, size.second, stylesheet.backgroundColor ?: rgb(40, 40, 40)) // TODO: style inheritance
    }

    override fun style(styles: Stylesheet.() -> Unit) {
        styles.invoke(stylesheet)
    }

    internal fun addChild(node: Node) {
        children.add(node)
    }

    override fun getSize(params: SizeParams): Pair<Double, Double> {
        val parentWidth = stylesheet.width?.getPixels(params, Direction.HORIZONTAL)
        val parentHeight = stylesheet.height?.getPixels(params, Direction.VERTICAL)
        val width = parentWidth ?: stackSize(children.map { it.getSize(parentSize(params, null, parentHeight)).first }, stylesheet.gap?.getPixels(params, Direction.HORIZONTAL) ?: 0.0)
        val height = parentHeight ?: children.maxOfOrNull { it.getSize(parentSize(params, parentWidth, null)).second } ?: 0.0

        return Pair(width, height)
    }

    internal fun parentSize(params: SizeParams, parentWidth: Double?, parentHeight: Double?): SizeParams {
        return SizeParams(
            parentWidth = parentWidth ?: params.parentWidth,
            parentHeight = parentHeight ?: params.parentHeight,
            viewportWidth = params.viewportWidth,
            viewportHeight = params.viewportHeight,
        )
    }

}