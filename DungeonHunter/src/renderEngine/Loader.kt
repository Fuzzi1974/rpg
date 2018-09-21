package renderEngine

import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11.GL_FLOAT
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30
import java.nio.FloatBuffer


class Loader {

    private var vaos = ArrayList<Int>()
    private var vbos = ArrayList<Int>()


    fun loadToVAO(positions: FloatArray): RawModel {
        val vaoID = createVAO()
        vaos.add(vaoID)
        storeDataInAtttubutesList(0, positions)
        dismissVAO()
        return RawModel(vaoID, positions.count() / 3)
    }

    fun free() {
        for (vao in vaos) {
            GL30.glDeleteVertexArrays(vao)
        }
        for (vbo in vbos) {
            GL15.glDeleteBuffers(vbo)
        }
    }


    private fun createVAO(): Int {
        val vaoId = GL30.glGenVertexArrays()
        GL30.glBindVertexArray(vaoId);
        return vaoId
    }


    private fun storeDataInAtttubutesList(attributeNumner: Int, data: FloatArray) {
        val vboId = GL15.glGenBuffers();
        vbos.add(vboId)
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId)
        val buffer = storeDataInFloatBuffer(data)
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW)
        GL20.glVertexAttribPointer(attributeNumner, 3, GL_FLOAT, false, 0, 0)
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0)

    }


    private fun dismissVAO() {
        GL30.glBindVertexArray(0)
    }


    private fun storeDataInFloatBuffer(data: FloatArray): FloatBuffer {
        var buffer = BufferUtils.createFloatBuffer(data.count())
        buffer.put(data)
        buffer.flip()
        return buffer
    }

}