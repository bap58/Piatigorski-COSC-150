package a4;

/*
This is a class called BlabServer. It creates a server
to which clients can connect. A server and a client
chat over this network by entering text into the
keyboard and hitting enter, and the client's text is
read and output to the screen. It runs a separate thread
to listen for client messages and output them.
*/

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class BlabServer
{
    int port;                   //the port number
    ServerSocket sock;          //the ServerSocket for this server
    boolean allDone = false;    //gets set to true if user types \quit

    //constructor, runs most of the good stuff
    public BlabServer(int p)
    {
        //assign port number
        port = p;

        System.out.println("blab server starting ...");
        try
        {
            sock = new ServerSocket(port); // open socket

            //do this while we haven't quit yet
            while (!allDone)
            {
                // when client calls, it happens in two steps:
                //1. the client tests if a server is open. accept that test.
                Socket client = sock.accept(); // this blocks until a client calls
                System.out.println("BlabServer: accepts test connection ");

                //2. client actually calls, accept that too, and keep this one.
                client = sock.accept();
                System.out.println("BlabServer: accepts client connection ");

                //start a new thread listening for messages from the client
                Thread listener = new Thread(new ServerListens(client));
                listener.start();

                //create a buffered writer to write to the output stream
                OutputStream out = client.getOutputStream();
                BufferedWriter bout = new BufferedWriter( new OutputStreamWriter( out ) );

                //string to store whatever the user writes, to send to the client
                String userLine;

                while(!allDone)
                {
                    Scanner s = new Scanner(System.in);         //creates a scanner to read from the user
                    userLine = s.nextLine();                    //stores what the user read
                    System.out.println("You: "+userLine);       //echos the user's input

                    bout.write(userLine+"\n");              //write to the output stream
                    bout.flush();                               //Send it.

                    if(userLine.equals("\\quit"))               //if quit, bread the loop to close
                    {
                        allDone = true;
                    }
                }

            }
            sock.close();
        }
        catch( Exception e )
        {
            System.err.println("BlabServer: error = "+e);
            e.printStackTrace();
        }

        System.exit(0);
    }


    //a runnable class to have a thread listen for client's messages
    public class ServerListens implements Runnable
    {
        Socket socket;

        //constructor
        ServerListens(Socket s)
        {
            socket = s;
        }

        @Override
        public void run()
        {
            try
            {
                //System.out.println("listening in server...");
                Scanner in = new Scanner(socket.getInputStream());      //scanner to read from the input

                //while the socket is still open, which should be until we quit unless something goes wrong
                while(!socket.isClosed())
                {
                    if(in.hasNextLine())        //if something was sent
                    {
                        String line = in.nextLine();                //read what was sent
                        System.out.println("Chat Buddy: "+line);    //and output it to the screen

                        if(line.equals("\\quit"))                   //if your buddy quit, quit too
                        {
                            System.out.println("Your buddy has left...exiting chat.");
                            System.exit(1);
                        }
                    }
                }
            }

            catch(Exception e)
            {
                System.out.println("Exception in ServerListens: "+e);
                e.printStackTrace();
            }
        }
    }
}
