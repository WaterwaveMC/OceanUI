package dev.yolocat.oceanui_test.ocean

import dev.yolocat.oceanui.Ocean
import dev.yolocat.oceanui.UI
import dev.yolocat.oceanui.Context
import dev.yolocat.oceanui.components.HStack
import dev.yolocat.oceanui.style.Color.Companion.rgb
import dev.yolocat.oceanui.style.Spacing
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.screen.Screen

class OceanScreen : Screen() {

    @Ocean
    @Suppress("FunctionName", "NAME_SHADOWING")
    fun CoolUI(ctx: Context) {
        HStack(ctx) { ctx ->
            for(i in 0 until 10) {
                HStack(ctx)
                    .style {
                        width = 50.px
                        height = 50.px
                        backgroundColor = rgb(0.0, (i + 1.0) * (1.0 / 10), 0.0)
                    }
            }
        }
            .style {
                height = 100.vp
                width = 100.vp
                gap = 10.px
                backgroundColor = rgb(255, 0, 0)
            }
    }

    private val ui = UI { ctx -> CoolUI(ctx) }
    private val renderer = NVGRenderer()

    override fun init() {
        renderer.init()
    }

    override fun removed() {
        renderer.dispose()
    }

    override fun render(mouseX: Int, mouseY: Int, tickDelta: Float) {
        ui.setMousePos(mouseX.toDouble(), mouseY.toDouble())
        ui.setScreenSize(MinecraftClient.getInstance().width.toDouble(), MinecraftClient.getInstance().height.toDouble())
        ui.render(renderer, true)
    }

}