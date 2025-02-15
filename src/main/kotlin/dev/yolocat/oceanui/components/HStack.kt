package dev.yolocat.oceanui.components

import dev.yolocat.oceanui.Renderer
import dev.yolocat.oceanui.Container
import dev.yolocat.oceanui.Context
import dev.yolocat.oceanui.style.SizeParams
import dev.yolocat.oceanui.style.Spacing
import dev.yolocat.oceanui.style.Unit.Direction

class HStack(context: Context, private val spacing: Spacing = Spacing.Leading, content: (Context) -> Unit = {}) : Container() {

    init {
        context.parent.addChild(this)
        val subContext = Context(this, context.invalidate)
        content.invoke(subContext)
    }

    override fun render(x: Double, y: Double, params: SizeParams, renderer: Renderer) {
        println("Rendering HStack ($x, $y) with ${children.size} children")
        renderSelf(x, y, params, renderer)

        when(spacing) {
            Spacing.Leading -> {
                var offsetX = 0.0

                children.forEach {
                    val size = it.getSize(params)
                    it.render(x + offsetX, y, params, renderer)
                    offsetX += size.first + (stylesheet.gap?.getPixels(params, Direction.HORIZONTAL) ?: 0.0)
                }
            }
            Spacing.Trailing -> {
                var offsetX = getSize(params).first

                children.forEach {
                    val size = it.getSize(params)
                    it.render(x + offsetX - size.first, y, params, renderer)
                    offsetX -= size.first + (stylesheet.gap?.getPixels(params, Direction.HORIZONTAL) ?: 0.0)
                }
            }
            Spacing.Center -> {
                val totalWidth = children.sumOf { it.getSize(params).first } + (children.size - 1) * (stylesheet.gap?.getPixels(params, Direction.HORIZONTAL) ?: 0.0)
                var offsetX = (getSize(params).first - totalWidth) / 2

                children.forEach {
                    val size = it.getSize(params)
                    it.render(x + offsetX, y, params, renderer)
                    offsetX += size.first + (stylesheet.gap?.getPixels(params, Direction.HORIZONTAL) ?: 0.0)
                }
            }
            Spacing.Between -> {
                val totalGap = getSize(params).first - children.sumOf { it.getSize(params).first }
                val gapSize = totalGap / (children.size - 1)

                var offsetX = 0.0

                children.forEach {
                    val size = it.getSize(params)
                    it.render(x + offsetX, y, params, renderer)
                    offsetX += size.first + gapSize
                }
            }
            Spacing.Evenly -> {
                val totalGap = getSize(params).first - children.sumOf { it.getSize(params).first }
                val gapSize = totalGap / (children.size + 1)

                var offsetX = gapSize

                children.forEach {
                    val size = it.getSize(params)
                    it.render(x + offsetX, y, params, renderer)
                    offsetX += size.first + gapSize
                }
            }
        }

    }

}