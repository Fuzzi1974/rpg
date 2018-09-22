package shaders

class StaticShader() : ShaderProgram("src/shaders/vertexShader.glsl",
        "src/shaders/fragmentShader.glsl") {


    override fun bindAttributes() {
        super.bindAttribute(0, "position")
        super.bindAttribute(1, "textureCoords")
    }




}