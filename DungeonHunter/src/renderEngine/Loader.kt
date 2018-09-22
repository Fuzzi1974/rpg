package renderEngine


import models.RawModel
import org.lwjgl.BufferUtils
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL11.GL_FLOAT
import org.lwjgl.opengl.GL15
import org.lwjgl.opengl.GL20
import org.lwjgl.opengl.GL30
import texture.loadTextureToBuffer
import java.nio.FloatBuffer
import java.nio.IntBuffer




class Loader {

    private var vaos = ArrayList<Int>()
    private var vbos = ArrayList<Int>()
    private var textures = ArrayList<Int>()


    fun loadToVAO(positions: FloatArray, textureCoords: FloatArray, indices: IntArray): RawModel {
        val vaoID = createVAO()
        bindIndexBuffer(indices)
        vaos.add(vaoID)
        storeDataInAtttubutesList(0, 3, positions)
        storeDataInAtttubutesList(1, 2, textureCoords)
        dismissVAO()
        return RawModel(vaoID, indices.count())
    }

    fun loadTexture(fileName: String): Int {
        val filePath = "res/$fileName.png"
        val textureId = loadTextureToBuffer(filePath)
        textures.add(textureId)
        return textureId
    }

    fun dismiss() {
        for (vao in vaos) {
            GL30.glDeleteVertexArrays(vao)
        }
        for (vbo in vbos) {
            GL15.glDeleteBuffers(vbo)
        }
        for (texture in textures) {
            GL11.glDeleteTextures(texture)
        }
    }


    private fun createVAO(): Int {
        val vaoId = GL30.glGenVertexArrays()
        GL30.glBindVertexArray(vaoId)
        return vaoId
    }


    private fun storeDataInAtttubutesList(attributeNumner: Int, coordinateSize: Int, data: FloatArray) {
        val vboId = GL15.glGenBuffers()
        vbos.add(vboId)
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId)
        val buffer = storeDataInFloatBuffer(data)
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW)
        GL20.glVertexAttribPointer(attributeNumner, coordinateSize, GL_FLOAT, false, 0, 0)
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0)

    }


    private fun dismissVAO() {
        GL30.glBindVertexArray(0)
    }


    private fun bindIndexBuffer(indices: IntArray) {
        val vboId = GL15.glGenBuffers()
        vbos.add(vboId)
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboId)
        val buffer = storeDataInIntBuffer(indices)
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW)
    }


    private fun storeDataInIntBuffer(data: IntArray): IntBuffer {
        val buffer = BufferUtils.createIntBuffer(data.count())
        buffer.put(data)
        buffer.flip()
        return buffer
    }


    private fun storeDataInFloatBuffer(data: FloatArray): FloatBuffer {
        val buffer = BufferUtils.createFloatBuffer(data.count())
        buffer.put(data)
        buffer.flip()
        return buffer
    }

}