package ConnectFour;

//A game of Connect Four for Java
//Two players, red and black, take turns adding a token of their color
//to the lowest available space in a column on a 7x6 board.
//First player with four tokens in a row (horizontally, vertically,
//or diagonally) wins.
//
//Brian Piatigorski, COSC 150 Fall 2017
//Based on TicTacToe java program in COSC 150

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class C4 extends JFrame
    implements MouseListener
{
    Player[] player;        //two players: red is player[0], black is player[1]
    Board theBoard;       //a 7x6 board for connect four

    static String welMes = "Welcome to Connect Four!\n";
    static String instr1 = "There are two players, red and black. Red goes first.\n";
    static String instr2 = "On your turn, pick a spot to play your token;";
    static String instr3 = " you must pick the lowest open spot in a column.\n";
    static String instr4 = "The first person to connect four wins! ";


    public static void main(String[] args)
    {
        //instantiate a new game
        new C4();

        JOptionPane.showMessageDialog(null, welMes+instr1+instr2+instr3+instr4);
    }

    //Constructor for Connect 4:
    //makes the board and 2 players, adds mouse listener
    public C4()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Connect Four");


        //Instantiate two players, red and black (0 and 1)
        //red player has a key 'r', and black player has a key 'b'
        player = new Player[2];
        player[0] = new Player('r');
        player[1] = new Player('b');


        //Instantiate the board
        theBoard = new Board();
        setLayout(new FlowLayout());
        add(theBoard);

        setSize(900,800);
        setVisible(true);


        //add a mouse listener to get events
        addMouseListener(this);
    }

    //Paint draws the board, with its squares
    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        theBoard.drawMe(g);
    }

    //Mouse listener implementation functions
    @Override public void mousePressed(MouseEvent e) {    }
    @Override public void mouseReleased(MouseEvent e) {    }
    @Override public void mouseEntered(MouseEvent e) {    }
    @Override public void mouseExited(MouseEvent e) {    }

    //Mouse clicks tell what column a piece is being played in
    //If the column is not full, the player whose turn it is
    //will add a token to the lowest available square
    @Override public void mouseClicked(MouseEvent m)
    {
        System.out.println("Mouse clicked at: "+m.getX()+" "+m.getY());
        Square sq = theBoard.squareAt(m);   //returns what square was clicked

        //if a square was clicked, play the square (if legal, determined by the board)
        if(sq != null)
        {
            theBoard.play(sq, player[theBoard.whoseTurn()]);
            repaint();

            //Check if there's a winner
            char w;        //key of the winner, if any
            w = theBoard.isWinner();
            if(w != ' ')
            {
                theBoard.reportWinner(w);    //if there is a winner, report winner.
            }
        }

        repaint();
    }
}
