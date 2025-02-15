package dev.yolocat.oceanui

class Context(internal val parent: Container, internal val invalidate: () -> Unit) {

    private var stateIndex = 0

    fun <T> remember(default: () -> T): State<T> {
        @Suppress("UNCHECKED_CAST")
        val existing = parent.stateStore[stateIndex] as? State<T>

        return if(existing != null) {
            stateIndex++
            existing
        } else {
            val newValue = default.invoke()
            val state = State(newValue, invalidate)
            parent.stateStore[stateIndex] = state
            stateIndex++
            state
        }
    }

}