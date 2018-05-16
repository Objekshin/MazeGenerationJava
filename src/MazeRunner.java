import javax.swing.JFrame;

public class MazeRunner {
	public static void main(String[] args){
		
		
		
		
		for (int i = 0; i< 5; i++)
		{
			MazePuzzle x = new MazePuzzle(311);
			x.generateWalls();
			//x.createEntranceNExit(); //OBSOLETE
			Position y = new Position (1,1);
			long time = System.currentTimeMillis();
			x.createNtrance(x);
			x.createPathMarkTwo(x,y);// Calls the path (recursive)

			System.out.println( System.currentTimeMillis()-time +"ms for Recursive Backtracking Method trial: " +i);
			//System.out.println((int)(Math.random()*4) +!1);
			//x.print();
			x.createEntranceNExit();
			//x.changeReds(x); //Temp

			//CheckerBoard z = new CheckerBoard(x.getMaze());

			//z.setTitle("CheckerBoard");
			//z.setSize(700,700);
			//z.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			//z.setVisible(true);
			//System.out.println(x.directions.size());
			//MazeGraphic y = new MazeGraphic(x);
		}
	
		
		
		for ( int i = 0; i< 5; i++)
		{
			MazeGenerator t = new MazeGenerator(311);
			t.generateWalls();
			//x.print();
			Position p = new Position(1,1);
			t.createEntranceNExit();
			//x.generateMaze(p);
			//x.print();
			long time = System.currentTimeMillis();
			while (!t.ifFinished())
			{
				p = t.generateMaze(p);		
			}
			System.out.println(System.currentTimeMillis()-time + "ms for Aldous-Broder algorithm trial : "+i);
		}
		
		for ( int i = 0; i< 5; i++)
		{
			MazeGen w = new MazeGen(311,311);
	
			long time = System.currentTimeMillis();
			w.newMaze(111,111);
			System.out.println(System.currentTimeMillis()-time + "ms for Hunt-and-Kill algorithm trial: "+i);
		}
	}
}
