package a4;

import java.net.Socket;

/*
This package, a4, is a project for COSC 150 with Professor
Barret Koster, by Brian Piatigorski. I worked with Nico Cuevas.
To operate the program, start Blab in java followed by two
command line arguments: the ip address for the server ("localhost"
will do fine) and the port number to be used.

This is a class called Blab, which is the java class
called to start a chat between a BlabServer and BlabClient.
It checks if there is already a server open. If there is, then
it creates a Blab Client. If not, then it creates a BlabServer.
*/

public class Blab
{
    int port;    // = 11058;
    String ip;   // = "localhost";

    public static void main(String[] args)
    {
        if(args.length != 2)
        {
            System.out.println("You must enter two command line arguments:");
            System.out.println("1. the ip address (localhost is fine)");
            System.out.println("2. the port number");
        }
        else {
            new Blab(args[0], args[1]);     //args[0] is the string for the ip, args[1] is the port number
        }
    }

    //constructor
    public Blab(String i, String p)
    {
        ip = i;
        port = Integer.parseInt(p);

        if(serverAlready())     //call function to see if a server is open
        {
            new BlabClient(port, ip);       //if so, you're the client
        }
        else
        {
            new BlabServer(port);           //if not, be the server
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
