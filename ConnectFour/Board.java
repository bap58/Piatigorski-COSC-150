package ConnectFour;

//The board for Connect Four. Consists of a 7x6 2D array of Squares.
//The board tracks whose turn it is, where tokens have been played,
//and if there is a winner.

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;

public class Board extends JPanel
{
    Square[][] theSquares;          //a 7x6 array of squares
    static int columnNum = 7;       //the number of columns
    static int rowNum = 6;          //the number of rows
    LinkedList<Scorable> scorables; //a list of possible ways to win


    //constructor for the board.
    //Makes a grid columnNum x rowNum
    public Board()
    {
        //instantiate a columnNum x rowNum grid:
        theSquares = new Square[columnNum][rowNum];
        for(int i = 0; i < columnNum; i++)
        {
            for(int j = 0; j < rowNum; j++)
            {
                theSquares[i][j] = new Square(i,j);
            }
        }

        //Define a list of scorables
        scorables = new LinkedList<Scorable>();

        //Define all the possible winning scorables, and add them to list:
        //Each possible win in one row:
        for(int startCol = 0; startCol < 4; startCol++)
        {
            for(int row = 0; row < rowNum; row++)
            {
                Scorable s = new Scorable();
                for(int col = startCol; col < startCol + 4; col++ )
                {
                    s.addSquare(theSquares[col][row]);
                }
                scorables.add(s);
            }
        }

        //Each possible win in one column:
        for(int startRow = 0; startRow < 3; startRow++)
        {
            for(int col = 0; col < columnNum; col++)
            {
                Scorable s = new Scorable();
                for(int row = startRow; row < startRow + 4; row++)
                {
                    s.addSquare(theSquares[col][row]);
                }
                scorables.add(s);
            }
        }

        //Each possible win in a diagonal running top left -> bottom right
        for(int startRow = 0; startRow < 3; startRow++)
        {
            for(int startCol = 0; startCol < 4; startCol++)
            {
                int i = startCol;
                int j = startRow;
                Scorable s = new Scorable();

                for(int k = 0; k < 4; k++)
                {
                    s.addSquare(theSquares[i+k][j+k]);
                }
                scorables.add(s);
            }
        }

        //Each possible win in a diagonal running top right -> bottom left
        for(int startRow = 0; startRow < 3; startRow++)
        {
            for(int startCol = 3; startCol < 7 ; startCol++)
            {
                int i = startCol;
                int j = startRow;
                Scorable s = new Scorable();

                for(int k = 0; k < 4; k++)
                {
                    s.addSquare(theSquares[i-k][j+k]);
                }
                scorables.add(s);
            }
        }

        //End listing all possible wins and adding them to scorables

    }//END board constructor



    //The board draws itself by asking each square to draw itself
    public void drawMe(Graphics g)
    {
        for(int i = 0; i < columnNum; i++)
        {
            for(int j = 0; j < rowNum; j++)
            {
                theSquares[i][j].drawMe(g);
            }
        }
    }


    //returns the Square that the mouse clicks on, for game play
    public Square squareAt(MouseEvent m)
    {
        Square sq = null;

        int i = m.getX() / Square.getSquareSize() - 1;
        int j = m.getY() / Square.getSquareSize() - 1;
        if ( i>=0 && i<columnNum && j>=0 && j<rowNum ) { sq = theSquares[i][j]; }

        return sq;
    }

    //play a token if a square is legally clicked on by a player
    //sets the square's key to the player's key to indicate color
    //legal squares are those that are unoccupied by a token, and are
    //the lowest available square in the row
    public void play(Square theSquare, Player thePlayer)
    {
        int sqI = theSquare.getI();
        int sqJ = theSquare.getJ();

        //check if the square is empty &&
        //it's the bottom row or the square below is occupied
        if(!theSquare.isOccupied() &&
                (sqJ == rowNum-1 || sqJ<rowNum && (theSquares[sqI][sqJ+1]).isOccupied()))
        {
            theSquare.setKey(thePlayer.getKey()); // set the square's color to the player's
        }
    }


    //function to check for a winner
    //iterates through list of scorables and checks each one for a winner
    public char isWinner()
    {
        char win = ' ';         //default is no winner

        Iterator<Scorable> it = scorables.iterator();
        while( win == ' ' && it.hasNext() )
        {
            Scorable s = it.next();
            win = s.win();
        }

        return win;
    }


    //determine whose turn it is by counting how many move have been made
    //if even, red's turn. if odd, black's turn.
    public int whoseTurn()
    {
        // count occupied squares
        int count = 0;
        for ( int i=0; i<columnNum; i++ )
        {
            for ( int j=0; j<rowNum; j++ )
            {
                if ( theSquares[i][j].isOccupied() ) { count++; }
            }
        }

        return ((count)%2);
    }

    public void reportWinner(char w)
    {
        String redWin = " Red wins! ";
        String blackWin = " Black wins! ";
        String whoWin = "";

        if(w == 'r')
        {
            whoWin = redWin;
        }
        else if(w == 'b')
        {
            whoWin = blackWin;
        }

        JOptionPane.showMessageDialog( null, whoWin);
    }

}
