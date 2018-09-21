package renderEngine

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL


val DISPLAY_WIDTH = 1280
val DISPLAY_HEIGHT = 720
var displayId: Long = 0


fun createDisplay() {
    GLFWErrorCallback.createPrint(System.err).set()

    if (!glfwInit()) {
        val exception = IllegalStateException("Unable to initialize GLFW")
        throw exception
    }

    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3)
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2)
    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE)
    glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE)

    displayId = glfwCreateWindow(DISPLAY_WIDTH, DISPLAY_HEIGHT, "DungeonHunter", 0, 0)
    if (displayId == null) {
        glfwTerminate()
        val exception = RuntimeException("Failed to create GLFW window")
        throw exception
    }

    glfwMakeContextCurrent(displayId)
    glfwSwapInterval(1)
    glfwShowWindow(displayId)
    GL.createCapabilities()

}


fun updateDisplay() {
    glfwSwapBuffers(displayId)
    glfwPollEvents()
}


fun dismissDisplay() {
    glfwDestroyWindow(displayId)
}