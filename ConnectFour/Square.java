package ConnectFour;

//this class defines squares usable for connect four
//each square can draw itself with or without a red/black token to indicate
//a player has placed a token in it.


import java.awt.*;

public class Square
{
    int x, y;                   //coordinates of the upper left of the square
    int i, j;                   //index in the array in Board, {0...5}
    static int squareSize = 100;      //size of the side of a square
    char key = ' ';             //key for whether there is a red/black token
                                //    in the square

    //constructor for square:
    //parameters are the coordinates in the board's grid, from the upper left
    public Square(int i1, int j1)
    {
        i = i1;
        j = j1;

        x = squareSize + squareSize * i;
        y = squareSize + squareSize * j;
    }


    //the square draws itself.
    //also can display a token if it has a red or black token,
    //denoted by its key: 'r' for red, 'b' for black, ' ' for none.
    public void drawMe(Graphics g)
    {
        g.drawRect(x, y, squareSize, squareSize);

        if(key == ' ')
        {
            g.drawOval(x, y, squareSize, squareSize);
        }
        else if(key == 'r')
        {
            g.setColor(Color.red);
            g.fillOval(x, y, squareSize, squareSize);
            g.setColor(Color.black);
        }
        else if(key == 'b')
        {
            g.setColor(Color.black);
            g.fillOval(x, y, squareSize, squareSize);
        }

    }


    //Getters and Setters for squareSize and key
    public static void setSquareSize( int s ) { squareSize = s; }
    public static int getSquareSize() { return squareSize; }
    public boolean isOccupied() { return (key=='r' || key=='b'); }
    public char getKey() { return key; }
    public void setKey( char k ) { key = k; }
    public int getI() {return i;}
    public int getJ() {return j;}

}
