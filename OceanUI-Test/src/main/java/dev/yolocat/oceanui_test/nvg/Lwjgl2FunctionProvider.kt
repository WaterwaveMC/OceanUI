package dev.yolocat.oceanui_test.nvg

import org.lwjgl.opengl.GLContext
import org.lwjgl.system.FunctionProvider
import java.lang.UnsupportedOperationException
import java.lang.reflect.Method
import java.nio.ByteBuffer

class Lwjgl2FunctionProvider : FunctionProvider {

    private val getFunctionAddressMethod: Method

    init {
        try {
            getFunctionAddressMethod = GLContext::class.java.getDeclaredMethod("getFunctionAddress", String::class.java)
            getFunctionAddressMethod.isAccessible = true
        } catch(e: Exception) {
            throw RuntimeException(e)
        }
    }

    override fun getFunctionAddress(functionName: CharSequence): Long {
        try {
            return getFunctionAddressMethod.invoke(null, functionName.toString()) as Long
        } catch(e: Exception) {
            throw RuntimeException(e)
        }
    }

    override fun getFunctionAddress(bytebuffer: ByteBuffer): Long {
        throw UnsupportedOperationException()
    }

}