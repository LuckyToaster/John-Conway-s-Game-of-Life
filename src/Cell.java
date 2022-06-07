import java.awt.Color;
import javax.swing.JButton;

public class Cell extends JButton {
	
	private boolean alive;
	private Color bg, fg, aliveColor;
	
	/**
	 * @param bg - background color
	 * @param fg - foreground color
	 * @param aliveColor - Color for when the cell's alive attribute is set to true
	 */
	Cell(Color bg, Color fg, Color aliveColor) {
		this.bg = bg;
		this.fg = fg;
		this.aliveColor = aliveColor;
		this.alive = false;
		setBackground(bg);
		setForeground(fg);
		setFocusable(false); // ActionListener & KeyListener will work in unison 
		addActionListener( e -> {
			alive = !alive;
			if (alive) setBackground(aliveColor);
			else setBackground(bg);
		});
	}

	/**
	 * Update's the color of the cell based on its state, effectively redrawing the cell
	 */
	public void update() {
		if (alive) setBackground(aliveColor);
		else setBackground(bg);
	}
	
	public void setAlive(boolean alive) { 
		this.alive = alive; 
	}

	public boolean isAlive() { 
		return alive; 
	}
	
	public void setBg(Color bg) { 
		this.bg = bg; 
	}
	
	public Color getBg() {
		return bg;
	}

	public void setFg(Color fg) {
		this.fg = fg;
	}
	
	public Color getFg() {
		return fg;
	}

	public void setAliveColor(Color aliveColor) {
		this.aliveColor = aliveColor;
	}
	
	public Color getAliveColor() {
		return aliveColor;
	}

}
