#version 400 core

in vec3 position;
in vec2 textureCoordinates;

out vec2 passThroughTextureCoords;

uniform mat4 transformationMatrix;

void main(void) {

	gl_Position = vec4(position, 1.0);
	passThroughTextureCoords = textureCoordinates;
}