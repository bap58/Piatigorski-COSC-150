package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;

public class Board
{
    Square[][] theSquares; // 3x3 array of squares
    static int boardSize = 3;
    LinkedList<Scorable> possibleWins;

    // constructor for the board
    public Board()
    {
        possibleWins = new LinkedList<Scorable>();

        theSquares = new Square[boardSize][boardSize];
        for(int i = 0; i < boardSize; i++)
        {
            for(int j = 0; j < boardSize; j++)
            {
                theSquares[i][j] = new Square(i,j); //gives us 9 new squares
            }
        }

        //add squares in various ways to the Scorables in the
        //possible wins list
        for(int i = 0; i < boardSize; i++)
        {
            Scorable s = new Scorable(); //one column

            for(int j = 0; j < boardSize; j++)
            {
                s.addSquare(theSquares[i][j]);
            }

            possibleWins.add(s);
        }

        for(int j = 0; j < boardSize; j++)
        {
            Scorable s = new Scorable(); //one row

            for(int i = 0; i < boardSize; i++)
            {
                s.addSquare(theSquares[i][j]);
            }

            possibleWins.add(s);
        }

        //diagonals
        Scorable d1 = new Scorable();
        Scorable d2 = new Scorable();
        for(int i = 0; i < boardSize; i++)
        {
            d1.addSquare(theSquares[i][i]);
            d2.addSquare(theSquares[i][boardSize - i - 1]);
        }

        possibleWins.add(d1);
        possibleWins.add(d2);
    }

    //take the mouse event and return the square that got clicked
    //return NULL if they didn't click on a square
    public Square findSquare(MouseEvent m)
    {
        Square sq = null;

        int i = m.getX() / Square.getSquareSize() - 1;
        int j = m.getY() / Square.getSquareSize() - 1;

        if(i >= 0 && i < boardSize && j >= 0 && j < boardSize)
        {
            sq = theSquares[i][j];
        }

        return sq;
    }

    //returns index of the player whose turn it is
    //count the filled squares, if the count is even, x's turn; odd, o's turn
    public int whoseTurn()
    {
        int whose;
        int count = 0;

        for(int i = 0; i < boardSize; i++)
        {
            for(int j = 0; j < boardSize; j++)
            {
                if(theSquares[i][j].getMark() != ' '){count++;}
            }
        }
        whose = count % 2;

        return whose;
    }

    public void drawMe(Graphics g)
    {
        for(int i = 0; i < 3; i++)
        {
            for(int j = 0; j < boardSize; j++)
            {
                theSquares[i][j].drawMe(g);
            }
        }
    }

    //returns the mark of the winner if there is one
    public char winner()
    {
        char win = ' ';

        Iterator<Scorable> it = possibleWins.iterator();
        while(win == ' ' && it.hasNext())
        {
            Scorable s = it.next();
            win = s.win();
            if(win != ' ')
            {
                JOptionPane.showMessageDialog(null, ""+win+" wins!");
            }
        }

        return win;
    }

    public static int getBoardSize() {return boardSize;}

}
