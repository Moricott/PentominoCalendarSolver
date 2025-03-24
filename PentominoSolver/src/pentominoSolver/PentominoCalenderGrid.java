package pentominoSolver;

public class PentominoCalenderGrid {
	
	private static int[][] grid;
	private static String[][] shapeGrid;
	
	
	//Konstruktor:
	private PentominoCalenderGrid() {}
	
	//Statische Methoden:
	
	public static int[][] newGrid(int x, int y) {
		grid = new int[x][y];
		shapeGrid = new String[x][y];
		return grid;
	}
	
	public static int[][] newGrid(int[][] grid) {
		PentominoCalenderGrid.grid = grid;
		
		//create shapeGrid, with each 0 as null and each -1 as "blocked"
		shapeGrid = new String[grid.length][grid[0].length];
		
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (grid[i][j] == 0) {
					shapeGrid[i][j] = null;
				} else {
					shapeGrid[i][j] = "blocked";
				}
			}
		}
		
		return grid;
	}
	
	public static void placeShape(PentominoShape shape, int x, int y, int rotation) {
		//catch if grid is not initialized
		if (grid == null) {
			throw new IllegalStateException("Grid not initialized");
		}
		
		int[][] currentGrid = PentominoCalenderGrid.getGrid();
		int[][] s = shape.getRotation(rotation);
		String shapeName = shape.getName();

		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < s[0].length; j++) {
				if (s[i][j] == 1) {
					currentGrid[x + i][y + j] = 1;
					shapeGrid[x + i][y + j] = shapeName;
				}
			}
		}
		setGrid(currentGrid);
	}
	
	public static void removeShape(PentominoShape shape, int x, int y, int rotation) { 
		//catch if grid is not initialized
		if (grid == null) {
			throw new IllegalStateException("Grid not initialized");
		}
		int[][] currentGrid = PentominoCalenderGrid.getGrid(); 
		int[][] s = shape.getRotation(rotation);
		for (int i = 0; i < s.length; i++) {
		    for (int j = 0; j < s[0].length; j++) {
		        if (s[i][j] == 1) {
		            currentGrid[x + i][y + j] = 0;
		            shapeGrid[x + i][y + j] = null;
		        }
		    }
		}
		setGrid(currentGrid);
	}
	
	//Getter und Setter: 
	public static int[][] getGrid() {
		return grid;
	}

	public static void setGrid(int[][] grid) {
		PentominoCalenderGrid.grid = grid;
	}
	
	public static String[][] getShapeGrid() {
		return shapeGrid;
	}
	
}
