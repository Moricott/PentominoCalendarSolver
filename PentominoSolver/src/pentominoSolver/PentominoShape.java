package pentominoSolver;

import java.util.*;

public class PentominoShape {
	
	private String name;
	private List<int[][]> rotations;
	
	private int[][] shape;

	public PentominoShape(int[][] shape, String name) {
		this.shape = shape;
		this.rotations = generateRotations(shape);
		this.name = name;
	}
	
	//Methoden:
	
	private List<int[][]> generateRotations(int[][] shape) {
		List<int[][]> all  = new ArrayList<int[][]>();
		int[][] temp = shape;
		
		for (int i = 0; i < 4; i++) {
			all.add(temp);
			temp = rotate90(temp);
		}
		
		return all;
	}
	
	private int[][] rotate90(int[][] shape){
		int r = shape.length;
		int c = shape[0].length;
		int[][] temp = new int[c][r];
		
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				temp[j][r - 1 - i] = shape[i][j];
			}
		}
		
		return temp;
	}
	
	//Getter und Setter:
	public int[][] getShape() {
		return shape;
	}
	
	public List<int[][]> getRotations() {
		return rotations;
	}
	
	public int[][] getRotation(int i) {
		return rotations.get(i);
	}
	
	public String getName() {
		return name;
	}
	

}
