// MazeGenerator.java
// Algorithm to create perfect mazes that have no circles ans always a solution
// by using the "hunt and kill" algorithm

// Freely available algorithm implementation in Java originally by:
// Walter D. Pullen - Dec 6, 1996 - MazeMaker 1.0
// code: http://www.astrolog.org/labyrnth/java.htm
// docu: http://www.astrolog.org/labyrnth/algrithm.htm

// Smaller changes and extraction to a separate class
// by Thomas Duebendorfer - Aug 9, 2000

// Kleine Aenderungen by Juergen Sauer

// How to use:
// /* Generate a 12x12 maze now (and up to a 50x50 maze later) */
// MazeGenerator mazeGenerator = MazeGenerator(50,50);
// mazeGenerator.newMaze(12, 12);
// /* Now find the path from (mazeGenerator.startX, mazeGenerator.startY) to      */
// /* (mazeGenerator.targetX,mazeGenerator.targetY) using mazeGenerator.rgfCell[] */
// /* to check for walls when walking.                                            */

// Coding of walls in rgfCell:
// labyrinth.feld[x][y] &= UP    iff (rgfCell[2*y*xmaz + (x*2 + 1)]);
// labyrinth.feld[x][y] &= LEFT  iff (rgfCell[(y*2 + 1)*xmaz + x*2]); 

public class MazeGen
{
	// Constants
	static final int xinc[] = { 0, -1, 0, 1};
	static final int yinc[] = {-1,  0, 1, 0};

	// Variables
	int xsiz, ysiz;
	int xsizmax, ysizmax;
	public int xmaz, ymaz;
	int xlocorg, xloc, yloc, wdir;
	public int startX, startY, targetX, targetY;

	public boolean[] rgfCell;

	// Constructor
	public MazeGen(int xsizmax, int ysizmax) 
	{
		// xsizmax, ysizmax = maximum number of cells in x- and y-direction
		this.xsizmax = xsizmax;
		this.ysizmax = ysizmax;
		rgfCell = new boolean[(xsizmax*2 + 2) * (ysizmax*2 + 2)];
	}

	// Functions
	public void newMaze(int xsiz, int ysiz) 
	{
		// generates a new maze and returns it
		// in: xsiz, ysiz = current number of cells in x- and y-direction
		// out: (startX, startY) to (targetX,targetY) is the path to find
		if (xsiz > xsizmax) xsiz = xsizmax;
		if (xsiz < 0) xsiz = 0;
		if (ysiz > ysizmax) ysiz = ysizmax;
		if (ysiz < 0) ysiz = 0;	
		// save size
		this.xsiz = xsiz;
		this.ysiz = ysiz;
		xmaz = xsiz*2 + 2;
		ymaz = ysiz*2 + 2;
		// create
		generateMaze();
	}

	private int rnd(int n) 
	{
		return (int)(Math.random() * (double)n);
	}

	private boolean fmap(int x, int y) 
	{
		return rgfCell[y*xmaz + x];
	}

	private boolean fget(int x, int y) 
	{
		if (x < 0 || y < 0 || x >= xmaz || y >= ymaz)
			return false;
		return rgfCell[y*xmaz + x];
	}

	private void set(int x, int y) 
	{
		rgfCell[y*xmaz + x] = false;
	}

	private void generateMaze() 
	{
		int x, y, i;
		int xnew = 0, ynew = 0, dirx = 2, diry = 2, count = xsiz*ysiz - 1, d;
		boolean hunt = false;

		for (i = 0; i < xmaz*ymaz; i++)
			rgfCell[i] = true;
		for (y = 0; y < ymaz; y++)
			set(xmaz - 1, y);
		for (x = 0; x < xmaz; x++)
			set(x, ymaz - 1);

		targetX = rnd(xsiz);
		targetY = 0;

		xlocorg = targetX*2 + 1; xloc = xlocorg;
		yloc = 1; wdir = 2;

		x = xloc; y = yloc;
		set(x, 0); set(x, y);
		do {
			d = rnd(4);
			if (!fmap(x, y)) {
				// find a neighbour cell that was not used before
				for (i = 0; i < 4; i++) {
					xnew = x + xinc[d]*2;
					ynew = y + yinc[d]*2;
					if (xnew > 0 && ynew > 0 && xnew < xmaz-1 && ynew < ymaz-1 &&
							fmap(xnew, ynew))
						break;
					d++; d &= 3;
				}
				// if no unused neighbour cell there - start hunting
				hunt = (i >= 4);
			}
			if (!hunt) {
				// it no hunting needed
				set(x+xnew >> 1, y+ynew >> 1);
				set(xnew, ynew);
				x = xnew; y = ynew;
				count--;
			} else {
				// start hunting for unused cells
				if (x + dirx >= 0 && x + dirx < xmaz-1)
					x += dirx;
				else {
					dirx = -dirx;
					if (y + diry >= 0 && y + diry < ymaz-1)
						y += diry;
					else
						diry = -diry;
				}
			}
		} while (count > 0);
		startX = rnd(xsiz);
		startY = ysiz-1;

		set(startX*2 + 1, ymaz-2);
	}

	
	
}
