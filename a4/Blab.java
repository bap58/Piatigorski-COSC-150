package a4;

import java.net.Socket;

public class Blab
{
    static int port = 11058;
    static String ip = "localhost";

    public static void main(String[] args) {
        new Blab();
    }

    public Blab()
    {
        if(serverAlready())
        {
            new BlabClient();
        }
        else
        {
            new BlabServer();
        }
    }

    //checks to see if there's already a server for the port
    //by trying to open a socket there
    public boolean serverAlready()
    {
        boolean returnValue = false;
        Socket sock = null;

        try
        {
            sock = new Socket(ip, port);   //if no exception, socket was opened
            returnValue = true;         //so there was a server there
        }
        catch(Exception e)
        {
            returnValue = false;        //if there's an exception, no server
        }


        if(sock != null) //we'll have to check if we opened a socket to close it
        {
            try
            {
                sock.close();   //have to close the socket if it was opened
            }
            catch(Exception e)  //should never be thrown...but who knows
            {
                System.out.println("Exception closing socket: "+e);
            }
        }

        return returnValue;

    }
}
