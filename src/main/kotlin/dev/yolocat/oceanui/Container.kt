package dev.yolocat.oceanui.nodes

import dev.yolocat.oceanui.Renderer

open class Container : Node {

    val children: MutableList<Node> = mutableListOf()

    override fun render(renderer: Renderer) {
        children.forEach { it.render(renderer) }
    }

}