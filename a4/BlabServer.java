package a4;
// DateServer.java
// Silberschatz et al OS Concepts with Java e6 p130+

/*
   This is a demo program for basic socket stuff, to be used with
   DateClient.java.
   The BlabServer starts by setting up a ServerSocket, a place for clients
   to connect to.  Then it waits (repeatedly) for a client to
   call.  When client calls, we have an active Socket.  We open
   an input stream to read from it and an output stream to write
   to it.  Note: the sequence of when the server and client each
   write and read must be exactly agreed upon between the two.
   If not, the pair will hang.
   In this case, the BlabServer expects a message from the BlabClient
   and then the BlabServer sends the date.
   (In this demo pair, the message is "What time is it?",
   but in general the message could be whatever and the
   BlabServer could respond specifically to it).
*/

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import static a4.Blab.port;

public class BlabServer
{
    ServerSocket sock;
    boolean keepGoing = true;
    boolean allDone = false;

    public static void main( String[] args ) //throws IOException
    {
        new BlabServer();
    }

    public BlabServer()
    {
        System.out.println("date server starting ...");
        try
        {
            sock = new ServerSocket(port); // open socket

            // listen for connections
            while (!allDone)
            {
                // when client calls, establish output stream to client and send date
                Socket client = sock.accept(); // this blocks until a client calls
                System.out.println("DateServer: accepts client connection ");

                Thread listener = new Thread(new ServerListens(client));
                listener.start();

                //InputStream in = client.getInputStream();
                //BufferedReader bin = new BufferedReader( new InputStreamReader(in) );
                // Thread.sleep(1000);
                //String msg = bin.readLine();
                //System.out.println("DateServer: read ="+msg);
                //PrintWriter pout = new PrintWriter( client.getOutputStream(), true);
                //String writeme = new java.util.Date().toString();
                //pout.println( writeme );
                //client.close();

                OutputStream out = client.getOutputStream();
                BufferedWriter bout = new BufferedWriter( new OutputStreamWriter( out ) );
                String userLine;

                while(!allDone)
                {
                    Scanner s = new Scanner(System.in);
                    userLine = s.nextLine();
                    bout.write(userLine+"\n");
                    bout.flush();

                    if(userLine.equals("\\quit"))
                    {
                        allDone = true;
                    }
                }

            }
            sock.close(); // is actually never called in this code
        }
        catch( Exception e ) { System.err.println("DateServer: error = "+e); }
        System.exit(0);
    }

    public class ServerListens implements Runnable
    {
        Socket socket;

        ServerListens(Socket s)
        {
            socket = s;
        }

        @Override
        public void run()
        {
            try
            {
                Scanner in = new Scanner(socket.getInputStream());

                while(!socket.isClosed())
                {
                    if(in.hasNextLine())
                    {
                        String line = in.nextLine();
                        System.out.println(line);

                        if(line.equals("\\quit"))
                        {
                            allDone = true;
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
