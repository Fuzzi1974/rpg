package game;


import org.lwjgl.opengl.Display;

import models.RawModel;
import models.TexturedModel;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;



public class MainGameLoop {
	
	
	public static void main(String[] args) {

		DisplayManager.createDisplay();
		Loader loader = new Loader();
		Renderer renderer = new Renderer();
		StaticShader shader = new StaticShader();
		
		float[] vertices = {
			-0.5f, 0.5f, 0f,
			-0,5f, -0.5f, 0f,
			0.5f, -0.5f, 0f,
			0.5f, 0.5f, 0f
		};

		int[] indices = {
			0,1,3,
			3,1,2
		};
		
		float[] textureCoordinates = {
				0,0,
				0,1,
				1,1,
				1,0
		};
		
		RawModel model = loader.loadToVao(vertices, textureCoordinates, indices);
		ModelTexture texture = new ModelTexture(loader.loadTexture("pebbles"));
		TexturedModel texturedModel = new TexturedModel(model, texture);
		
		while(!Display.isCloseRequested()) {
			
			renderer.init();
			shader.launch();
			renderer.render(texturedModel);
			shader.terminate();
			DisplayManager.updateDisplay();
		}
		
		shader.dismiss();
		loader.dismiss();
		DisplayManager.dismissDisplay();
	}

}
