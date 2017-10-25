package a4;

/*
This is a class called BlabClient. It creates a client
connects to a server. A server and a client
chat over this network by entering text into the
keyboard and hitting enter, and the server's text is
read and output to the screen. It runs a separate thread
to listen for server messages and output them.
*/

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class BlabClient
{
    boolean allDone = false;
    int port;
    String ip;

    //constructor, most of the action here
    public BlabClient(int p, String str)
    {
        //assign the port and ip address
        port = p;
        ip = str;

        System.out.println("blab client starting ...");
        try
        {
            //connect to the server
            Socket sock = new Socket(ip,port );

            //start a new thread listening for messages from the server
            Thread listener = new Thread(new ClientListens(sock));
            listener.start();

            //get a buffered writer to write to the output stream to the server
            OutputStream out = sock.getOutputStream();
            BufferedWriter bout = new BufferedWriter( new OutputStreamWriter( out ) );

            //a string to store what the user wrote
            String userLine;

            while(!allDone)     //while the user hasn't quit, get what they wrote
            {
                Scanner s = new Scanner(System.in);     //get a scanner to retrieve from the keyboard
                userLine = s.nextLine();                //read the line
                System.out.println("You: "+userLine);   //and echo the user's input

                bout.write(userLine+"\n");          //write to the output stream
                bout.flush();                           //Send it.

                if(userLine.equals("\\quit"))           //if the user typed \quit, we're all done.
                {
                    allDone = true;
                }
            }


            // close the socket connection
            sock.close();
        }
        catch ( IOException ioe )
        { System.err.println(ioe); }
        System.exit(0);
    }

    //runnable class to constantly listen for new messages from the server
    public class ClientListens implements Runnable
    {
        Socket socket;

        //constructor
        ClientListens(Socket s)
        {
            socket = s;
        }

        @Override
        public void run()
        {
            try
            {
                //System.out.println("Client is listening...");
                Scanner in = new Scanner(socket.getInputStream());      //get a scanner to read from the input stream

                while(!socket.isClosed())       //while the socket is open, which should be until we quit
                {
                    if(in.hasNextLine())        //if there's something to get from the input stream
                    {
                        String line = in.nextLine();                //get what the server wrote
                        System.out.println("Chat Buddy: "+line);    //print it out

                        if(line.equals("\\quit"))                   //if your chat buddy quit, quit too
                        {
                            System.out.println("Your buddy has left...exiting chat.");
                            System.exit(1);
                        }
                    }
                }
            }

            catch(Exception e)
            {
                System.out.println("Exception in ClientListens: "+e);
            }
        }
    }
}
