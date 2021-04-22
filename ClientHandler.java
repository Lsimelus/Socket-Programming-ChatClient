import java.net.Socket;
import java.util.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;



public class ClientHandler implements Runnable{
    private ArrayList<PrintWriter> streamHandler = new ArrayList<PrintWriter>();

    private Socket clientSocket;

    BufferedReader inFromClient;
    DataOutputStream outToClient;
    ChatServer server;
    PrintWriter outToClientPrint;


    public ClientHandler(Socket mainSocket, ArrayList<PrintWriter> outputHandler)
    {
        clientSocket = mainSocket;
        streamHandler = outputHandler;


        try
        {

           inFromClient = new BufferedReader(
                          new InputStreamReader(clientSocket.getInputStream() ));

           outToClientPrint = new PrintWriter(clientSocket.getOutputStream(), true);

           streamHandler.add(outToClientPrint);

        }
        catch (IOException ioe)
        {
 
           ioe.printStackTrace(); 

         }
    }

    public void handleMessages()
   {

        try
        {
            String modifiedSentence = "";
            while((modifiedSentence == null) || (modifiedSentence.length() < 1)) {
                modifiedSentence = inFromClient.readLine();
               
            }


            for (int counter = 0; counter < streamHandler.size(); counter++) {
                streamHandler.get(counter).println(modifiedSentence);
                streamHandler.get(counter).flush();
            }
        }
        catch (IOException e)
        {

           // another error occurred

        }

   }//end method handleMessages

    @Override
    public void run(){
        try {

            while(true){
                handleMessages();

            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

}


    