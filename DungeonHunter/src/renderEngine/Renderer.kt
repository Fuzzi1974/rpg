package renderEngine

import entities.Entity
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL13
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30
import shaders.StaticShader
import utils.createTransformationMatrix


class Renderer {

    fun init() {
        GL11.glClearColor(0.2f, 0.4f, 0.8f, 1f)
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT)
    }

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

}