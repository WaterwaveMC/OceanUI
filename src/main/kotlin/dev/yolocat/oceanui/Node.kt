package dev.yolocat.oceanui.nodes

import dev.yolocat.oceanui.Renderer

interface Node {

    fun render(renderer: Renderer)

}