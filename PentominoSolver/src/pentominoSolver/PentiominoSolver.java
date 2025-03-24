package pentominoSolver;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class PentiominoSolver {
	
	PentominoShape[] shapes = {
			new PentominoShape(new int[][] {
                { 1, 1, 1, 1 }
            }, "i"),
			new PentominoShape(new int[][] {
                { 1, 1, 1 },
                { -1, 1, -1 },
                { -1, 1, -1 }
            }, "t"),
			new PentominoShape(new int[][] { 
				{ 1, 1, 1 }, 
				{ 1, -1, -1 }, 
				{ 1, -1, -1 } 
			}, "lBig"),
			new PentominoShape(new int[][] {
				{ -1, -1, 1 },
				{ 1, 1, 1 },
                { 1, -1, -1 }
			}, "s"),
			new PentominoShape(new int[][] {
                { 1, 1, -1 },
                { 1, 1, 1 }
            }, "p"),
			new PentominoShape(new int[][] {
				{ 1, 1, 1 },
                { 1, -1, 1 }
            }, "u"),
			new PentominoShape(new int[][] {
				{ -1, 1, 1, 1 },
				{ 1, 1, -1, -1 }
			}, "zLong"),
			new PentominoShape(new int[][] {
				{ 1, 1, -1 },
				{ -1, 1, 1 }
			}, "zSmall"),
			new PentominoShape(new int[][] {
					{ 1, -1, -1 }, 
					{ 1, 1, 1 }
			}, "lsmall"),
			new PentominoShape(new int[][] {
					{ 1, -1, -1, -1 }, 
					{ 1, 1, 1, 1 }
			}, "lLong")
			
	};
	
	
	
	int[][] grid = new int[][] {
		{ 0,  0,  0,  0,  0,  0, -1},
	    { 0,  0,  0,  0,  0,  0, -1},
	    { 0,  0,  0,  0,  0,  0,  0},
	    { 0,  0,  0,  0,  0,  0,  0},
	    { 0,  0,  0,  0,  0,  0,  0},
	    { 0,  0,  0,  0,  0,  0,  0},
	    { 0,  0,  0,  0,  0,  0,  0},
	    {-1, -1, -1, -1, 0,  0,  0}
	};

	public void start() {
		drawEmptyGrid(grid, shapes);
	}
	
	public static void main(String[] args) {
		PentiominoSolver solver = new PentiominoSolver();
        solver.start();
		
//		printRotations(shapes);
		
		//print out the solution
//		int[][] solution = PentominoCalendarSolvingAlgorithm.solve(shapes);
//		if (solution != null) {
//			for (int i = 0; i < solution.length; i++) {
//				for (int j = 0; j < solution[0].length; j++) {
//					System.out.print(solution[i][j] + " ");
//				}
//				System.out.println();
//			}
//		} else {
//			System.out.println("No solution found");
//		}
		
//		System.out.println(PentominoCalendarSolvingAlgorithm.canPlaceShape(shapes[0].getShape(), 0, 0));
		
		
		
		
		
}
		
		
		//print out all rotations of all shapes
		
		static void printRotations(PentominoShape[] shapes) {
			for (PentominoShape s : shapes) {
				for (int r = 0; r < s.getRotations().size(); r++) {
					int[][] shape = s.getRotations().get(r);
					for (int i = 0; i < shape.length; i++) {
						for (int j = 0; j < shape[0].length; j++) {
							System.out.print(shape[i][j] + " ");
						}
						System.out.println();
					}
					System.out.println();
				}
			}
		}
		
		//print a 2D array
		
		static void print2DArray(int[][] array) {
			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array[0].length; j++) {
					System.out.print(array[i][j] + " ");
				}
				System.out.println();
			}
		}
		
		static void print2DArray(String[][] array) {
			for (int i = 0; i < array.length; i++) {
				for (int j = 0; j < array[0].length; j++) {
					System.out.print(array[i][j] + " ");
				}
				System.out.println();
			}
		}
		
		static void drawEmptyGrid(int[][] grid, PentominoShape[] shapes) {
		    JFrame frame = new JFrame("Pentomino Grid");
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setSize(800, 800);
		    frame.setLayout(new BorderLayout());

		    SelectBlockedCellsGridPanel gridPanel = new SelectBlockedCellsGridPanel(grid);
		    frame.add(gridPanel, BorderLayout.CENTER);

		    JButton submitButton = new JButton("Submit");
		    submitButton.addActionListener(e -> {
		    	
		    	int[][] submittedGrid = gridPanel.grid;
		    	print2DArray(submittedGrid);
		    	
		    	PentominoCalenderGrid.newGrid(submittedGrid);
		    	
		    	PentominoCalendarSolvingAlgorithm.solve(shapes);
		    	
		    	drawShapeGrid(PentominoCalenderGrid.getShapeGrid());
		    	
		    	frame.dispose();
		    	
		        System.out.println("Submit button clicked");
		    });
		    frame.add(submitButton, BorderLayout.SOUTH);

		    frame.setVisible(true);
		}
		
		static void drawShapeGrid(String[][] shapeGrid) {
	        JFrame frame = new JFrame("Pentomino Grid");
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(800, 800);
	        frame.add(new ShapeGridPanel(shapeGrid), BorderLayout.CENTER);
	        
	        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
	        topPanel.add(new JLabel("Tries: " + PentominoCalendarSolvingAlgorithm.tries));
	        topPanel.add(new JLabel("Time: " + PentominoCalendarSolvingAlgorithm.timeInS + "s"));
	        frame.add(topPanel, BorderLayout.NORTH);
	        
	        
	        frame.setVisible(true);
	    }

	    static class ShapeGridPanel extends JPanel {
	        private String[][] shapeGrid;
	        private static final int CELL_SIZE = 50;

	        public ShapeGridPanel(String[][] shapeGrid) {
	            this.shapeGrid = shapeGrid;
	        }

	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            for (int i = 0; i < shapeGrid.length; i++) {
	                for (int j = 0; j < shapeGrid[0].length; j++) {
	                    if (shapeGrid[i][j] == null) {
	                        g.setColor(Color.WHITE);
	                    } else if (shapeGrid[i][j].equals("blocked")) {
	                        g.setColor(Color.BLACK);
	                    } else {
	                        g.setColor(getColorForShape(shapeGrid[i][j]));
	                    }
	                    g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
	                    g.setColor(Color.GRAY);
	                    g.drawRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
	                }
	            }
	        }

	        private Color getColorForShape(String shapeName) {
	            switch (shapeName) {
	                case "i":
	                    return Color.RED;
	                case "t":
	                    return Color.GREEN;
	                case "lBig":
	                    return Color.BLUE;
	                case "s":
	                    return Color.YELLOW;
	                case "p":
	                    return Color.MAGENTA;
	                case "u":
	                    return Color.CYAN;
	                case "zLong":
	                    return Color.ORANGE;
	                case "zSmall":
	                    return Color.PINK;
	                case "lsmall":
	                    return Color.LIGHT_GRAY;
	                case "lLong":
	                    return Color.DARK_GRAY;
	                default:
	                    return Color.WHITE;
	            }
	        }
	        
	        
	    }
	    
	    static class SelectBlockedCellsGridPanel extends JPanel {
	        private int[][] grid;
	        private static final int CELL_SIZE = 50;

	        public SelectBlockedCellsGridPanel(int[][] grid) {
	            this.grid = grid;
	            addMouseListener(new MouseAdapter() {
	                @Override
	                public void mouseClicked(MouseEvent e) {
	                    int x = e.getY() / CELL_SIZE;
	                    int y = e.getX() / CELL_SIZE;
	                    if (x < grid.length && y < grid[0].length) {
	                        grid[x][y] = (grid[x][y] == 0) ? -1 : 0;
	                        repaint();
	                    }
	                }
	            });
	        }

	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            for (int i = 0; i < grid.length; i++) {
	                for (int j = 0; j < grid[0].length; j++) {
	                    if (grid[i][j] == 0) {
	                        g.setColor(Color.WHITE);
	                    } else {
	                        g.setColor(Color.BLACK);
	                    }
	                    g.fillRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
	                    g.setColor(Color.GRAY);
	                    g.drawRect(j * CELL_SIZE, i * CELL_SIZE, CELL_SIZE, CELL_SIZE);
	                }
	            }
	        }
	    }
}
