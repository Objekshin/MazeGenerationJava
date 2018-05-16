import java.util.Arrays;

import javax.swing.JFrame;

public class MazeGenerator
{

	public int [][] maze;
	public boolean [][] bolmaze; //Change this to match the boolean status of visited or not visited


	MazeGenerator(int y)
	{
		initMaze(y);		
	}


	//Generate Maze method, generates the maze
	public Position generateMaze(Position p) 
	{
		if (ifFinished())
		{
			print();
			System.out.println("completion");
		} else {
			int direction = (int)(Math.random()*4) +1;
			boolean emptyFound = false;
			int tries = 0;

			while (tries <4 && !emptyFound)
			{
				direction++;
				try
				{	
					//System.out.println(direction);
					emptyFound = checkSquare(direction%4, p); //Looking for DEFAULTS
				} catch(Exception E)
				{
				}
				tries++;
			}




			//System.out.println("dic "+direction%4);
			//System.out.println("pos "+p.getRow() + " " + p.getColumn());
			//System.out.println(emptyFound);


			Position newPos = getNewPos(p, direction%4);
			fillMaze(p,newPos,direction%4);


			//	generateMaze(newPos);

			return newPos;
		}

		return p;
	}

	public void fillMaze(Position cp, Position np, int direction)
	{
		if (direction == 0)
		{
			maze[cp.getRow()][cp.getColumn()] =3;

			if (!(maze[np.getRow()][np.getColumn()] == 3))
			{
				maze[cp.getRow()][cp.getColumn()+1] = 3;
				maze[np.getRow()][np.getColumn()] = 3;
			}
		}
		else if (direction == 1)
		{
			maze[cp.getRow()][cp.getColumn()] =3;

			if (!(maze[np.getRow()][np.getColumn()] == 3))
			{
				maze[cp.getRow()-1][cp.getColumn()] = 3;
				maze[np.getRow()][np.getColumn()] = 3;
			}
		}
		else if (direction == 2)
		{
			maze[cp.getRow()][cp.getColumn()] =3;

			if (!(maze[np.getRow()][np.getColumn()] == 3))
			{
				maze[cp.getRow()][cp.getColumn()-1] = 3;
				maze[np.getRow()][np.getColumn()] = 3;
			}
		}
		else if (direction == 3)
		{

			maze[cp.getRow()][cp.getColumn()] =3;

			if (!(maze[np.getRow()][np.getColumn()] == 3))
			{
				maze[cp.getRow()+1][cp.getColumn()] = 3;
				maze[np.getRow()][np.getColumn()] = 3;
			}
		}
	}

	private Position getNewPos(Position CurrentPosition, int direct) //Gets the new modified position According to the direction.
	{

		if (direct == 1)
			return new Position (CurrentPosition.getRow()-2, CurrentPosition.getColumn());
		else if (direct == 2)
			return new Position (CurrentPosition.getRow(), CurrentPosition.getColumn()-2);
		else if (direct == 3)
			return new Position (CurrentPosition.getRow()+2, CurrentPosition.getColumn());
		else 
			return new Position (CurrentPosition.getRow(), CurrentPosition.getColumn()+2);

	}


	public void initMaze(int y)
	{
		maze = new int[y][y];
	}

	public boolean ifFinished()
	{
		for (int i = 0; i<maze.length; i++)
		{
			for ( int j  = 0; j< maze.length; j++)
			{
				if (maze[i][j]==0 )
					return false;
			}
		}
		return true;
	}

	public void generateWalls()
	{
		for (int i = 0; i< maze.length; i++)
		{
			for (int j= 0; j< maze.length; j++)
			{
				if (i%2==0)
					maze[i][j] = 1;

				if (j%2==0)
					maze[i][j] = 1;

			}
		}
	}

	public void createEntranceNExit()
	{
		maze[0][1] = 3;
		maze[maze.length-1][maze.length-2] = 3;
	}

	private boolean checkSquare(int direc, Position curr) throws Exception
	{

		if (direc == 1) //up
		{
			if (maze[curr.getRow()-2][curr.getColumn()] ==0 || maze[curr.getRow()-2][curr.getColumn()] ==3)
				return true;
		} else if (direc == 2) //left
		{
			if (maze[curr.getRow()][curr.getColumn()-2] ==0 ||maze[curr.getRow()][curr.getColumn()-2] ==3)
				return true;
		} else if (direc == 3) //down 
		{
			if (maze[curr.getRow()+2][curr.getColumn()] ==0 ||maze[curr.getRow()+2][curr.getColumn()] ==3)
				return true;
		} else //right
		{
			if (maze[curr.getRow()][curr.getColumn()+2] ==0 ||maze[curr.getRow()][curr.getColumn()+2] ==3 )
				return true;
		}

		return false;
	}



	public void print()
	{
		for (int i = 0; i<maze.length; i++)
		{	
			System.out.println(Arrays.toString(maze[i]));
		}
	}

	public static void main(String [] args)
	{
		for ( int i = 0; i< 5; i++)
		{
			MazeGenerator x = new MazeGenerator(51);
			x.generateWalls();
			//x.print();
			Position p = new Position(1,1);
			x.createEntranceNExit();
			//x.generateMaze(p);
			//x.print();
			long time = System.currentTimeMillis();
			while (!x.ifFinished())
			{
				p = x.generateMaze(p);		
			}
			System.out.println(System.currentTimeMillis()-time);
		}

		for ( int i = 0; i< 5; i++)
		{
			MazeGen y = new MazeGen(111,111);


			long time = System.currentTimeMillis();
			y.newMaze(111,111);
			System.out.println(System.currentTimeMillis()-time);
		}


		MazeGenerator x = new MazeGenerator(51);
		x.generateWalls();
		//x.print();
		Position p = new Position(1,1);
		x.createEntranceNExit();

		
		while (!x.ifFinished())
		{
			p = x.generateMaze(p);		
		}

		//x.print();
		CheckerBoard z = new CheckerBoard(x.maze);


		z.setUndecorated(true);
		z.setTitle("CheckerBoard");
		z.setSize(900,900);
		z.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		z.setVisible(true);
	}
}
