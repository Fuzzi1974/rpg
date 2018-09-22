package game

import models.TexturedModel
import org.lwjgl.glfw.GLFW
import renderEngine.*
import shaders.StaticShader
import texture.ModelTexture


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

    val textureCoords = floatArrayOf(
        0f, 0f,
        0f, 1f,
        1f, 1f ,
        1f, 0f
    )

    val model = loader.loadToVAO(vertices, textureCoords, indices)
    val texture = ModelTexture(loader.loadTexture("pebbles"))
    val texturedModel = TexturedModel(model, texture)

    while (!GLFW.glfwWindowShouldClose(displayId)) {
        renderer.init()
        shader.launch()
        renderer.render(texturedModel)
        shader.terminate()
        updateDisplay()
    }

    shader.dismiss()
    loader.dismiss()
    dismissDisplay()
}