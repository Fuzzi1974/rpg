package renderEngine

import entities.Entity
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL13
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30
import shaders.StaticShader
import utils.Matrix4f
import utils.createTransformationMatrix


class Renderer(shader: StaticShader) {

    private val shader = shader;

    private val FOV = 70f
    private val NEAR_PLANE = 0.01f
    private val FAR_PLANE = 1000f

    private var projectionMatrix: Matrix4f = Matrix4f()


    fun init() {
        GL11.glClearColor(0.2f, 0.4f, 0.8f, 1f)
        GL11.glEnable(GL11.GL_DEPTH_TEST)
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT)
        createProjectionMatrix()
        shader.launch()
        shader.loadProjectionMatrix(projectionMatrix)
        shader.terminate()    }


    fun render(entity: Entity, shader: StaticShader) {
        val texturedModel = entity.model
        val model = texturedModel.model
        GL30.glBindVertexArray(model.vaoId)
        GL20.glEnableVertexAttribArray(0)
        GL20.glEnableVertexAttribArray(1)
        val transformationMatrix = createTransformationMatrix(entity.position, entity.rotX, entity.rotY, entity.rotZ,
                entity.scale)
        shader.loadTransformationMatrix(transformationMatrix)
        GL13.glActiveTexture(GL13.GL_TEXTURE0)
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturedModel.texture.textureId)
        GL11.glDrawElements(GL11.GL_TRIANGLES, model.vertexCount, GL11.GL_UNSIGNED_INT, 0)
        GL20.glDisableVertexAttribArray(1)
        GL20.glDisableVertexAttribArray(0)
        GL30.glBindVertexArray(0)
    }


    private fun createProjectionMatrix() {
        val aspectRatio = DISPLAY_WIDTH.toFloat() / DISPLAY_HEIGHT.toFloat()
        val yScale = (1f / Math.tan(Math.toRadians(FOV.toDouble() / 2f))).toFloat() * aspectRatio
        val xScale = yScale / aspectRatio
        val frustrumLength = FAR_PLANE - NEAR_PLANE

        projectionMatrix = Matrix4f()
        projectionMatrix.m00 = xScale
        projectionMatrix.m11 = yScale
        projectionMatrix.m22 = -((FAR_PLANE + NEAR_PLANE) / frustrumLength)
        projectionMatrix.m23 = -1f
        projectionMatrix.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustrumLength)
    }

}