package texture

import de.matthiasmann.twl.utils.PNGDecoder
import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL30
import java.io.File
import java.io.FileInputStream
import java.nio.ByteBuffer


fun loadTextureToBuffer(filepath: String): Int {
    val file = File(filepath)
    val inStream = FileInputStream(file)
    val decoder = PNGDecoder(inStream)

    val buffer = ByteBuffer.allocateDirect(4 * decoder.width * decoder.height)
    decoder.decode(buffer, decoder.width * 4, PNGDecoder.Format.RGBA)
    buffer.flip()

    val textureId = GL11.glGenTextures()
    GL11.glBindTexture(GL11.GL_TEXTURE_2D, textureId)
    GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT)
    GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT)

    GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST)
    GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST)
    GL30.glGenerateMipmap(textureId)

    GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, decoder.width, decoder.height, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer)

    GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0)

    return textureId
}