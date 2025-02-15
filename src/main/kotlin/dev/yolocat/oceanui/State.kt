package dev.yolocat.oceanui

class State<T>(initialValue: T, private val invalidate: () -> Unit) {
    private var _value = initialValue

    var value: T
        get() = _value
        set(newValue) {
            if(_value != newValue) {
                _value = newValue

                invalidate.invoke()
            }
        }
}