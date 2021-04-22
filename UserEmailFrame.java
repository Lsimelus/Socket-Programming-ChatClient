/* A CLASS FOR THE WINDOW THAT WILL HOLD THE GUI*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class UserEmailFrame extends JFrame {
	public UserEmailFrame() {
		setTitle("User Email Application");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		int width = 300;
		int height = 170;
		setBounds((d.width - width) / 2, (d.height - height) / 2, width, height);
		setResizable(false);

		addWindowListener(

				// AN ANONYMOUS CLASS DECLARED AS A PARAMETER IN A METHOD CALL
				new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}

				});

		Container contentPane = getContentPane();

		// A CALL TO A CONSTRUCTOR CREATES A REFERENCE AN RETURNS IT TO OUR PROGRAM
		JPanel panel = new UserEmailPanel();
		contentPane.add(panel);

		setVisible(true);

	}// end constructor
}// end class UserEmailFrame