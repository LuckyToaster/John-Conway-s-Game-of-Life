import static java.awt.Toolkit.getDefaultToolkit;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.Timer;

public class CellGrid extends JFrame implements KeyListener {
	
	private final int pixelW, pixelH, rows, cols;
	private int updateInterval;
	private boolean fullscreen, pause;
	private Color bg, fg, aliveColor;
	private Cell[][] grid;
	private Timer timer;
	
	/**
	 * @param title - Title of the graphical window
	 * @param cellSize - width and height of cells in the grid  
	 * @param updateInterval - milliseconds between each update
	 * @param fullscreen - open graphical window as fulscreen
	 * @param bg - background color for the cells
	 * @param fg - foreground color for the cells
	 * @param aliveColor - color for when a cell's 'alive' attribute is set to true
	 */
	CellGrid (String title, int cellSize, int updateInterval, boolean fullscreen, 
			 Color bg, Color fg, Color aliveColor) {
		super(title);
		this.updateInterval = updateInterval;
		this.fullscreen = fullscreen;
		this.bg = bg;
		this.fg = fg;
		this.aliveColor = aliveColor;
		this.pause = true;
		this.pixelW = (int) getDefaultToolkit().getScreenSize().getWidth();
		this.pixelH = (int) getDefaultToolkit().getScreenSize().getHeight();
		this.rows = pixelH / cellSize;
		this.cols = pixelW / cellSize;

		initializeFrame();
		initializeGrid();
		initializeTimer();
	}
	
	CellGrid(String title) {
		this(title, 20, 100, false, Color.black, Color.gray, Color.white);
	}
	
	CellGrid() {
		this("CellGrid", 20, 100, false, Color.black, Color.gray, Color.white);
	}

	/**
	 * Initialize the frame with default configuration.
	 * Also adds a KeyListener that will toggle fullscreen and pause with 'f' 
	 * and 'p' keys
	 */
	private void initializeFrame() {
		setVisible(true);
		getContentPane().setBackground(bg);
		getContentPane().setForeground(fg);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new GridLayout(rows, cols));
		setSize((int) (pixelW / 2), (int) (pixelH / 2));

		addKeyListener(new KeyAdapter() {
			@Override public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();

				if (key == KeyEvent.VK_P) {

					if (pause) timer.start();
					else timer.stop();
					pause = !pause;

				} if (key == KeyEvent.VK_F) {

					if (!fullscreen) setExtendedState(MAXIMIZED_BOTH);
					else setExtendedState(NORMAL);
					fullscreen = !fullscreen;

				} else if (key == KeyEvent.VK_ESCAPE) dispose();
			}
		});

		if (fullscreen) setExtendedState(MAXIMIZED_BOTH);
	}
	
	/**
	 * Instantiates the cells and adds them to the CellGrid
	 */
	private void initializeGrid() {
		grid = new Cell[rows][cols];
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new Cell(bg, fg, aliveColor);
				add(grid[i][j]);
			}
	}
	
	/**
	 * Instantiates the timer, the timer will execute the 'timerOperations' 
	 * method, which can be overridden to customize the CellGrid's behaviour
	 * @see CellGrid#timerOperations {@link CellGrid.timerOperations()}
	 */
	private void initializeTimer() {
		timer = new Timer(updateInterval, new ActionListener() {
			@Override public void actionPerformed(ActionEvent e) {
				timerOperations();
			}
		});
	}
	
	/**
	 * Override this method to customize the CellGrid's behaviour. This method 
	 * is executed by the timer inside the 'initializeTimer' method
	 * @see CellGrid#initializeTimer {@link CellGrid.initializeTimer()}
	 */
	public void timerOperations() {
		// TODO
	}
	
	/**
	 * Update's the cellGrid's cell states and redraws them
	 *  @param nextState - 2D int array to update the cellGrid to
	 *  @see Cell#update {@link Cell.update()}
	 */
	public void update(int[][] nextState) {
		if (nextState.length == rows && nextState[0].length == cols) {
			for (int row = 0; row < nextState.length; row++)
				for (int col = 0; col < nextState[row].length; col++) {
					if (nextState[row][col] == 1) 
						grid[row][col].setAlive(true);
					else grid[row][col].setAlive(false);
					grid[row][col].update(); // redraw the cell
				}
		} else System.out.println("nextState's dimensions don't match grid's dimensions ");
	}
	
	/**
	 * @return 2D int array that is a copy of this cellGrid's cell states
	 */
	public int[][] to2DArray() {
		int[][] arr = new int[rows][cols];
		for (int r = 0; r < arr.length; r++)
			for (int c = 0; c < arr[r].length; c++) {

				if (grid[r][c].isAlive()) arr[r][c] = 1;
				else arr[r][c] = 0;

			}
		return arr;
	}
	
	public int getRows() {
		return rows;
	}
	
	public int getCols() {
		return cols;
	}

	public int getUpdateInterval() {
		return updateInterval;
	}
	
	public void setUpdateInterval(int updateInterval) {
		this.updateInterval = updateInterval;
	}
	
	public boolean isFullscreen() {
		return fullscreen;
	}
	
	public void setFullscreen(boolean fullscreen) {
		if (fullscreen) setExtendedState(MAXIMIZED_BOTH);
		else setExtendedState(NORMAL);
	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public Color getBg() {
		return bg;
	}

	public void setBg(Color bg) {
		this.bg = bg;
	}

	public Color getFg() {
		return fg;
	}

	public void setFg(Color fg) {
		this.fg = fg;
	}

	public Color getAliveColor() {
		return aliveColor;
	}

	public void setAliveColor(Color aliveColor) {
		this.aliveColor = aliveColor;
	}

	public Cell[][] getGrid() {
		return grid;
	}

	public void setGrid(Cell[][] grid) {
		this.grid = grid;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

}
