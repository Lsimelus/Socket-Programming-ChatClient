/* A UTILITY CLASS WITH ONE STATIC METHOD FOR ADDING USER EMAIL TO THE FILE */

import java.io.*;

public class UserIO {
	public static void addRecord(User user) throws IOException {

		PrintWriter out = new PrintWriter(new FileWriter("UserEmailDB.txt", true));

		out.println(user.getEmailAddress() + " (" + user.getFirstName() + " " + user.getLastName() + ")");

		out.close();

	}// end method main
}// end class UserIO