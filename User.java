/* A USER CLASS TO STORE EMAIL INFORMATION */

public class User {
	private String firstName;
	private String lastName;
	private String emailAddress;

	public User(String first, String last, String email) {
		firstName = first;
		lastName = last;
		emailAddress = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

}// end class User