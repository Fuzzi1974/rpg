package game

import entities.Entity
import models.TexturedModel
import org.lwjgl.glfw.GLFW
import renderEngine.*
import shaders.StaticShader
import texture.ModelTexture
import utils.Vector3f


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

    val entity = Entity(texturedModel, Vector3f(0f, 0f, 0f), 0f, 0f, 0f, 1f)

    while (!GLFW.glfwWindowShouldClose(displayId)) {
        //entity.translatePosition(0.002f, 0f, 0f)
        //entity.increaseRotation(0f, 1f, 0f)
        renderer.init()
        shader.launch()
        renderer.render(entity, shader)
        shader.terminate()
        updateDisplay()
    }

    shader.dismiss()
    loader.dismiss()
    dismissDisplay()
}