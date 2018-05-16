//CheckerBoard.java - Jimmy Kurian

import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;

public class CheckerBoard extends JFrame
{

	private int [][] puzz;

	public void paint(Graphics g)
	{
		int row;
		int col;
		int x;
		int y;

		for ( row = 0;  row < puzz.length;  row++ )
		{
			for ( col = 0;  col < puzz.length;  col++)
			{
				x = col * 12;
				y = row * 12;
				if (puzz[row][col] == 1)
					g.setColor(Color.BLACK);
				else if (puzz[row][col] == 2)
					g.setColor(Color.RED);
				else if (puzz[row][col] == 0)
					g.setColor(Color.DARK_GRAY);
				else
					g.setColor(Color.WHITE);

				g.fillRect(x, y, 12, 12);
			}
		}
	}

	CheckerBoard(int[][] x)
	{
		puzz = x;
	}

	public static void main(String args[]) {
		
		int[][] z = new int[3][3];
		CheckerBoard check = new CheckerBoard(z);
		check.setUndecorated(true);
		check.setTitle("CheckerBoard");
		check.setSize(1600, 500);
		check.setDefaultCloseOperation(EXIT_ON_CLOSE);
		check.setVisible(true);
	}
}