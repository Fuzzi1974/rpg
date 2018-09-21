package shaders;

import org.lwjgl.util.vector.Matrix4f;

public class StaticShader extends ShaderProgram {

	
	private static final String VERTEX_FILE = "src/shaders/vertexShader.glsl";
	private static final String FRAGMENT_FILE = "src/shaders/fragmentShader.glsl";
	
	private int transformationMatrixLocation;
	
	
	public StaticShader() {
		
		super(VERTEX_FILE, FRAGMENT_FILE);
	}

	
	@Override
	protected void bindAttributes() {

		super.bindAttribute(0, "position");
		super.bindAttribute(1, "textureCoordinates");
	}


	@Override
	protected void getAllUniformLocations() {
		
		transformationMatrixLocation = super.getUniformLocation("transfomationMatrix");
	}
	
	
	public void injectTransformationMatrix(Matrix4f matrix) {
		super.loadMatrix(transformationMatrixLocation, matrix);
	}

}
