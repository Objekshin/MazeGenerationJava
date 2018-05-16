public class Position
{
	private int xRow;
	private int yColumn;
	private int direct;
	Position(int x, int y)
	{
		xRow = x;
		yColumn = y;
	}
	public void setDirect(int temp)
	{
		direct=temp;
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