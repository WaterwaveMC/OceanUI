package dev.yolocat.oceanui_test.ocean

import dev.yolocat.oceanui.Renderer
import dev.yolocat.oceanui.style.Color
import net.minecraft.client.MinecraftClient
import org.lwjgl.nanovg.NVGColor
import org.lwjgl.nanovg.NanoVG
import org.lwjgl.nanovg.NanoVGGL2

class NVGRenderer : Renderer {

    private var ctx: Long = -1L

    fun init() {
        ctx = NanoVGGL2.nvgCreate(NanoVGGL2.NVG_ANTIALIAS or NanoVGGL2.NVG_STENCIL_STROKES)
    }

    fun dispose() {
        NanoVGGL2.nvgDelete(ctx)
    }

    override fun beginFrame() {
        ConsoleRenderer.beginFrame()
        NanoVG.nvgBeginFrame(
            ctx,
            MinecraftClient.getInstance().width.toFloat(),
            MinecraftClient.getInstance().height.toFloat(),
            2f
        )
    }

    override fun endFrame() {
        ConsoleRenderer.endFrame()
        NanoVG.nvgEndFrame(ctx)
    }

    override fun drawRect(x: Double, y: Double, width: Double, height: Double, color: Color) {
        ConsoleRenderer.drawRect(x, y, width, height, color)
        NanoVG.nvgBeginPath(ctx)
        NanoVG.nvgRect(ctx, x.toFloat(), y.toFloat(), width.toFloat(), height.toFloat())
        val nvgColor = NVGColor.calloc().r(color.red.toFloat()).g(color.green.toFloat()).b(color.blue.toFloat()).a(color.alpha.toFloat())
        NanoVG.nvgFillColor(ctx, nvgColor)
        nvgColor.free()
        NanoVG.nvgFill(ctx)
        NanoVG.nvgClosePath(ctx)
    }

    override fun drawText(x: Double, y: Double, text: String, color: Color) {
        ConsoleRenderer.drawText(x, y, text, color)
        TODO("Not yet implemented")
    }

}
