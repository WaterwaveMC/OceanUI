package dev.yolocat.oceanui

import dev.yolocat.oceanui.style.Stylesheet

abstract class Component : Node {

    override val stateStore: MutableMap<Int, Any?> = mutableMapOf()
    private val stylesheet: Stylesheet = Stylesheet()

    override fun style(styles: Stylesheet.() -> Unit) {
        styles.invoke(stylesheet)
    }

}