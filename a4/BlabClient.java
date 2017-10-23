package a4;
// DateClient.java
// Silberschatz et al OS Concepts with Java 6e p.131+

/* This is a demo program, to be used with DateServer.java.
   BlabClient calls BlabServer at given IP and port number.  These
   have to match the server (and so you might want to input
   these if they change a lot instead of just having them
   hard-wired into the code).
   The BlabClient says "What time is it?" which the BlabServer is expecting
   to read, and then the BlabServer sends the date back.


*/

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static a4.Blab.ip;
import static a4.Blab.port;
import static java.lang.System.in;

public class BlabClient
{
    boolean allDone = false;

    public static void main( String[] args ) // throws IOException
    {
        new BlabClient();
    }

    public BlabClient()
    {
        System.out.println("blab client starting ...");
        try
        {
            Socket sock = new Socket(ip,port );
            //Socket sock = new Socket("141.161.88.4", 5155);

            Thread listener = new Thread(new ClientListens(sock));
            listener.start();

            //InputStream in = sock.getInputStream();
            //BufferedReader bin = new BufferedReader( new InputStreamReader(in) );

            OutputStream out = sock.getOutputStream();
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


            // close the socket connection
            sock.close();
        }
        catch ( IOException ioe )
        { System.err.println(ioe); }
        System.exit(0);
    }

    public class ClientListens implements Runnable
    {
        Socket socket;

        ClientListens(Socket s)
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
