package game

import entities.Camera
import entities.Entity
import input.Keyboard
import models.TexturedModel
import org.lwjgl.glfw.GLFW
import org.lwjgl.glfw.GLFWKeyCallback
import renderEngine.*
import shaders.StaticShader
import texture.ModelTexture
import utils.Vector3f
import org.lwjgl.glfw.GLFW.glfwSetKeyCallback
import java.security.Key
import kotlin.coroutines.experimental.ContinuationInterceptor


fun main(args: Array<String>) {

    createDisplay()

    val keyCallback = Keyboard()
    glfwSetKeyCallback(displayId, keyCallback)

    val loader = Loader()
    val shader = StaticShader()
    val renderer = Renderer(shader)

    val vertices = floatArrayOf(
            -0.5f,0.5f,-0.5f,
            -0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,0.5f,-0.5f,

            -0.5f,0.5f,0.5f,
            -0.5f,-0.5f,0.5f,
            0.5f,-0.5f,0.5f,
            0.5f,0.5f,0.5f,

            0.5f,0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,0.5f,
            0.5f,0.5f,0.5f,

            -0.5f,0.5f,-0.5f,
            -0.5f,-0.5f,-0.5f,
            -0.5f,-0.5f,0.5f,
            -0.5f,0.5f,0.5f,

            -0.5f,0.5f,0.5f,
            -0.5f,0.5f,-0.5f,
            0.5f,0.5f,-0.5f,
            0.5f,0.5f,0.5f,

            -0.5f,-0.5f,0.5f,
            -0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,0.5f
    )

    val indices = intArrayOf(
            0,1,3,
            3,1,2,
            4,5,7,
            7,5,6,
            8,9,11,
            11,9,10,
            12,13,15,
            15,13,14,
            16,17,19,
            19,17,18,
            20,21,23,
            23,21,22
    )

    val textureCoords = floatArrayOf(
            0f,0f,
            0f,1f,
            1f,1f,
            1f,0f,
            0f,0f,
            0f,1f,
            1f,1f,
            1f,0f,
            0f,0f,
            0f,1f,
            1f,1f,
            1f,0f,
            0f,0f,
            0f,1f,
            1f,1f,
            1f,0f,
            0f,0f,
            0f,1f,
            1f,1f,
            1f,0f,
            0f,0f,
            0f,1f,
            1f,1f,
            1f,0f
    )

    val model = loader.loadToVAO(vertices, textureCoords, indices)
    val texture = ModelTexture(loader.loadTexture("pebbles"))
    val texturedModel = TexturedModel(model, texture)

    val entity = Entity(texturedModel, Vector3f(0f, 0f, -5f), 0f, 0f, 0f, 1f)
    val camera = Camera(keyCallback)

    while (!GLFW.glfwWindowShouldClose(displayId)) {
        //entity.translatePosition(0f, 0f, -0.2f)
        entity.increaseRotation(0.5f, 0.3f, 0.1f)
        renderer.init()
        shader.launch()
        shader.loadViewMatrix(camera)
        renderer.render(entity, shader)
        shader.terminate()
        updateDisplay()
        camera.move()
    }

    shader.dismiss()
    loader.dismiss()
    dismissDisplay()
}
