package game

import jdk.dynalink.beans.StaticClass
import org.lwjgl.glfw.GLFW
import renderEngine.*
import shaders.StaticShader


fun main(args: Array<String>) {

    createDisplay()

    val loader = Loader()
    val renderer = Renderer()
    val shader = StaticShader()

    val vertices = floatArrayOf(
            -0.5f, 0.5f, 0f,
            -0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
            0.5f, 0.5f, 0f
    )

    val indices = intArrayOf(
            0, 1, 3,
            3, 1, 2
    )

    val model = loader.loadToVAO(vertices, indices)

    while (!GLFW.glfwWindowShouldClose(displayId)) {
        renderer.init()
        shader.launch()
        renderer.render(model)
        shader.terminate()
        updateDisplay()
    }

    shader.dismiss()
    loader.dismiss()
    dismissDisplay()
}