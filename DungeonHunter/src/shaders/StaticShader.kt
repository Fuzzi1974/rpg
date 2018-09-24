package shaders

import org.lwjgl.ovr.OVRMatrix4f
import utils.Matrix4f

class StaticShader() : ShaderProgram("src/shaders/vertexShader.glsl",
        "src/shaders/fragmentShader.glsl") {

    var locationTransformationMatrix: Int = 0
        private set

    override fun bindAttributes() {
        super.bindAttribute(0, "position")
        super.bindAttribute(1, "textureCoords")
    }


    override fun getAllUniformLocations() {
        locationTransformationMatrix = super.getUnifomLocation("transformationMatrix")
        println(locationTransformationMatrix)
    }


    fun loadTransformationMatrix(matrix: Matrix4f) {
        super.loadMatrix(locationTransformationMatrix, matrix)
    }
}