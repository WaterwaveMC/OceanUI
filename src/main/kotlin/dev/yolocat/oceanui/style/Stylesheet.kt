package dev.yolocat.oceanui.style

class Stylesheet {

    val Int.px get() = Unit.px(this)
    val Int.pr get() = Unit.pr(this)
    val Int.pw get() = Unit.pw(this)
    val Int.ph get() = Unit.ph(this)
    val Int.vp get() = Unit.vp(this)
    val Int.vw get() = Unit.vw(this)
    val Int.vh get() = Unit.vh(this)

    val Float.px get() = Unit.px(this)
    val Float.pr get() = Unit.pr(this)
    val Float.pw get() = Unit.pw(this)
    val Float.ph get() = Unit.ph(this)
    val Float.vp get() = Unit.vp(this)
    val Float.vw get() = Unit.vw(this)
    val Float.vh get() = Unit.vh(this)

    val Double.px get() = Unit.px(this)
    val Double.pr get() = Unit.pr(this)
    val Double.pw get() = Unit.pw(this)
    val Double.ph get() = Unit.ph(this)
    val Double.vp get() = Unit.vp(this)
    val Double.vw get() = Unit.vw(this)
    val Double.vh get() = Unit.vh(this)

    var width: Unit? = null
    var height: Unit? = null

    var gap: Unit? = null

    var paddingTop: Unit? = null
    var paddingBottom: Unit? = null
    var paddingLeft: Unit? = null
    var paddingRight: Unit? = null

    fun padding(top: Unit, bottom: Unit, left: Unit, right: Unit) {
        paddingTop = top
        paddingBottom = bottom
        paddingLeft = left
        paddingRight = right
    }

    fun padding(vertical: Unit, horizontal: Unit) {
        paddingTop = vertical
        paddingBottom = vertical
        paddingLeft = horizontal
        paddingRight = horizontal
    }

    fun padding(all: Unit) {
        paddingTop = all
        paddingBottom = all
        paddingLeft = all
        paddingRight = all
    }

    var marginTop: Unit? = null
    var marginBottom: Unit? = null
    var marginLeft: Unit? = null
    var marginRight: Unit? = null

    fun margin(top: Unit, bottom: Unit, left: Unit, right: Unit) {
        marginTop = top
        marginBottom = bottom
        marginLeft = left
        marginRight = right
    }

    fun margin(vertical: Unit, horizontal: Unit) {
        marginTop = vertical
        marginBottom = vertical
        marginLeft = horizontal
        marginRight = horizontal
    }

    fun margin(all: Unit) {
        marginTop = all
        marginBottom = all
        marginLeft = all
        marginRight = all
    }

    var backgroundColor: Color? = null

}