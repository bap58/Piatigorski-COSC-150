package networking;
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

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class DateServer
{
   ServerSocket sock;
   boolean keepGoing = true;
   
   public static void main( String[] args ) //throws IOException
   {
	   new DateServer();
   }
   
   public DateServer()
   {
	   System.out.println("date server starting ...");
      try
      {
         sock = new ServerSocket(5155); // open socket
 
         // listen for connections
         while (keepGoing) // has no way to stop as written
         {
        	// when client calls, establish output stream to client and send date
            Socket client = sock.accept(); // this blocks until a client calls      
            System.out.println("DateServer: accepts client connection ");
            InputStream in = client.getInputStream();
            BufferedReader bin = new BufferedReader( new InputStreamReader(in) );
            // Thread.sleep(1000);
            String msg = bin.readLine();
            System.out.println("DateServer: read ="+msg);
            PrintWriter pout = new PrintWriter( client.getOutputStream(), true);
            String writeme = new java.util.Date().toString();
            pout.println( writeme );
            client.close();
         }
    	 sock.close(); // is actually never called in this code
      }
      catch( Exception e ) { System.err.println("DateServer: error = "+e); }      
      System.exit(0);
   }
}
