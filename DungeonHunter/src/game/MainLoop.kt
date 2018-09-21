package game

import org.lwjgl.glfw.GLFW
import renderEngine.*


fun main(args: Array<String>) {

    createDisplay()

    val loader = Loader()
    val renderer = Renderer()

    val vertices = floatArrayOf(
            -0.5f, 0.5f, 0f,
            -0.5f, -0.5f, 0f,
            0.5f, -0.5f, 0f,
            0.5f,  -0.5f, 0f,
            0.5f, 0.5f, 0f,
            -0.5f, 0.5f, 0f
    )

    val model = loader.loadToVAO(vertices)

    while (!GLFW.glfwWindowShouldClose(displayId)) {
        renderer.init()
        renderer.render(model)
        updateDisplay()
    }

    loader.free()
    dismissDisplay()
}