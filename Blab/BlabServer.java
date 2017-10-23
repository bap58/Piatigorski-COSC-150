package Blab;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;



public class BlabServer
{
    ServerSocket sock;
    int port = 11058;
    ServerReader reader;
    ServerWriter writer;
    Socket client;
    boolean keepgoing = true;


    public BlabServer()
    {
        System.out.println("Blab server starting...");

        try
        {
            sock = new ServerSocket(port);  //open a socket

            while(keepgoing) {

                //System.out.println("Waiting for client call...");
                client = sock.accept();         //accept the client
                System.out.println("Client accepted.");


                //System.out.println("Enter \\quit to quit");

                reader = new ServerReader();
                writer = new ServerWriter();

                Thread reader1 = new Thread(reader);    //make a reader thread
                Thread writer1 = new Thread(writer);    //make a writer thread

                //start both threads
                //reader1.start();
                //writer1.start();

                PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
                Scanner userIn = new Scanner(System.in);
                String line;
                do {
                    line = userIn.nextLine();
                    System.out.println("Sending line: "+line);
                    pout.write(line+"\r\n");

                }while(!line.equals("\\quit"));


                //PrintWriter pout = new PrintWriter(client.getOutputStream(), true);

                //InputStream in = client.getInputStream();
                //BufferedReader bin = new BufferedReader(new InputStreamReader(in));
                //String line = bin.readLine();

                /*pout.println("does this work too...\n");

                line = bin.readLine();
                System.out.println(line);

                pout.println("\\quit");
                pout.flush();

                //client.close();*/

            }

            sock.close();
        }

        catch(Exception e)
        {
            System.err.println("BlabServer error: "+e);
            System.exit(1);
        }

        System.exit(0); //exit the program once we're all done
    }

    //thread that writes to client from the server
    public class ServerWriter implements Runnable
    {
        ServerWriter() {}

        @Override public void run()
        {
            try
            {

                /*Scanner scanner = new Scanner(System.in);  //scanner to get user's msg
                String line = scanner.nextLine();   //store msg in a line string

                //define the output stream's print writer
                PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
                pout.println("Test Line From Server");

                while(!line.equals("\\quit")) //while the command to quit isn't typed
                {
                    System.out.println("writing "+line);
                    pout.write(line);

                    /*if(line != null)    //if there's something in the line
                    {
                        pout.write(line);
                        //pout.println(line); //send the line to output stream
                        //pout.flush();
                    }

                    line = scanner.nextLine(); //wait for the next input
                }*/


            }

            catch(Exception e)
            {
                System.out.println("Server Writer Error: "+e);
            }
        }
    }

    public class ServerReader implements Runnable
    {
        ServerReader() {}

        @Override public void run()
        {
            while(true)
            {
                try
                {
                    while(!client.isClosed()) {
                        Scanner bin = new Scanner(client.getInputStream());
                        if (bin.hasNext()) {
                            String line = bin.nextLine();
                            System.out.println(line);
                        }
                    }

                    /*//get the input stream from the socket
                    InputStream in = client.getInputStream();
                    BufferedReader bin = new BufferedReader( new InputStreamReader(in) );

                    while(!client.isClosed())
                    {
                        String line = bin.readLine();
                        if(line != null)
                        {
                            System.out.println(line);
                        }


                    }*/

                }

                catch(Exception e)
                {
                    System.out.println("Server Reader Error: "+e);
                }
            }

        }
    }


}
