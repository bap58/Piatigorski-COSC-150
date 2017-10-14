package tictactoe2;

public class ComputerPlayer extends Player
{
    Thread t;

   /* public ComputerPlayer(char m)
    {
        super(m);

        t = new Thread(new CPrunner());
        t.start();
    }*/

    public ComputerPlayer(char m, TTT tg)
    {
        super(m, tg);

        t = new Thread(new CPrunner());
        t.start();
    }

    //call this to make the cpu take it's turn, if it is
    //the cpu's turn
    public void takeTurn()
    {
        //System.out.println("take my turn?");    //stub

        Board theBoard = theGame.getTheBoard();

        int whoseTurn = theBoard.whoseTurn();
        if(theGame.player[whoseTurn] == this)
        {
            Square s = theBoard.pickASquare();
            theBoard.play(s, this);
            theGame.repaint();
        }
    }

    //class to ping cpu player every second to see if it's
    //the cpu's turn
    class CPrunner implements Runnable
    {
        public void run()
        {
            while(true)
            {
                try
                {
                    Thread.sleep(1000);
                    takeTurn();
                }
                catch(Exception e){}
            }
        }
    }
}
