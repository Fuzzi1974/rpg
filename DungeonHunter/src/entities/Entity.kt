package entities

import models.TexturedModel
import utils.Vector3f


class Entity( model: TexturedModel, position: Vector3f, rotX: Float, rotY: Float, rotZ: Float, scale: Float) {

    var model: TexturedModel = model
    var position: Vector3f = position
    var rotX: Float = rotX
    var rotY: Float = rotY
    var rotZ: Float = rotZ
    var scale: Float = scale


    fun translatePosition(dx: Float, dy: Float, dz: Float) {
        position.x += dx
        position.y += dy
        position.z += dz
    }


    fun increaseRotation(dx: Float, dy: Float, dz: Float) {
        rotX += dx;
        rotY += dy;
        rotZ += dz;
    }
}
