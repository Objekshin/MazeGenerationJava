package FirstMaze;

import java.util.Arrays;



/**
 * MazePuzzle will be the class that contains the 2D array that is the maze puzzle.
 * @author Objekshin
 *
 * @version 0.1
 */
public class MazePuzzle 
{



	private int[][] maze;
	private Position currentPos;
	public final int DEFAULT = 0; //Will be initialized as this!!!
	public final int WALL = 1;
	public final int REDSPACE = 2;
	public final int WHITEPATH = 3; //OBSOLETE [USED PREVIOUSLY BUT FAILED XDDDDD]






	MazePuzzle(int dimens) //Change this later for 2D support.
	{
		maze = new int[dimens][dimens];
		currentPos = new Position(0,1);
	}

	MazePuzzle(MazePuzzle puzzle, Position pos)
	{
		maze = puzzle.getMaze();




	}
	MazePuzzle(int dimens, int startx, int starty, int endx, int endy) //Later Support this update.

	{

	}


	/*
	public boolean createPath(MazePuzzle puzzle, Position currentLocation) //Returns true if the goal has been reached.
	{
		if (isTouchingEnd(currentLocation))
		{
			System.out.println("FINISHED");
			puzzle.print();
			return true;
		}

		else 
		{
			int tries = 0;
			boolean emptyFound = false;
			int direction = (int)(Math.random()*4)+1;
			//1 is up
			//2 is left
			//3 is down
			//4 is right


			while (tries <4 && !emptyFound)
			{
				direction++;
				try
				{	
					System.out.println(direction);
					emptyFound = checkSquare(direction%4, currentLocation);

				} catch(Exception E)
				{
					System.out.println("NULLPOINTER XD REKT BY JAVA");
				}
				tries++;
			}

			if (emptyFound)
			{
				System.out.println("YES");

				Position newPos = getNewPos(currentLocation, direction%4);
				MazePuzzle newPuzzle = new MazePuzzle(puzzle, newPos);
				newPuzzle.setPositionAs(newPuzzle, newPos, 2);
				boolean solution = createPath(newPuzzle, newPos);
			} else {
				return false;
			} 
			//[Error 1, fix here]
			System.out.println("RIP");

		}

		return false;
	}
	 */

	public boolean createPathMarkTwo(MazePuzzle puzzle, Position currentPosition)
	{
		if (ifFinished(puzzle, currentPosition))
		{

			System.out.println("GG WE FINIHSED");
		} else 
		{
			int tries = 0;
			boolean emptyFound = false;
			int direction = (int)(Math.random()*4)+1;
			//1 is up
			//2 is left
			//3 is down
			//4 is right



			System.out.println(currentPosition.getRow() +" "+ currentPosition.getColumn());

			while (tries <4 && !emptyFound)
			{
				direction++;
				try
				{	
					System.out.println(direction);
					emptyFound = checkSquare(direction%4, currentPosition); //Looking for DEFAULTS


				} catch(Exception E)
				{
					//System.out.println("NULLPOINTER XD REKT BY JAVA");
				}
				tries++;
			}

			if (emptyFound) //Create a REDFOUND case for recursive purposes later on.
			{
				Position newPos = getNewPos(currentPosition, direction%4);
				MazePuzzle newPuzzle = new MazePuzzle(puzzle, newPos);
				//newPuzzle.setPositionAs(newPuzzle, currentPosition, 4); //OBSOLETE
				newPuzzle.setPositionsAsRED(newPuzzle, currentPosition, newPos, direction%4);

				System.out.println("DIRECT: "+ direction%4);
				System.out.println("POSITION: "+currentPosition.getRow() + "," +currentPosition.getColumn() );
				System.out.println("NPOSITION: "+newPos.getRow() + "," +newPos.getColumn() );


				boolean solution = createPathMarkTwo(newPuzzle, newPos);

			} else {
				
				//Make a check for red paths method, check for exceptions.				
				
				Position newPos = getNewPos(currentPosition, direction%4);
				MazePuzzle newPuzzle = new MazePuzzle(puzzle, newPos);
				
				System.out.println("direct: "+ direction%4);
				System.out.println("position: "+currentPosition.getRow() + "," +currentPosition.getColumn() );
				System.out.println("nposition: "+newPos.getRow() + "," +newPos.getColumn() );
				
				newPuzzle.setPositionsAsWHITE(newPuzzle, currentPosition, newPos, direction%4);
				
				boolean solution = createPathMarkTwo(newPuzzle, newPos);
				//This is where you clear red paths
				
				System.out.println("BOOM WE GOT HERE");
				//return false;
			} 
			//[Error 1, fix here]
			System.out.println("RIP");


		}


		return false;
	}

	private boolean ifFinished(MazePuzzle puzzle, Position currentPosition)
	{
		for (int i = 0; i< puzzle.getMaze().length; i++)
		{
			for (int j = 0; j < puzzle.getMaze().length; j++)
			{
				if (puzzle.getMaze()[i][j] != 1 || puzzle.getMaze()[i][j] !=2) //white or walls
					return false;
			}
		}


		return true; 
	}


	private void setPositionAs(MazePuzzle x, Position newPos, int setAsWhat)
	{
		x.getMaze()[newPos.getRow()][newPos.getColumn()] = setAsWhat;
	}

	private void setPositionsAsRED(MazePuzzle x, Position currentPosition, Position newPos, int direction) //This is for the inital redpath laying down //Works with getNewPos
	{
		if (direction == 1)
		{
			x.getMaze()[currentPosition.getRow()][currentPosition.getColumn()] = 2;
			x.getMaze()[currentPosition.getRow()-1][currentPosition.getColumn()] = 2;
			x.getMaze()[newPos.getRow()][newPos.getColumn()] = 2;
		} else if (direction == 2)
		{
			x.getMaze()[currentPosition.getRow()][currentPosition.getColumn()-1] = 2;
			x.getMaze()[currentPosition.getRow()][currentPosition.getColumn()] = 2;
			x.getMaze()[newPos.getRow()][newPos.getColumn()] = 2;
		} else if (direction == 3)
		{
			x.getMaze()[currentPosition.getRow()][currentPosition.getColumn()] = 2;
			x.getMaze()[currentPosition.getRow()+1][currentPosition.getColumn()] = 2;
			x.getMaze()[newPos.getRow()][newPos.getColumn()] = 2;
		} else {
			x.getMaze()[currentPosition.getRow()][currentPosition.getColumn()] = 2;
			x.getMaze()[currentPosition.getRow()][currentPosition.getColumn()+1] = 2;
			x.getMaze()[newPos.getRow()][newPos.getColumn()] = 2;
		}
		
	}

	private void setPositionsAsWHITE(MazePuzzle x, Position currentPosition, Position newPos, int direction)
	{
		if (direction == 1)
		{
			x.getMaze()[currentPosition.getRow()][currentPosition.getColumn()] = 3;
			x.getMaze()[currentPosition.getRow()-1][currentPosition.getColumn()] = 3;
			x.getMaze()[newPos.getRow()][newPos.getColumn()] = 3;
		} else if (direction == 2)
		{
			x.getMaze()[currentPosition.getRow()][currentPosition.getColumn()-1] = 3;
			x.getMaze()[currentPosition.getRow()][currentPosition.getColumn()] = 3;
			x.getMaze()[newPos.getRow()][newPos.getColumn()] = 3;
		} else if (direction == 3)
		{
			x.getMaze()[currentPosition.getRow()][currentPosition.getColumn()] = 3;
			x.getMaze()[currentPosition.getRow()+1][currentPosition.getColumn()] = 3;
			x.getMaze()[newPos.getRow()][newPos.getColumn()] = 3;
		} else {
			x.getMaze()[currentPosition.getRow()][currentPosition.getColumn()] = 3;
			x.getMaze()[currentPosition.getRow()][currentPosition.getColumn()+1] = 3;
			x.getMaze()[newPos.getRow()][newPos.getColumn()] = 3;
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

	private boolean checkSquare(int direc, Position curr) throws Exception
	{

		if (direc == 1) //up
		{
			if (maze[curr.getRow()-2][curr.getColumn()] ==0)
				return true;
		} else if (direc == 2) //left
		{
			if (maze[curr.getRow()][curr.getColumn()-2] ==0)
				return true;
		} else if (direc == 3) //down 
		{
			if (maze[curr.getRow()+2][curr.getColumn()] ==0)
				return true;
		} else //right
		{
			if (maze[curr.getRow()][curr.getColumn()+2] ==0)
				return true;
		}

		return false;
	}




	private boolean isTouchingEnd(Position x) //Obsolete
	{
		if (maze[x.getRow()+1][x.getColumn()]==3)
		{
			return true;
		}

		return false;
	}


	public void print()
	{
		for (int i = 0; i<maze.length;i++)
		{
			System.out.println(Arrays.toString(maze[i]));
		}
	}
	public void generateWalls() //This generates walls 
	{
		for (int i =0; i<maze.length;i++)
		{
			for (int j = 0; j<maze.length;j++)
			{
				if (i%2 ==0)
				{
					maze[i][j] = WALL;
				}

				if (j%2 == 0)
					maze[i][j] = WALL;

			}
		}

	}
	public void createEntranceNExit() //Assuming maze wall >2 //No longer needed, OBSOLETE
	{
		maze[0][0] = REDSPACE;
		maze[0][1] = DEFAULT;
		maze[maze.length-1][maze.length-2] = DEFAULT;
		maze[maze.length-1][maze.length-2] =REDSPACE; //OBSOLETE + WRONG
	}	

	public void createNtrance(MazePuzzle x)
	{
		x.getMaze()[1][1] = REDSPACE;
	}
	public int[][] getMaze() //Get the maze [For accessing purposes]
	{
		return maze;
	}


	public static void main (String[] args)
	{
		MazePuzzle x = new MazePuzzle(21);
		x.generateWalls();
		//x.createEntranceNExit(); //OBSOLETE
		Position y = new Position (1,1);

		x.createNtrance(x);
		x.createPathMarkTwo(x,y);
		//System.out.println((int)(Math.random()*4) +!1);
		x.print();
	}

}
