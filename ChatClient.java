 /* A CLASS FOR THE TCP CLIENT */

/**
 * Internet and Intranet Applications and Protocols
 * Prof. Arthur Goldberg 
 *
 * Homework # 1
 * Submitted by: Allen Harper
 * Date: 2-10-04
 * 
 * Sources used to write this program:
 * http://java.sun.som/docs/books/tutorial/networking/index.html
 * Java Networking Programming, Hughes, Shoffner,Hammer and Bellur
 * Java How to Program, Deitel and Deitel
 *
 *
 *
 * Modified example from K&R textbook.
 * 
 *
 *
 * Modifications made:
 * 1. Constants placed in interface NetworkConstants.
 * 2. Wrote GUI for Client.
 * 3. Catching important exceptions.
 * 4. User can enter address and port# as command-line arguments.
 *
 */           


import java.io.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class ChatClient extends JFrame implements ActionListener, NetworkConstants
{


   private String address;
   private int port;


   private String sentence;
   private String modifiedSentence;


   private Socket clientSocket;

   BufferedReader inFromServer;
   DataOutputStream outToServer;

   private JTextArea displayArea;
   private JLabel inputLabel;
   private JTextField inputTextField;
   private JButton haltClientButton, sendMessageButton;

   private String User = "";
   private boolean started = false;


   

   //A default constructor for TCPClient
   public ChatClient()
   {

      address = TCP_SERVER_IP;
      port = TCP_SERVER_PORT;       

      setGUI();

   }

   //A two-argument constructor for TCPClient
   public ChatClient(String a)
   {
      address = TCP_SERVER_IP;
      port = TCP_SERVER_PORT; 

      User = a;

      setGUI();

   }

   private void setGUI()
   {

        //Handle the window properties

        if (User.length() == 0) {
           setTitle("Chat Client");
        }else{
         setTitle("Chat Client (" + User + ")");
        }
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int width = 500;
        int height = 200;
        setBounds((d.width - width)/2, (d.height-height)/2, width, height);
        setResizable(false);


        //Setup the GUI components


        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        inputLabel = new JLabel("Client Sentence");
        inputTextField = new JTextField(25);
        inputPanel.add(inputLabel);
        inputPanel.add(inputTextField); 


        displayArea = new JTextArea();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        haltClientButton = new JButton("HALT CLIENT");
        haltClientButton.addActionListener(this);

	sendMessageButton = new JButton("Send Sentence");
        sendMessageButton.addActionListener(this);
 
        buttonPanel.add(haltClientButton);
        buttonPanel.add(sendMessageButton);


        //Create the surface to write the GUI onto

        Container contentPane = getContentPane();
        contentPane.setLayout( new BorderLayout(5,5));
        contentPane.add(buttonPanel, BorderLayout.NORTH);
        contentPane.add(inputPanel, BorderLayout.SOUTH);
        contentPane.add(new JScrollPane( displayArea ) , BorderLayout.CENTER) ;	
        setVisible( true );


   }//end setGUI

   //Implement the one method of the ActionListener interface

   public void actionPerformed(ActionEvent e)
   {

        Object source = e.getSource();

        try{

           if (source == haltClientButton)

              System.exit(0);

           else if(source == sendMessageButton)
               if (started){
                  handleMessages();


               }else{
                  startClient();

               }


        }//end try


        catch(Exception ex )
        {
           JOptionPane.showMessageDialog(this, ex);
           ex.printStackTrace(); 

        }

     }//end method actionPerformed


   //A method to start the server
   //This method handles all the method calls

   private void  startClient()
   {


        displayArea.append("Client Started" + '\n');

        setupConnection();        

        setupIOStreams();

         handleMessages();

   }


   //A method that establishes connection to server

   private void setupConnection()
   {

        try
        {

           clientSocket = new Socket(address, port);

        }
        catch(IOException ioe)
        {

           JOptionPane.showMessageDialog(this, ioe);
           ioe.printStackTrace(); 
        }

   }//end method blockForConnection


   private void setupIOStreams()
   {

        try
        {

           inFromServer = new BufferedReader(
                          new InputStreamReader(clientSocket.getInputStream() ));

           outToServer =  new DataOutputStream(clientSocket.getOutputStream());


        }
        catch (IOException ioe)
        {
 
           JOptionPane.showMessageDialog(this, ioe);
           ioe.printStackTrace(); 

         }
   }//end method setupIOStreams



   private void handleMessages()
   {
      started = true;

        try
        {

           sentence = inputTextField.getText();
           inputTextField.setText("");
           if (sentence != null){
              if (sentence.length() > 0 ){
                  if (User.length() == 0) {
                     sentence = "Anonymous" + ": " + sentence +'\n';
                  }else{
                     sentence = User + ": " + sentence +'\n';
                  }
                  outToServer.writeBytes(sentence + '\n');

              }
           }

            String modifiedSentence = "";

            modifiedSentence = inFromServer.readLine();

            if(modifiedSentence != null){
               if((modifiedSentence.length() > 1)){
                  displayArea.append(modifiedSentence  +'\n');
               }
            }
            

        }
        catch (IOException ioe)
        {

           JOptionPane.showMessageDialog(this, ioe);
           ioe.printStackTrace(); 
        }

   }//end method handleMessages



   private void closeConnection()
   {

        try
        {

           clientSocket.close();

        }
        catch (Exception ex)
        {

           JOptionPane.showMessageDialog(this, ex);
           ex.printStackTrace(); 
        }

   }//end method closeConnection


public static void main(String args [] ) throws Exception
   {
      ChatClient app;
	   if(args.length == 0)
        {
           app = new ChatClient();
           app.setupIOStreams();
        }

        else
        {
           app = new ChatClient(args[0]);
           app.setupIOStreams();
        }

        
        while(true){
           app.handleMessages();
         }


   }//end method main

}//end class TCPClient