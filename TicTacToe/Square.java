package TicTacToe;

import java.awt.*;

public class Square
{
    protected int x, y; //coords of upper left of the square
    protected int i, j; //index in the array
    protected static int squareSize = 100; //size of the side of each square
    protected char mark; // either an 'x' or 'o' or ' ' for starters

    public Square(int i1, int j1)
    {
        i = i1;
        j = j1;

        x = squareSize + squareSize*i;
        y = squareSize + squareSize*j;

        mark = ' ';
    }

    public void drawMe(Graphics g)
    {
        g.drawRect(x, y, squareSize, squareSize);

        g.drawString(" "+mark, x+squareSize/2, y+squareSize/2);
    }

    public static int getSquareSize() {return squareSize;}
    public void setMark(char m) {mark = m;}
    public char getMark() {return mark;}
}
