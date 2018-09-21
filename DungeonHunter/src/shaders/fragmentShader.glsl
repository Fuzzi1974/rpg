#version 400 core

in vec3 color;

out vec4 finalizedColor;


void main(void) {
    finalizedColor = vec4(color, 0.8);
}