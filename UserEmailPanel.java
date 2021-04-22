/* A CLASS FOR THE GUI */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class UserEmailPanel extends JPanel implements ActionListener {
	private JLabel firstNameLabel, lastNameLabel, emailLabel;
	private JTextField firstNameTextField, lastNameTextField, emailTextField;
	private JButton addButton, printButton, exitButton;

	public UserEmailPanel() {

		JPanel textFieldPanel = new JPanel();
		textFieldPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		firstNameLabel = new JLabel("First name:");
		firstNameTextField = new JTextField(15);
		lastNameLabel = new JLabel("Last name:");
		lastNameTextField = new JTextField(15);
		emailLabel = new JLabel("Email address:");
		emailTextField = new JTextField(15);
		emailTextField.setEditable(false);
		textFieldPanel.add(firstNameLabel);
		textFieldPanel.add(firstNameTextField);
		textFieldPanel.add(lastNameLabel);
		textFieldPanel.add(lastNameTextField);
		textFieldPanel.add(emailLabel);
		textFieldPanel.add(emailTextField);

		firstNameTextField.addActionListener(this);
		lastNameTextField.addActionListener(this);

		JPanel buttonPanel = new JPanel();

		// Choose a layout for the buttonPanel
		buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		// Create all the elements and REGISTER the listener
		addButton = new JButton("Add");
		addButton.addActionListener(this);
		printButton = new JButton("Print");
		printButton.addActionListener(this);
		exitButton = new JButton("Exit");
		exitButton.addActionListener(this);
	
		// Add buttons to the buttonPanel
		buttonPanel.add(addButton);
		buttonPanel.add(printButton);
		buttonPanel.add(exitButton);

		// Set the layout of the overall panel		
		setLayout(new BorderLayout());

		// Add the individual panels at specific locations
		add(textFieldPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

	}// end constructor

	// This method is called everytime an event is fired on the GUI
	public void actionPerformed(ActionEvent e) {

		Object source = e.getSource();

		try {
			if (source == exitButton)
				System.exit(0);

			else if (source == firstNameTextField || source == lastNameTextField)

			// This could be improved to match the correct Bowdoin format
				emailTextField
						.setText(firstNameTextField.getText() + "." + lastNameTextField.getText() + "@bowdoin.edu");

			else if (source == printButton)

				PrintAFile.printFile("UserEmailDB.txt");

			else if (source == addButton) {

				User addUser = new User(firstNameTextField.getText(), lastNameTextField.getText(),
						emailTextField.getText());

				UserIO.addRecord(addUser);
				JOptionPane.showMessageDialog(this, "Your email address has been added to the file.");
				firstNameTextField.setText("");
				lastNameTextField.setText("");
				emailTextField.setText("");
			}
		} // end try
		catch (IOException ioe) {
			JOptionPane.showMessageDialog(this, ioe);
		}
	}// end method actionPerformed
}// end class UserEmailPanel