#version 400 core

in vec2 passTextureCoords;

out vec4 finalizedColor;

uniform sampler2D textureSampler;


void main(void) {
    finalizedColor = texture(textureSampler, passTextureCoords);
 }