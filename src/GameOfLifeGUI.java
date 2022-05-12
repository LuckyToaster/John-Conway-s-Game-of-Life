import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class GameOfLifeGUI extends CellGrid {
	
	GameOfLifeGUI(String title, int cellSize, int updateInterval, boolean fullscreen, Color bg, Color fg, Color aliveColor) {
		super(title, cellSize, updateInterval, fullscreen, bg, fg, aliveColor);
	}
	
	@Override
	public void timerOperations() {
		update(GameOfLife.getNextGeneration(to2DArray()));
	}

	public static void main(String[] args) {

		String title = "Conway's Game of Life";
		boolean fullscreen = true;
		int cellSize = 20;
		int updateInterval = 100;
		Color bg = Color.black;
		Color fg = Color.gray;
		Color aliveColor = Color.white;

		new GameOfLifeGUI(title, cellSize, updateInterval, fullscreen, bg, fg, aliveColor);
	}

}
