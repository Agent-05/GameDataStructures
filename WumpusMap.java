import java.util.ArrayList;
import java.util.*;

public class WumpusMap {
    WumpusMap(){createMap();}

    public static final int numRows = 10;
    public static final int numColumns = 10;
    public static final int numPits = 10;

    private WumpusSquare[][] grid = new WumpusSquare[numRows][numColumns];
    static int ladderC;
    static int ladderR;

    void createMap(){
        for (int i = 0; i < numRows;i++)
        {
            for (int a = 0; a < numColumns;a++)
            {
                WumpusSquare w = new WumpusSquare();
                grid[i][a] = w;
            }
        }
        Random rand = new Random();
        int randX;
        int randY;
        for(int i = 0; i < 3; i++)
        {
            do{
                 randX =rand.nextInt(10);
                 randY =rand.nextInt(10);
            }while(!grid[randY][randX].isEmpty());
            grid[randY][randX].setEmpty(false);
            if(i == 0)
                grid[randY][randX].setGold(true);
            if(i == 1)
            {
                grid[randY][randX].setWumpus(true);
                if(randY != 0)
                {
                    grid[randY-1][randX].setEmpty(false);
                    grid[randY-1][randX].setStench(true);
                }
                if(randY != 9)
                {
                    grid[randY+1][randX].setEmpty(false);
                    grid[randY+1][randX].setStench(true);
                }
                if(randX != 0)
                {
                    grid[randY][randX-1].setEmpty(false);
                    grid[randY][randX-1].setStench(true);
                }
                if(randX != 9)
                {
                    grid[randY][randX+1].setEmpty(false);
                    grid[randY][randX+1].setStench(true);
                }
            }
            if(i == 2)
                grid[randY][randX].setLadder(true);
        }
        //goes to place pits
        for(int i = 0; i < numPits; i++)
        {
            randX = rand.nextInt(10);
            randY = rand.nextInt(10);
            if(grid[randY][randX].isEmpty())
            {
                //puts breeze around holes
                grid[randY][randX].setEmpty(false);
                grid[randY][randX].setPit(true);
                if(randY != 0)
                {
                    grid[randY-1][randX].setEmpty(false);
                    grid[randY-1][randX].setBreeze(true);
                }
                if(randY != 9)
                {
                    grid[randY+1][randX].setEmpty(false);
                    grid[randY+1][randX].setBreeze(true);
                }
                if(randX != 0)
                {
                    grid[randY][randX-1].setEmpty(false);
                    grid[randY][randX-1].setBreeze(true);
                }
                if(randX != 9)
                {
                    grid[randY][randX+1].setEmpty(false);
                    grid[randY][randX+1].setBreeze(true);
                }

            }else{
                i-=1;
            }
        }

    }

    public ArrayList<WumpusSquare> map(){
        ArrayList<WumpusSquare> b = new ArrayList<>();
        for(int i = 0; i< 10;i++)
        {
            for (int a = 0; a<10;a++)
            {
                b.add(grid[i][a]);
            }
        }
        return b;
    }

    int getLadderCol() {return numColumns;}
    int getLadderRows() {return numRows;}
    @Override
    public  String toString(){
        for(int i = 0; i <10; i++)
        {
            for(int a = 0; a < 10; a++)
            {
                System.out.print(grid[i][a].toString());
            }
            System.out.println("\n");
        }
        return "";
    }


}
