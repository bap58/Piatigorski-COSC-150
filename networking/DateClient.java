package networking;
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

import static java.lang.System.in;

public class DateClient
{
   public static void main( String[] args ) // throws IOException
   {
	   new DateClient();
   }
 
   public DateClient()
   {
	   System.out.println("date client starting ...");
      try
      {
         Socket sock = new Socket("localhost",5155 );
         //Socket sock = new Socket("141.161.88.4", 5155);

         InputStream in = sock.getInputStream();
         BufferedReader bin = new BufferedReader( new InputStreamReader(in) );
         
         OutputStream out = sock.getOutputStream();
         BufferedWriter bout = new BufferedWriter( new OutputStreamWriter( out ) );
         bout.write("What time is it?\n");
         bout.flush();
         
         // read the date from the socket
         String line;
         line = bin.readLine();
       //  while( (line=bin.readLine()) != null )
         { System.out.println("client read: "+line);  }
         
         // close the socket connection
         sock.close();
      }
      catch ( IOException ioe )
      { System.err.println(ioe); }
      System.exit(0);
   }
}
