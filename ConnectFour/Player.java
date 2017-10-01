package ConnectFour;

//a class that defines the players for connect four
//each player has a key, 'r' or 'b' that indicates if they are red or black

public class Player
{
    char key; //either 'r' or 'b'

    //constructor, only needs to assign key
    public Player(char k){key = k;}


    //key getter
    public char getKey() {return key;}


}
