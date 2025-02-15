package dev.yolocat.oceanui.style

class Unit(private val value: Double, private val type: Type) {

    private val calculations = mutableMapOf<Unit, Operation>()

    operator fun plus(other: Unit): Unit {
        calculations += other to Operation.ADD
        return this
    }

    operator fun minus(other: Unit): Unit {
        calculations += other to Operation.SUBTRACT
        return this
    }

    operator fun times(other: Unit): Unit {
        calculations += other to Operation.MULTIPLY
        return this
    }

    operator fun div(other: Unit): Unit {
        calculations += other to Operation.DIVIDE
        return this
    }

    operator fun times(other: Double): Unit {
        return Unit(value * other, type)
    }

    operator fun times(other: Float): Unit {
        return Unit(value * other, type)
    }

    operator fun times(other: Int): Unit {
        return Unit(value * other, type)
    }

    enum class Operation {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
    }

    enum class Type {
        PIXELS,
        PARENT,
        PARENT_WIDTH,
        PARENT_HEIGHT,
        VIEWPORT,
        VIEWPORT_WIDTH,
        VIEWPORT_HEIGHT,
    }

    companion object {

        @JvmStatic fun px(px: Int) = Unit(px.toDouble(), Type.PIXELS)
        @JvmStatic fun px(px: Float) = Unit(px.toDouble(), Type.PIXELS)
        @JvmStatic fun px(px: Double) = Unit(px, Type.PIXELS)

        @JvmStatic fun pr(pr: Int) = Unit(pr.toDouble(), Type.PARENT)
        @JvmStatic fun pr(pr: Float) = Unit(pr.toDouble(), Type.PARENT)
        @JvmStatic fun pr(pr: Double) = Unit(pr, Type.PARENT)

        @JvmStatic fun pw(pw: Int) = Unit(pw.toDouble(), Type.PARENT_WIDTH)
        @JvmStatic fun pw(pw: Float) = Unit(pw.toDouble(), Type.PARENT_WIDTH)
        @JvmStatic fun pw(pw: Double) = Unit(pw, Type.PARENT_WIDTH)

        @JvmStatic fun ph(ph: Int) = Unit(ph.toDouble(), Type.PARENT_HEIGHT)
        @JvmStatic fun ph(ph: Float) = Unit(ph.toDouble(), Type.PARENT_HEIGHT)
        @JvmStatic fun ph(ph: Double) = Unit(ph, Type.PARENT_HEIGHT)

        @JvmStatic fun vp(vp: Int) = Unit(vp.toDouble(), Type.VIEWPORT)
        @JvmStatic fun vp(vp: Float) = Unit(vp.toDouble(), Type.VIEWPORT)
        @JvmStatic fun vp(vp: Double) = Unit(vp, Type.VIEWPORT)

        @JvmStatic fun vw(vw: Int) = Unit(vw.toDouble(), Type.VIEWPORT_WIDTH)
        @JvmStatic fun vw(vw: Float) = Unit(vw.toDouble(), Type.VIEWPORT_WIDTH)
        @JvmStatic fun vw(vw: Double) = Unit(vw, Type.VIEWPORT_WIDTH)

        @JvmStatic fun vh(vh: Int) = Unit(vh.toDouble(), Type.VIEWPORT_HEIGHT)
        @JvmStatic fun vh(vh: Float) = Unit(vh.toDouble(), Type.VIEWPORT_HEIGHT)
        @JvmStatic fun vh(vh: Double) = Unit(vh, Type.VIEWPORT_HEIGHT)

        fun List<Unit>.sum(): Unit {
            return this.reduce { acc, unit -> acc + unit }
        }

        internal fun stackSize(children: List<Double>, gap: Double): Double {
            return children.sum() + gap * (children.size - 1)
        }

    }

    enum class Direction {
        HORIZONTAL,
        VERTICAL,
    }

    private fun calculate(params: SizeParams, autoDirection: Direction): Double {
        return when(type) {
            Type.PIXELS -> value
            Type.PARENT -> when(autoDirection) {
                Direction.HORIZONTAL -> value / 100.0 * params.parentWidth
                Direction.VERTICAL -> value / 100.0 * params.parentHeight
            }
            Type.PARENT_WIDTH -> value / 100.0 * params.parentWidth
            Type.PARENT_HEIGHT -> value / 100.0 * params.parentHeight
            Type.VIEWPORT -> when(autoDirection) {
                Direction.HORIZONTAL -> value / 100.0 * params.viewportWidth
                Direction.VERTICAL -> value / 100.0 * params.viewportHeight
            }
            Type.VIEWPORT_WIDTH -> value / 100.0 * params.viewportWidth
            Type.VIEWPORT_HEIGHT -> value / 100.0 * params.viewportHeight
        }
    }

    fun getPixels(params: SizeParams, autoDirection: Direction): Double {
        var result = calculate(params, autoDirection)

        calculations.forEach { (unit, operation) ->
            val value = unit.getPixels(params, autoDirection)

            result = when(operation) {
                Operation.ADD -> result + value
                Operation.SUBTRACT -> result - value
                Operation.MULTIPLY -> result * value
                Operation.DIVIDE -> result / value
            }
        }

        return result
    }

}