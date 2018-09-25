package shaders

import entities.Camera
import utils.Matrix4f
import utils.createViewMatrix


class StaticShader() : ShaderProgram("src/shaders/vertexShader.glsl",
        "src/shaders/fragmentShader.glsl") {

    var locationTransformationMatrix: Int = 0
        private set

    var locationProjectionMatrix: Int = 0
        private set

    var locationViewMatrix: Int = 0
        private set


    override fun bindAttributes() {
        super.bindAttribute(0, "position")
        super.bindAttribute(1, "textureCoords")
    }


    override fun getAllUniformLocations() {
        locationTransformationMatrix = super.getUnifomLocation("transformationMatrix")
        locationProjectionMatrix = super.getUnifomLocation("projectionMatrix")
        locationViewMatrix = super.getUnifomLocation("viewMatrix")
    }


    fun loadTransformationMatrix(matrix: Matrix4f) {
        super.loadMatrix(locationTransformationMatrix, matrix)
    }


    fun loadProjectionMatrix(projection: Matrix4f) {
        super.loadMatrix(locationProjectionMatrix, projection)
    }


    fun loadViewMatrix(camera: Camera) {
        val view = createViewMatrix(camera)
        super.loadMatrix(locationViewMatrix, view)
    }
}
