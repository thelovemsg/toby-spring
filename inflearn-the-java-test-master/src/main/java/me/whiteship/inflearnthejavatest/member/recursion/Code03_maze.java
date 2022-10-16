package me.whiteship.inflearnthejavatest.algorithm.recursion;

public class Code03_maze {
    private static int N=8;
    private static int [][] maze = {
        {0,0,0,0,0,0,0,1},
        {0,1,1,0,1,1,0,1},
        {0,0,0,1,0,0,0,1},
        {0,1,0,0,1,1,0,0},
        {0,1,1,1,0,0,1,1},
        {0,1,0,0,0,1,0,1},
        {0,0,0,1,0,0,0,1},
        {0,1,1,1,0,1,0,0},
    };

    private static final int PATHWAY_COLOR = 0; //white
    private static final int WALL_COLOR = 1; //blue
    private static final int BLOCKED_COLOR = 2; //red
    private static final int PATH_COLOUR = 3; //green

    public static boolean findMazePath(int x, int y) {
        if (x<0 || y<0 || x>=N || y>=N)
            return false;
        else if(maze[x][y] != PATHWAY_COLOR)
            return false;
        else if(x==N-1 && y==N-1){
            maze[x][y] = PATH_COLOUR;
            return true;
        }else{
            maze[x][y] = PATH_COLOUR;
            if (findMazePath(x-1, y) || findMazePath(x,y+1)
                || findMazePath(x+1, y) || findMazePath(x, y-1)){
                return true;
            }
            maze[x][y] = BLOCKED_COLOR;
            return false;
        }
    }



}
