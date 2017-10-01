package TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TTT extends JFrame
    implements MouseListener
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

        addMouseListener(this);

        setSize(500,500);
        setVisible(true);
    }

    //Mouse handler functions
    //only really need mouse clicked right now
    @Override public void mouseEntered(MouseEvent m){}
    @Override public void mouseExited(MouseEvent m){}
    @Override public void mousePressed(MouseEvent m){}
    @Override public void mouseReleased(MouseEvent m){}

    @Override public void mouseClicked(MouseEvent m)
    {
        System.out.println("Mouse at "+m.getX()+" "+m.getY());
        Square sq = theBoard.findSquare(m);

        if(sq != null && sq.getMark() == ' ')
        {
            sq.setMark(player[theBoard.whoseTurn()].getMark());
            repaint();
            theBoard.winner();
        }
        repaint();
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        theBoard.drawMe(g);
    }

}
