package entities


import input.Keyboard
import org.lwjgl.glfw.GLFW
import utils.Vector3f


class Camera(keyboard: Keyboard) {

    var position = Vector3f(0f, 0f, 0f)
        private set

    var pitch = 0f
        private set

    var yaw = 0f
        private set

    var roll = 0f
        private set

    val keyboard = keyboard


    fun move() {
        if (keyboard.isKeyDown(GLFW.GLFW_KEY_W)) {
            this.position.z -= 0.02f
        }
        else if (keyboard.isKeyDown(GLFW.GLFW_KEY_S)) {
            this.position.z += 0.02f
        }
        else if (keyboard.isKeyDown(GLFW.GLFW_KEY_A)) {
            this.yaw -= 0.1f
        }
        else if (keyboard.isKeyDown(GLFW.GLFW_KEY_D)) {
            this.yaw += 0.1f
        }
    }
}