package renderEngine

import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30


class Renderer {

    fun init() {
        GL11.glClearColor(0f, 0f, 1f, 1f);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }

    fun render(model: RawModel) {
        GL30.glBindVertexArray(model.vaoId)
        GL20.glEnableVertexAttribArray(0)
        GL11.glDrawArrays(GL11.GL_TRIANGLES, 0, model.vertexCount)
        GL20.glDisableVertexAttribArray(0)
        GL30.glBindVertexArray(0)
    }

}