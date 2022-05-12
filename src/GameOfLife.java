import java.awt.Color;

public class GameOfLife {

	/* 
	 * @param previous - previous generation 2D int array
	 * @return the next generation 2D int array as a finite grid (with borders)
	 */
	public static int[][] getNextGeneration(int[][] previous) {

		int rows = previous.length, cols = previous[0].length;
		int[][] next = new int[rows][cols];

		for (int l = 0; l < rows; l++) {
			for (int m = 0; m < cols; m++) {

				int aliveNeighbours = 0;

				for (int i = -1; i <= 1; i++)
					for (int j = -1; j <= 1; j++)
						if ((l + i >= 0 && l + i < rows) && (m + j >= 0 && m + j < cols))
							aliveNeighbours += previous[l + i][m + j];

				// The cell needs to be subtracted from
				// its neighbours as it was counted before
				aliveNeighbours -= previous[l][m];

				// Implementing the Rules of Life
				if ((previous[l][m] == 1) && (aliveNeighbours < 2))
					next[l][m] = 0;
				else if ((previous[l][m] == 1) && (aliveNeighbours > 3))
					next[l][m] = 0;
				else if ((previous[l][m] == 0) && (aliveNeighbours == 3))
					next[l][m] = 1;
				else next[l][m] = previous[l][m];
			}
		} return next;
	}

}
