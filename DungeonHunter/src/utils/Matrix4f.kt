package utils

import org.lwjgl.BufferUtils
import org.lwjgl.ovr.OVRMatrix4f
import org.lwjgl.ovr.OVRVector3f
import java.nio.FloatBuffer


class Matrix4f() {

    var m00 = 0f
    var m01 = 0f
    var m02 = 0f
    var m03 = 0f
    var m10 = 0f
    var m11 = 0f
    var m12 = 0f
    var m13 = 0f
    var m20 = 0f
    var m21 = 0f
    var m22 = 0f
    var m23 = 0f
    var m30 = 0f
    var m31 = 0f
    var m32 = 0f
    var m33 = 0f


    constructor(source: Matrix4f): this() {
        m00 = source.m00;
        m01 = source.m01;
        m02 = source.m02;
        m03 = source.m03;
        m10 = source.m10;
        m11 = source.m11;
        m12 = source.m12;
        m13 = source.m13;
        m20 = source.m20;
        m21 = source.m21;
        m22 = source.m22;
        m23 = source.m23;
        m30 = source.m30;
        m31 = source.m31;
        m32 = source.m32;
        m33 = source.m33;
    }


    fun setIdentity() {
        m00 = 1f;
        m11 = 1f;
        m22 = 1f;
        m33 = 1f;
    }


    fun translate(vec: Vector3f) {
        m30 += m00 * vec.x + m10 * vec.y + m20 * vec.z
        m31 += m01 * vec.x + m11 * vec.y + m21 * vec.z
        m32 += m02 * vec.x + m12 * vec.y + m22 * vec.z
        m33 += m03 * vec.x + m13 * vec.y + m23 * vec.z
    }

    fun rotate(angle: Float, axis: Vector3f): Matrix4f {
        var dest = Matrix4f(this)
        val c = Math.cos(angle.toDouble()).toFloat()
        val s = Math.sin(angle.toDouble()).toFloat()
        val oneminusc = 1.0f - c
        val xy = axis.x * axis.y
        val yz = axis.y * axis.z
        val xz = axis.x * axis.z
        val xs = axis.x * s
        val ys = axis.y* s
        val zs = axis.z * s

        val f00 = axis.x * axis.x * oneminusc + c
        val f01 = xy * oneminusc + zs
        val f02 = xz * oneminusc - ys
        val f10 = xy * oneminusc - zs
        val f11 = axis.y * axis.y * oneminusc + c
        val f12 = yz * oneminusc + xs
        val f20 = xz * oneminusc + ys
        val f21 = yz * oneminusc - xs
        val f22 = axis.z * axis.z * oneminusc + c

        val t00 = m00 * f00 + m10 * f01 + m20 * f02
        val t01 = m01 * f00 + m11 * f01 + m21 * f02
        val t02 = m02 * f00 + m12 * f01 + m22 * f02
        val t03 = m03 * f00 + m13 * f01 + m23 * f02
        val t10 = m00 * f10 + m10 * f11 + m20 * f12
        val t11 = m01 * f10 + m11 * f11 + m21 * f12
        val t12 = m02 * f10 + m12 * f11 + m22 * f12
        val t13 = m03 * f10 + m13 * f11 + m23 * f12

        dest.m20 = m00 * f20 + m10 * f21 + m20 * f22
        dest.m21 = m01 * f20 + m11 * f21 + m21 * f22
        dest.m22 = m02 * f20 + m12 * f21 + m22 * f22
        dest.m23 = m03 * f20 + m13 * f21 + m23 * f22
        dest.m00 = t00
        dest.m01 = t01
        dest.m02 = t02
        dest.m03 = t03
        dest.m10 = t10
        dest.m11 = t11
        dest.m12 = t12
        dest.m13 = t13

        return dest
    }


    fun scale(vector: Vector3f) {
        m00 *= vector.x
        m01 *= vector.x
        m02 *= vector.x
        m03 *= vector.x
        m10 *= vector.y
        m11 *= vector.y
        m12 *= vector.y
        m13 *= vector.y
        m20 *= vector.z
        m21 *= vector.z
        m22 *= vector.z
        m23 *= vector.z
    }


    fun store(buffer: FloatBuffer) {
        buffer.put(m00)
        buffer.put(m01)
        buffer.put(m02)
        buffer.put(m03)
        buffer.put(m10)
        buffer.put(m11)
        buffer.put(m12)
        buffer.put(m13)
        buffer.put(m20)
        buffer.put(m21)
        buffer.put(m22)
        buffer.put(m23)
        buffer.put(m30)
        buffer.put(m31)
        buffer.put(m32)
        buffer.put(m33)
    }

}