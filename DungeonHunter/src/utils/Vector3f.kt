package utils

import org.lwjgl.ovr.OVRVector3f

class Vector3f() {

    var x: Float = 0f
    var y: Float = 0f
    var z: Float = 0f

    constructor(x: Float, y: Float, z: Float) : this() {
        this.x = x
        this.y = y
        this.z = z
    }

    constructor(vector: OVRVector3f): this() {
        this.x = vector.x()
        this.y  = vector.y()
        this.z = vector.z()
    }
}