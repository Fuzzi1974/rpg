package renderEngine;

import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL30;


public class Loader {

	public RawModel loadToVao(float[] positions) {
		
		int vaoId = createVao();
		storeDataInAttributeList(0, positions);
		unbindVao();
		
		return new RawModel(vaoId, positions.length / 3);
	}

	
	private int createVao() {

		int vaoId = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vaoId);
		
		return vaoId;
	}
	
	
	private void storeDataInAttributeList(int attributeNumber, float[] data) {
		
		int vboId = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboId);
	}
	
	
	private void unbindVao() {
		
		GL30.glBindVertexArray(0);
	}
	
}
