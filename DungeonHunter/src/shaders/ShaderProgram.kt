package shaders

import java.io.BufferedReader
import java.io.FileReader

import org.lwjgl.opengl.GL11
import org.lwjgl.opengl.GL20



abstract class ShaderProgram(vertexFile: String, fragmentFile: String) {

    private val programId: Int
    private val vertexShaderId: Int
    private val fragmentShaderId: Int


    init {
        vertexShaderId = loadShader(vertexFile, GL20.GL_VERTEX_SHADER)
        fragmentShaderId = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER)
        programId = GL20.glCreateProgram()
        GL20.glAttachShader(programId, vertexShaderId)
        GL20.glAttachShader(programId, fragmentShaderId)
        bindAttributes()
        GL20.glLinkProgram(programId)
        GL20.glValidateProgram(programId)
    }


    fun launch() {
        GL20.glUseProgram(programId)
    }


    fun terminate() {
        GL20.glUseProgram(0)
    }


    fun dismiss() {
        terminate()
        GL20.glDetachShader(programId, vertexShaderId)
        GL20.glDetachShader(programId, fragmentShaderId)
        GL20.glDeleteShader(vertexShaderId)
        GL20.glDeleteShader(fragmentShaderId)
        GL20.glDeleteProgram(programId)
    }


    protected abstract fun bindAttributes()


    protected fun bindAttribute(attribute: Int, variableName: String) {

        GL20.glBindAttribLocation(programId, attribute, variableName)
    }



    private fun loadShader(file: String, type: Int): Int {

        val shaderSource = StringBuilder()

        try {
            val reader = BufferedReader(FileReader(file))
            var line = reader.readLine()
            while (line != null) {
                shaderSource.append(line).append("\n")
                line = reader.readLine()
            }
            reader.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val shaderId = GL20.glCreateShader(type)
        GL20.glShaderSource(shaderId, shaderSource)
        GL20.glCompileShader(shaderId)

        if (GL20.glGetShaderi(shaderId, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            println(GL20.glGetShaderInfoLog(shaderId, 1024))
            System.exit(-1)
        }

        return shaderId
    }

}
