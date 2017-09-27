package TicTacToe;

import java.awt.*;

public class Square
{
    protected int x, y; //coords of upper left of the square
    protected int i, j; //index in the array
    protected int squareSize = 100; //size of the side of each square

    public Square(int i1, int j1)
    {
        i = i1;
        j = j1;

        x = squareSize + squareSize*i;
        y = squareSize + squareSize*j;
    }

    public void drawMe(Graphics g)
    {
        g.drawRect(x, y, squareSize, squareSize);
    }
}
