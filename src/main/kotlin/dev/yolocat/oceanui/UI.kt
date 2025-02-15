package dev.yolocat.oceanui

import dev.yolocat.oceanui.style.SizeParams

class UI(private val content: (Context) -> Unit) {

    private var mouseX = 0.0
    private var mouseY = 0.0

    private var screenWidth = 0.0
    private var screenHeight = 0.0

    private var invalidated = true

    private val root: Container

    init {
        val root = Container()
        root.style {
            width = 100.vw
            height = 100.vh
        }
        this.root = root
    }

    fun setMousePos(x: Double, y: Double) {
        if(mouseX != x || mouseY != y) {
            mouseX = x
            mouseY = y

            invalidate()
        }
    }

    fun setScreenSize(width: Double, height: Double) {
        if(screenWidth != width || screenHeight != height) {
            screenWidth = width
            screenHeight = height

            invalidate()
        }
    }

    fun render(renderer: Renderer, lazy: Boolean = false) {
        if(invalidated) {
            invalidated = false

            println("\n=== Rebuilding Root UI ===")
            root.children.clear()
            val context = Context(root, ::invalidate)
            content.invoke(context)

            if(lazy) {
                draw(renderer)
            }
        }

        if(!lazy) draw(renderer)
    }

    private fun draw(renderer: Renderer) {
        renderer.beginFrame()
        root.render(0.0, 0.0, SizeParams(screenWidth, screenHeight, screenWidth, screenHeight), renderer)
        renderer.endFrame()
    }

    private fun invalidate() {
        invalidated = true
    }

}