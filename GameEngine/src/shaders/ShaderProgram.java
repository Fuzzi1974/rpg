package shaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;


public abstract class ShaderProgram {
	
	private int programId;
	private int vertexShaderId;
	private int fragmentShaderId;
	
	private static FloatBuffer matrixBuffer = BufferUtils.createFloatBuffer(16);
	
	
	public ShaderProgram(String vertexFile, String fragmentFile) {

		vertexShaderId = loadShader(vertexFile, GL20.GL_VERTEX_SHADER);
		fragmentShaderId = loadShader(fragmentFile, GL20.GL_FRAGMENT_SHADER);
		programId = GL20.glCreateProgram();
		GL20.glAttachShader(programId, vertexShaderId);
		GL20.glAttachShader(programId, fragmentShaderId);
		bindAttributes();
		GL20.glLinkProgram(programId);
		GL20.glValidateProgram(programId);
		getAllUniformLocations();
	}
	
	
	protected abstract void getAllUniformLocations();
	 
	
	protected int getUniformLocation(String uniformName) {
		return GL20.glGetUniformLocation(programId, uniformName);
	}
	 
	
	public void launch() {
		
		GL20.glUseProgram(programId);
	}
	
	
	public void terminate() {
		
		GL20.glUseProgram(0);
	}
	
	
	public void dismiss() {
		
		terminate();
		GL20.glDetachShader(programId, vertexShaderId);
		GL20.glDetachShader(programId, fragmentShaderId);
		GL20.glDeleteShader(vertexShaderId);
		GL20.glDeleteShader(fragmentShaderId);
		GL20.glDeleteProgram(programId);
	}
	
	
	protected abstract void bindAttributes();
	
	
	protected void bindAttribute(int attribute, String variableName) {
		
		GL20.glBindAttribLocation(programId, attribute, variableName);
	}
	
	
	protected void loadFloat(int location, float value) {
		GL20.glUniform1f(location, value);
	}
	
	
	protected void loadVector(int location, Vector3f vector) {
		GL20.glUniform3f(location, vector.x, vector.y, vector.z);
	}
	
	
	protected void loadMatrix(int location, Matrix4f matrix) {
		matrix.store(matrixBuffer);
		matrixBuffer.flip();
		GL20.glUniformMatrix4(location, false, matrixBuffer);
	}
	
	
	protected void loadBoolean(int location, boolean value) {
		float glValue = 0f;
		if (value) {
			glValue = 1f;
		}
		GL20.glUniform1f(location, glValue);
	}
	
	
	private static int loadShader(String file, int type) {
		
		StringBuilder shaderSource = new StringBuilder();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while ((line = reader.readLine()) != null) {
				shaderSource.append(line).append("\n");
			}
			reader.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		int shaderId = GL20.glCreateShader(type);
		GL20.glShaderSource(shaderId, shaderSource);
		GL20.glCompileShader(shaderId);
		
		if (GL20.glGetShaderi(shaderId, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
			System.out.println(GL20.glGetShaderInfoLog(shaderId, 1024));
			System.exit(-1);
		}
		
		return shaderId;
	}

}
