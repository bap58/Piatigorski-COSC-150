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
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class C4 extends JFrame
    implements MouseListener, ActionListener
{
    Player[] player;        //two players: red is player[0], black is player[1]
    Board theBoard;       //a 7x6 board for connect four

    JPanel panelE;          //the east panel of our border layout, will contain reset button
    JButton resetButton;    //a button to reset the board for a new game
    JButton saveButton;     //a button to save the game, write it to a file
    JButton loadButton;     //a button to load the game, read from a file

    JTextField redName;     //a text field to enter Red's name
    JTextField blackName;   //a text field to enter Black's name

    static String saveFile = "src/ConnectFour/saveFileC4.txt";      //file name to save/load a game

    //Strings in the welcome message pop up, has instructions
    static String welMes = "Welcome to Connect Four!\n";
    static String instr1 = "There are two players, red and black. Red goes first.\n";
    static String instr2 = "On your turn, pick a spot to play your token;";
    static String instr3 = " you must pick the lowest open spot in a column.\n";
    static String instr4 = "The first person to connect four wins!\n\n ";


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
            //setLayout(new FlowLayout());  //old layout, before adding buttons
            //add(theBoard);
        setLayout(new BorderLayout());
        add(theBoard, BorderLayout.CENTER);


        //Instantiate the buttons in the east panel:
        //reset button, save button, and load button
        panelE = new JPanel();
        resetButton = new JButton(" Reset ");
        saveButton = new JButton(" Save ");
        loadButton = new JButton(" Load ");

        //instantiate the text fields and frames for adding names for each color
        redName = new JTextField("Write Red's name here.",25);
        blackName = new JTextField("Write Black's name here.", 25);

        add(panelE, BorderLayout.EAST);         //adding panelE to the east border
        panelE.setLayout(new GridLayout(8, 1));

        panelE.add(resetButton);                //put the reset button in the east panel
        panelE.add(saveButton);                 //put the save button in the east panel
        panelE.add(loadButton);                 //put the load button in the east panel
        panelE.add(redName);                    //put the text box for red's name in panel
        panelE.add(blackName);                  //put the text box for black's name in panel

        //add action listeners for all the buttons and text fields
        resetButton.addActionListener(this);
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
        redName.addActionListener(this);
        blackName.addActionListener(this);

        //Display red and black colors in their name text boxes, center text
        redName.setBackground(Color.red);
        redName.setHorizontalAlignment(JTextField.CENTER);
        blackName.setBackground(Color.lightGray);
        blackName.setHorizontalAlignment(JTextField.CENTER);
        

        //determine the window size and make it visible
        setSize(1200,800);
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

    //action events:
    //1. reset button was hit --> reset the game
    //2. save button was hit --> write to saveFile
    //3. load button was hit --> read from saveFile

    @Override public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == resetButton)        //reset button was hit, clear the board
        {
            theBoard.clear();
            repaint();
            System.out.println("The game has been reset.");
        }

        if(e.getSource() == saveButton)         //save button was hit, write a save file
        {
            theBoard.save(saveFile);
            System.out.println("The game has been saved.");
        }

        if(e.getSource() == loadButton)         //load button was hit, read the save file
        {
            theBoard.clear();
            theBoard.load(saveFile);
            repaint();
            System.out.println("The game has been loaded");
        }

    }
}
