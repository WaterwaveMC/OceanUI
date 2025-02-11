package dev.yolocat.oceanui.context

import dev.yolocat.oceanui.nodes.Container
import dev.yolocat.oceanui.nodes.Node

class Context(private val parent: Container) {

    private var stateIndex = 0
    private var stateStore = mutableMapOf<Int, Any?>()

    fun <T> remember(default: () -> T): T {
        @Suppress("UNCHECKED_CAST")
        val existing = stateStore[stateIndex] as T?
        return if(existing != null) {
            stateIndex++
            existing
        } else {
            val value = default.invoke()
            stateStore[stateIndex] = value
            stateIndex++
            value
        }
    }

    fun addNode(node: Node, content: (Context) -> Unit = {}) {
        if(parent !is Container) return
        parent.children.add(node)

        if(node is Container) {
            content.invoke(Context(node))
        }
    }

}