package FirstMaze;
public class Position
{
	private int xRow;
	private int yColumn;
	Position(int x, int y)
	{
		xRow = x;
		yColumn = y;
	}

	public int getRow()
	{
		return xRow;
	}

	public int getColumn()
	{
		return yColumn;
	}
}