package TicTacToe;

public class Player
{
    char mark; //X or O for each player

    //constructor, needs argument m which is x or o
    public Player(char m)
    {
        mark  = m;
    }

    public char getMark() {return mark;}

    //might need turn method
}
