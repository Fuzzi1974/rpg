package utils

import org.lwjgl.ovr.OVRMatrix4f
import org.lwjgl.ovr.OVRVector3f

fun createTransformationMatrix(vector: Vector3f, rx: Float, ry: Float, rz: Float, scale: Float): Matrix4f {
    var matrix = Matrix4f()
    matrix.setIdentity()
    matrix.translate(vector)
    matrix = matrix.rotate(Math.toRadians(rx.toDouble()).toFloat(), Vector3f(1f, 0f, 0f))
    matrix = matrix.rotate(Math.toRadians(ry.toDouble()).toFloat(), Vector3f(0f, 1f, 0f))
    matrix = matrix.rotate(Math.toRadians(rz.toDouble()).toFloat(), Vector3f(0f, 0f, 1f))
    matrix.scale(Vector3f(scale, scale, scale))
    return matrix
}