package entities

import utils.Vector3f

class Camera {

    var position = Vector3f(0f, 0f, 0f)
        private set

    var pitch = 0f
        private set

    var yaw = 0f
        private set

    var roll = 0f
        private set


    fun move() {
    }
}