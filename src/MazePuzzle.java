
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;



/**
 * MazePuzzle will be the class that contains the 2D array that is the maze puzzle.
 * @author asus
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
	private Position prevPos;
	boolean test=true;
	private ArrayList directions=new ArrayList();

	MazePuzzle(int dimens) //Change this later for 2D support.
	{
		maze = new int[dimens][dimens];
		currentPos = new Position(0,1);
	}

	MazePuzzle(MazePuzzle puzzle, Position pos)
	{
		maze = puzzle.getMaze();
	}

	MazePuzzle(int dimens, int startx, int starty, int endx, int endy) //Later Support 
	{
		//For later dev.
	}

	/*
	 * 
	 * Make the initial cell the current cell and mark it as visited
		While there are unvisited cells
			If the current cell has any neighbours which have not been visited
			Choose randomly one of the unvisited neighbours
			Push the current cell to the stack
			Remove the wall between the current cell and the chosen cell
			Make the chosen cell the current cell and mark it as visited
				Else if stack is not empty
				Pop a cell from the stack
				Make it the current cell
	 * */
	public boolean createPathMarkTwo(MazePuzzle puzzle, Position currentPosition)
	{
		//fix direction that is 0 opposing direction is not working
		boolean solution;
		Position newPos;
		int direct;
		int temp;
		if (ifFinished(puzzle, currentPosition))
		{
			//Base case!
			//System.out.println("GG WE FINIHSED");
		} else 
		{
			int tries = 0;
			boolean emptyFound = false;
			boolean wallFound=false;
			int direction = (int)(Math.random()*4)+1;
			//1 is up
			//2 is left
			//3 is down
			//4 and 0 is right


		//	System.out.println(currentPosition.getRow() +" "+ currentPosition.getColumn());

			//This is the portion that looks for an empty grey spot.
			while (tries <4 && !emptyFound)
			{
				direction++;
				try
				{	
					//System.out.println(direction);
					emptyFound = checkSquare(direction%4, currentPosition); //Looking for DEFAULTS
				} catch(Exception E)
				{
					//This is if the point it picks is outside the array
					//System.out.println("NULLPOINTER REKT BY JAVA");
				}
				tries++;
			}

			if (emptyFound) //Create a REDFOUND case for recursive purposes later on.
			{
				newPos = getNewPos(currentPosition, direction%4);
				MazePuzzle newPuzzle = new MazePuzzle(puzzle, newPos);
				//newPuzzle.setPositionAs(newPuzzle, currentPosition, 4); //OBSOLETE
				newPuzzle.setPositionsAsRED(newPuzzle, currentPosition, newPos, direction%4);
				directions.add(direction%4);
				//System.out.println("directions size "+directions.size());
			//	System.out.println("DIRECT: "+ direction%4);
				//System.out.println("opposite direct "+previousDirect((int)directions.remove(directions.size()-1)));
				//System.out.println("POSITION: "+currentPosition.getRow() + "," +currentPosition.getColumn() );
				//System.out.println("NPOSITION: "+newPos.getRow() + "," +newPos.getColumn() );
				//System.out.println("found grey");
				solution = createPathMarkTwo(newPuzzle, newPos); //Recursive case.

			} 
			else {
				tries = 0;
				//test=false;
				boolean RedFound = false;
				//direction = (int)(Math.random()*4)+1;
				//1 is up
				//2 is left
				//3 is down
				//4 is right
				//System.out.println("current position: "+currentPosition.getRow() +" "+ currentPosition.getColumn());
				
				
			//	System.out.println(direct);
				//System.out.println("directions size "+directions.size());
			//	System.out.println("direction and opposite direction "+direct); 
//				for (int i=directions.size()-1;i>0;i--)
//				{
//					System.out.println ("directions array "+directions.get(i));
//					System.out.println("opposite direction "+direct);
//				}
				
				try
				{//when backtracking once it gets to the beginning it tries to remove on an empty arraylist, and fix the zero direction issue
				direct=previousDirect((int)(directions.remove(directions.size()-1)));
				}
				catch(Exception E)
				{
					direct=0;
				}
				while (tries <4 && !RedFound)
				{
					direction++;
					try
					{	
						//Picking the direction.
						//System.out.println("N DIRECITON: "+direction%4);
						//System.out.println("direction and opp: "+direct+" "+previousDirect(direct));
						//Right here, it is checking for a random red spot. Instead of doing this, have it go back to the spot it came from previously.
						RedFound = checkReds(direct, currentPosition); //Looking for DEFAULTS
					} catch(Exception E)
					{
						//System.out.println("NULLPOINTER XD REKT BY JAVA");
					}
					tries++;
				}

				if (RedFound)
				{
					
					//System.out.println("found red");
					//change newPos direction as well
					
					newPos = getNewPos(currentPosition,direct);
					MazePuzzle newPuzzle = new MazePuzzle(puzzle, newPos);
					//System.out.println("direct: "+ direction%4);
					//System.out.println("position: "+currentPosition.getRow() + "," +currentPosition.getColumn() );
					//System.out.println("nposition: "+newPos.getRow() + "," +newPos.getColumn() );
					newPuzzle.setPositionsAsWHITE(newPuzzle, currentPosition, newPos, direct);
					solution = createPathMarkTwo(newPuzzle, newPos); //also recursive case.
				}//pop cell of stack make it current cell below
				else {

					//System.out.println(currentPosition.getRow());
					//System.out.println(currentPosition.getColumn());

					//					if (puzzle.getMaze()[currentPosition.getRow()][currentPosition.getColumn()] == 2)
					//						puzzle.setPositionAs(puzzle, currentPosition, 3);

					//	print();
					//System.out.println("Do something here");
				}

				//This is where you clear red paths

				//System.out.println("Recursive (redpath)");
				//return false;*/
			} 
			//[Error 1, fix here]
			//System.out.println("Recursive (regular)");

	}


		return false;
	}
	public int previousDirect(/*Position currentpos*/int direct)
	{
		if (direct==1)		
			return 3;
		if (direct==3)		
			return 1;
		if (direct==2)		
			return 4;
		if (direct==4)		
			return 2;
		return 2;
	}
	private boolean checkReds(int direc, Position curr) throws Exception {
		if (direc == 1) //up
		{
			if (maze[curr.getRow()-2][curr.getColumn()] ==2)
				return true;
		} else if (direc == 2) //left
		{
			if (maze[curr.getRow()][curr.getColumn()-2] ==2)
				return true;
		} else if (direc == 3) //down 
		{
			if (maze[curr.getRow()+2][curr.getColumn()] ==2)
				return true;
		} else //right
		{
			if (maze[curr.getRow()][curr.getColumn()+2] ==2)
				return true;
		}

		return false;
	}

	private boolean ifFinished(MazePuzzle puzzle, Position currentPosition)
	{
		for (int i = 0; i< puzzle.getMaze().length; i++)
		{
			for (int j = 0; j < puzzle.getMaze().length; j++)
			{
				if (puzzle.getMaze()[i][j] != 0 || puzzle.getMaze()[i][j] !=2) //white or walls
					return false;
			}
		}


		return true; 
	}



	private void setPositionAs(MazePuzzle x, Position newPos, int setAsWhat)
	{
		x.getMaze()[newPos.getRow()][newPos.getColumn()] = setAsWhat;
	}

	/**
	 * Sets positions as red (before recursion)
	 * @param x
	 * @param currentPosition
	 * @param newPos
	 * @param direction
	 */
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

	/**
	 * Sets positions as white (recursion)
	 * @param x
	 * @param currentPosition
	 * @param newPos
	 * @param direction
	 */
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

	private Position getIncompletePos(MazePuzzle z) //Should call this method when incomplete but traps itself.
	{

		for ( int i = 1; i< z.getMaze().length-1; i= i+2)
		{
			for ( int j = 1; j< z.getMaze().length-1; j= j+2)
			{

				if (z.getMaze()[i][j] !=3)
					return new Position(i,j);

			}
		}



		System.out.println("CALLED NULL");
		return null; //Shouldn't have to....
	}
	//track positions direction
	private Position getNewPos(Position CurrentPosition, int direct) //Gets the new modified position According to the direction.
	{
		Position temppos;
		if (direct == 1)
		{
			temppos=new Position (CurrentPosition.getRow()-2, CurrentPosition.getColumn());
			temppos.setDirect(direct);
			return temppos;
		}
		else if (direct == 2)
		{
			temppos=new Position (CurrentPosition.getRow(), CurrentPosition.getColumn()-2);
			temppos.setDirect(direct);
			return temppos;
		}
		else if (direct == 3)
		{
			temppos= new Position (CurrentPosition.getRow()+2, CurrentPosition.getColumn());
			temppos.setDirect(direct);
			return temppos;
		}
		else 
		{
			temppos= new Position (CurrentPosition.getRow(), CurrentPosition.getColumn()+2);
			temppos.setDirect(direct);
			return temppos;
		}

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
	private boolean checkWall(int direc, Position curr) throws Exception
	{

		if (direc == 1) //up
		{
			if (maze[curr.getRow()-2][curr.getColumn()] ==1)
				return true;
		} else if (direc == 2) //left
		{
			if (maze[curr.getRow()][curr.getColumn()-2] ==1)
				return true;
		} else if (direc == 3) //down 
		{
			if (maze[curr.getRow()+2][curr.getColumn()] ==1)
				return true;
		} else //right
		{
			if (maze[curr.getRow()][curr.getColumn()+2] ==1)
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

	/**
	 * Prints for ease of use
	 */
	public void print()
	{
		for (int i = 0; i<maze.length;i++)
		{
			System.out.println(Arrays.toString(maze[i]));
		}
	}
	/**
	 * Generates the walls around the enclosure
	 */
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

	/**
	 * Gets the maze
	 * @return maze
	 */
	public int[][] getMaze() //Get the maze [For accessing purposes]
	{
		return maze;
	}


	public void changeReds(MazePuzzle x)
	{
		for (int i =0; i<x.getMaze().length; i++)
		{
			for (int j = 0; j<x.getMaze().length; j++)
			{
				if (x.getMaze()[i][j] == 2)
					x.setPositionAs(x, new Position (i,j), 3);
			}
		}
	}

	public static void main (String[] args)
	{
		MazePuzzle x = new MazePuzzle(31);
		x.generateWalls();
		//x.createEntranceNExit(); //OBSOLETE
		Position y = new Position (1,1);

		x.createNtrance(x);
		x.createPathMarkTwo(x,y);// Calls the path (recursive)


		//System.out.println((int)(Math.random()*4) +!1);
		x.print();
		x.createEntranceNExit();
		//x.changeReds(x); //Temp

		CheckerBoard z = new CheckerBoard(x.getMaze());

		z.setTitle("CheckerBoard");
		z.setSize(700,700);
		z.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		z.setVisible(true);
		System.out.println(x.directions.size());
		//MazeGraphic y = new MazeGraphic(x);
	}
}