package dev.yolocat.oceanui.style

import kotlin.math.abs

class Color(val red: Double, val green: Double, val blue: Double, val alpha: Double) {

    override fun toString() = "Color(r=$red,g=$green,b=$blue,a=$alpha)"

    companion object {

        @JvmStatic fun rgb(red: Int, green: Int, blue: Int) = rgb(red, green, blue, 1.0)
        @JvmStatic fun rgb(red: Double, green: Double, blue: Double) = Color(red, green, blue, 1.0)

        @JvmStatic fun rgb(red: Int, green: Int, blue: Int, alpha: Double) = Color(red / 255.0, green / 255.0, blue / 255.0, alpha)
        @JvmStatic fun rgb(red: Double, green: Double, blue: Double, alpha: Double) = Color(red, green, blue, alpha)

        @JvmStatic fun hsv(hue: Double, saturation: Double, value: Double) = hsv(hue, saturation, value, 1.0)
        @JvmStatic fun hsv(hue: Int, saturation: Double, value: Double) = hsv(hue, saturation, value, 1.0)

        @JvmStatic fun hsv(hue: Double, saturation: Double, value: Double, alpha: Double): Color {
            val c = value * saturation
            val x = c * (1 - abs((hue / 60) % 2 - 1))
            val m = value - c

            val (r, g, b) = when {
                hue < 60 -> Triple(c, x, 0.0)
                hue < 120 -> Triple(x, c, 0.0)
                hue < 180 -> Triple(0.0, c, x)
                hue < 240 -> Triple(0.0, x, c)
                hue < 300 -> Triple(x, 0.0, c)
                else -> Triple(c, 0.0, x)
            }

            return Color(r + m, g + m, b + m, alpha)
        }
        @JvmStatic fun hsv(hue: Int, saturation: Double, value: Double, alpha: Double) = hsv((hue % 360.0) / 360.0, saturation, value, alpha)

        @JvmStatic fun hex(hex: String): Color {
            if(!hex.startsWith("#")) {
                if(hex.length != 6 && hex.length != 8) throw IllegalArgumentException("Invalid hex color format")
            } else {
                if(hex.length != 7 && hex.length != 9) throw IllegalArgumentException("Invalid hex color format")
            }

            if(hex.length == 7 || hex.length == 9) {
                if(hex[0] != '#') throw IllegalArgumentException("Invalid hex color format")
                else return hex(hex.substring(1))
            }

            val red = hex.substring(0, 2).toInt(16) / 255.0
            val green = hex.substring(2, 4).toInt(16) / 255.0
            val blue = hex.substring(4, 6).toInt(16) / 255.0
            val alpha = if(hex.length == 8) hex.substring(6, 8).toInt(16) / 255.0 else 1.0

            return Color(red, green, blue, alpha)
        }
    }

}