import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.http.HttpRequest;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.io.*;
import java.util.*;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;


public class ChatServer extends JFrame implements ActionListener, NetworkConstants
{


   /**
    *
    */
   private static final long serialVersionUID = 1L;
   private String clientSentence;
   private String capitalizedSentence;

   private ServerSocket welcomeSocket;
   private Socket connectionSocket;

   BufferedReader inFromClient;
   DataOutputStream outToClient;

   private JTextArea displayArea;

   private JButton haltServerButton, startServerButton;
   private ArrayList<PrintWriter> streamHandler;


   //A public constructor to create the TCPServer

   public ChatServer()
   {

        //Handle the window properties

        setTitle("Chat Server");
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int width = 500;
        int height = 200;
        setBounds((d.width - width)/2, (d.height-height)/2, width, height);
        setResizable(false);


        //Setup the GUI components

        displayArea = new JTextArea();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        haltServerButton = new JButton("HALT SERVER");
        haltServerButton.addActionListener(this);
        startServerButton = new JButton("START SERVER");
        startServerButton.addActionListener(this);
        buttonPanel.add(haltServerButton);
        buttonPanel.add(startServerButton);



        //Create the surface to write the GUI onto

        Container contentPane = getContentPane();
        contentPane.add(buttonPanel, BorderLayout.NORTH);
        contentPane.add(new JScrollPane( displayArea ) , BorderLayout.CENTER) ;	
        setVisible( true );


   }//end constructor


   //Implement the one method of the ActionListener interface

   public void actionPerformed(ActionEvent e)
   {

        Object source = e.getSource();

        try{

           if (source == haltServerButton)

              System.exit(0);

           else if(source == startServerButton)
           
              startServer();	
           
		
        }//end try

        catch(Exception ex)
        {
           JOptionPane.showMessageDialog(this, ex);
        }

     }//end method actionPerformed


   //A method to start the server
   //This method handles all the method calls

   private void  startServer() throws Exception
   {


        displayArea.append("Server Started" + '\n');

        welcomeSocket = new ServerSocket(TCP_SERVER_PORT);

        streamHandler = new ArrayList<PrintWriter>();

        while(true)
        {
           //put output stream in a pool of oututstrings

           blockForConnection();  

           ClientHandler handler = new ClientHandler(connectionSocket, streamHandler);
           Thread thread = new Thread(handler);
           thread.start();

        }//end while


   }//close method startServer


   //A method that listens for client requests
   private void blockForConnection()
   {

        try
        {

           connectionSocket = welcomeSocket.accept();

        }
        catch(Exception ex)
        {

           JOptionPane.showMessageDialog(this, ex);

        }

   }//end method blockForConnection

   public static void main(String args [] ) throws Exception
   {
        ChatServer app = new ChatServer();

        app.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        app.startServer();

   }//end method main

}//end class TCPServer