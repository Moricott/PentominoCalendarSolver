package pentominoSolver;

public class PentominoCalendarSolvingAlgorithm {
	
	public static int tries;
	public static int timeInS;
	
	public static int[][] solve(PentominoShape[] shapes) {
		
		tries = 0;
		timeInS = 0;
		
		int timeCurrent = (int) System.currentTimeMillis();
		
		
		 if (backtrack(shapes, 0)) {
		 	System.out.println("Solution found");
		 	timeInS = ((int) System.currentTimeMillis() - timeCurrent) / 1000;
            return PentominoCalenderGrid.getGrid();
		 }
		 System.out.println("No solution found");
		 return null;
	}
	
	static boolean backtrack(PentominoShape[] shapes, int shapeIndex) {
		 if (shapeIndex == shapes.length) {
	            return true; // All shapes placed successfully
	        }

	        PentominoShape shape = shapes[shapeIndex];
	        for (int rotation = 0; rotation < shape.getRotations().size(); rotation++) {
	            int[][] rotatedShape = shape.getRotation(rotation);
	            for (int x = 0; x < PentominoCalenderGrid.getGrid().length; x++) {
	                for (int y = 0; y < PentominoCalenderGrid.getGrid()[0].length; y++) {
	                    if (canPlaceShape(rotatedShape, x, y)) {
	                        PentominoCalenderGrid.placeShape(shape, x, y, rotation);
	                        tries++;
	                        if (backtrack(shapes, shapeIndex + 1)) {
	                            return true;
	                        }
	                        PentominoCalenderGrid.removeShape(shape, x, y, rotation);
	                    }
	                }
	            }
	        }
	        return false; // No valid placement found
	}
	
	static boolean canPlaceShape(int[][] shape, int x, int y) {
		 for (int i = 0; i < shape.length; i++) {
		        for (int j = 0; j < shape[0].length; j++) {
		        	if (shape[i][j]!= -1) {
		        		if (x + i >= PentominoCalenderGrid.getGrid().length || y + j >= PentominoCalenderGrid.getGrid()[0].length || PentominoCalenderGrid.getGrid()[x + i][y + j] != 0) {
		        			return false;
		        		}
		        	}
		        }
		 }
		 return true;
	}


}
