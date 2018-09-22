package models

import texture.ModelTexture


class TexturedModel(model: RawModel, texture: ModelTexture) {

    var model: RawModel = model
        private set

    var texture: ModelTexture = texture
        private set
}