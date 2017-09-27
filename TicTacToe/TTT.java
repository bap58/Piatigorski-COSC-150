package TicTacToe;

import javax.swing.*;
import java.awt.*;

public class TTT extends JFrame
{
    Player[] player; //two players: x is player[0], o is player[1]
    Board theBoard;

    public static void main(String[] args)
    {
        new TTT();
    }

    //constructor for TTT
    public TTT()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Tic Tac Toe");

        player = new Player[2];
        player[0] = new Player('x');
        player[1] = new Player('o');

        theBoard = new Board();

        setSize(500,500);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        theBoard.drawMe(g);
    }

}
