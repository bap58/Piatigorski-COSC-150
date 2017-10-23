package Blab;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;
import static java.lang.System.setOut;

public class BlabClient
{
    int port = 11058;
    String ip = "localhost";
    ClientReader reader;
    ClientWriter writer;
    Socket sock;
    boolean done = false;

    public BlabClient()
    {
        System.out.println("Blab client starting...");
        //System.out.println("Enter \\quit to quit");

        try
        {
            System.out.println("Calling IP: "+ip+" at Port: "+port);
            sock = new Socket(ip, port);

            reader = new ClientReader();
            writer = new ClientWriter();

            Thread reader1 = new Thread(reader);
            Thread writer1 = new Thread(writer);

            reader1.start();
            //writer1.start();

            //InputStream in = sock.getInputStream();
            //BufferedReader bin = new BufferedReader( new InputStreamReader(in) );

            /*OutputStream out = sock.getOutputStream();
            BufferedWriter bout = new BufferedWriter( new OutputStreamWriter( out ) );
            bout.write("Worked this time...i think\n");
            bout.flush();
            bout.write("hb now...\n");
            bout.flush();*/
            //String line = bin.readLine();
            //System.out.println(line);

            while(!done)
            {
                //wait to be done
            }

            System.out.println("here now");

            sock.close();

        }

        catch(IOException ioe)
        {
            System.err.println(ioe);
        }

        System.exit(0);
    }


    public class ClientReader implements Runnable
    {
        ClientReader() {}

        @Override public void run()
        {
            try
            {

                    //get the input stream from the socket
                    //System.out.println("in here");
                    InputStream in = sock.getInputStream();
                    BufferedReader bin = new BufferedReader( new InputStreamReader(in) );
                    //Scanner bin = new Scanner(sock.getInputStream());
                System.out.println("at least im here");
                    while(!sock.isClosed()) {
                        //System.out.println("now here");
                        String line = bin.readLine();
                        System.out.println(line);
                        System.out.println("further...");
                        /*if(bin.hasNext())
                        {
                            System.out.println("has next...");
                            String line = bin.nextLine();
                            System.out.println(line);

                            if (line.equals("\\quit")) {
                                done = true;
                                System.out.println("I did that");
                            }
                        }
                        else
                        {
                            //Thread.sleep(1000);
                            System.out.println("not yet...");
                        }*/


                    }

            }

            catch(Exception e)
            {
                out.println("Client Reader Error: "+e);
                done = true;
            }

        }
    }

    //thread that writes to client from the server
    public class ClientWriter implements Runnable
    {
        ClientWriter() {}

        @Override public void run()
        {
            try
            {
                Scanner scanner = new Scanner(System.in);  //scanner to get user's msg
                String line = scanner.nextLine();   //store msg in a line string

                //define the output stream's print writer
                PrintWriter pout = new PrintWriter(sock.getOutputStream(), true);
                //pout.write("Test line from client\n");

                while(!line.equals("\\quit")) //while the command to quit isn't typed
                {
                    System.out.println("writing: "+line);
                    pout.println(line);

                    /*if(line != null)    //if there's something in the line
                    {
                        pout.write(line);
                        //pout.println(line); //send the line to output stream
                        pout.flush();
                    }*/

                    line = scanner.nextLine(); //wait for the next input
                }

                done = true;
            }

            catch(Exception e)
            {
                out.println("Client Writer Error: "+e);
            }
        }
    }
}
