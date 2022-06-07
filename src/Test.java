import java.awt.Color;
import static java.awt.Color.blue;
import static java.awt.Color.green;
import static java.awt.Color.red;

@SuppressWarnings("serial")
public class Test extends CellGrid {
	
	Test(String title, int cellSize, int updateInterval, boolean fullscreen, Color bg, Color fg, Color aliveColor) {
		super(title, cellSize, updateInterval, fullscreen, bg, fg, aliveColor);
		// toggle fullscreen once to render buttons properly (its a bug)
		setFullscreen(!isFullscreen());
		setFullscreen(isFullscreen());
	}
	
	@Override
	public void timerOperations() {
		update(GameOfLife.getNextGeneration(to2DArray()));
	}

	public static void main(String[] args) throws InterruptedException {
		new Test("Conway's Game of Life", 15, 10, true, green, blue, red);
	}

}
